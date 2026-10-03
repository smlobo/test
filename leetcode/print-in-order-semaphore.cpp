#include <functional>
#include <iostream>
#include <sys/semaphore.h>
#include <unistd.h>
#include <semaphore.h>

class Foo {
    sem_t firstDone;
    sem_t secondDone;

public:
    Foo() {
        sem_init(&firstDone, 0, 0);
        sem_init(&secondDone, 0, 0);
    }

    ~Foo() {
        sem_destroy(&firstDone);
        sem_destroy(&secondDone);
    }

    void first(std::function<void()> printFirst) {
        
        // printFirst() outputs "first". Do not change or remove this line.
        printFirst();

        sem_post(&firstDone);
    }

    void second(std::function<void()> printSecond) {
        sem_wait(&firstDone);

        // printSecond() outputs "second". Do not change or remove this line.
        printSecond();

        sem_post(&secondDone);
    }

    void third(std::function<void()> printThird) {
        sem_wait(&secondDone);

        // printThird() outputs "third". Do not change or remove this line.
        printThird();
    }
};

void* t1(void *vargp) {
    Foo* f = (Foo*) vargp;
    f->first([]() -> void{
        std::cout << "first";
    });
    return nullptr;
}

void* t2(void *vargp) {
    Foo* f = (Foo*) vargp;
    f->second([]() -> void{
        std::cout << "second";
    });
    return nullptr;
}

void* t3(void *vargp) {
    Foo* f = (Foo*) vargp;
    f->third([]() -> void{
        std::cout << "third";
    });
    return nullptr;
}

int main() {
    Foo f;

    pthread_t tid1, tid2, tid3;
    pthread_create(&tid3, nullptr, &t3, &f);
    pthread_create(&tid2, nullptr, &t2, &f);
    sleep(1);
    pthread_create(&tid1, nullptr, &t1, &f);

    sleep(1);

    pthread_join(tid1, nullptr);
    pthread_join(tid2, nullptr);
    pthread_join(tid3, nullptr);

    std::cout << "\n";
    return 0;
}
