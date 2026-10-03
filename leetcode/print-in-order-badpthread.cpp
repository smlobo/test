// BAD implementtion - pthread mutex requires thread who locks to unlock

#include <functional>
#include <iostream>
#include <pthread.h>
#include <unistd.h>

class Foo {
    pthread_mutex_t mutex12;
    pthread_mutex_t mutex23;

public:
    Foo() {
        pthread_mutex_init(&mutex12, nullptr);
        pthread_mutex_init(&mutex23, nullptr);
        pthread_mutex_lock(&mutex12);
        pthread_mutex_lock(&mutex23);
        std::cout << "Foo Constructor locked mutex\n";
    }

    ~Foo() {
        pthread_mutex_destroy(&mutex12);
        pthread_mutex_destroy(&mutex23);
    }

    void first(std::function<void()> printFirst) {
        // pthread_mutex_lock(&mutex12);
        
        // printFirst() outputs "first". Do not change or remove this line.
        printFirst();
        pthread_mutex_unlock(&mutex12);
    }

    void second(std::function<void()> printSecond) {
        pthread_mutex_lock(&mutex12);

        // printSecond() outputs "second". Do not change or remove this line.
        printSecond();
        pthread_mutex_unlock(&mutex23);
    }

    void third(std::function<void()> printThird) {
        pthread_mutex_lock(&mutex23);

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
    f->first([]() -> void{
        std::cout << "second";
    });
    return nullptr;
}

void* t3(void *vargp) {
    Foo* f = (Foo*) vargp;
    f->first([]() -> void{
        std::cout << "third";
    });
    return nullptr;
}

int main() {
    Foo f;

    pthread_t tid1, tid2, tid3;
    pthread_create(&tid1, nullptr, &t1, &f);
    pthread_create(&tid3, nullptr, &t3, &f);
    pthread_create(&tid2, nullptr, &t2, &f);

    sleep(1);

    pthread_join(tid1, nullptr);
    pthread_join(tid2, nullptr);
    pthread_join(tid3, nullptr);

    std::cout << "\n";
    return 0;
}
