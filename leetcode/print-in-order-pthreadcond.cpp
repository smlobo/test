#include <functional>
#include <iostream>
#include <sys/_pthread/_pthread_mutex_t.h>
#include <unistd.h>
#include <pthread.h>

class Foo {
    pthread_mutex_t mutex;
    pthread_cond_t cond;
    bool firstDone = false;
    bool secondDone = false;

public:
    Foo() {
        pthread_mutex_init(&mutex, nullptr);
        pthread_cond_init(&cond, nullptr);
        std::cout << "Foo Constructor locked mutex\n";
    }

    ~Foo() {
        pthread_mutex_destroy(&mutex);
        pthread_cond_destroy(&cond);
    }

    void first(std::function<void()> printFirst) {
        
        // printFirst() outputs "first". Do not change or remove this line.
        printFirst();

        pthread_mutex_lock(&mutex);
        firstDone = true;
        pthread_cond_broadcast(&cond);
        pthread_mutex_unlock(&mutex);
    }

    void second(std::function<void()> printSecond) {
        pthread_mutex_lock(&mutex);
        while (!firstDone) {
            pthread_cond_wait(&cond, &mutex);
        }
        pthread_mutex_unlock(&mutex);

        // printSecond() outputs "second". Do not change or remove this line.
        printSecond();

        pthread_mutex_lock(&mutex);
        secondDone = true;
        pthread_cond_broadcast(&cond);
        pthread_mutex_unlock(&mutex);
    }

    void third(std::function<void()> printThird) {
        pthread_mutex_lock(&mutex);
        while (!secondDone) {
            pthread_cond_wait(&cond, &mutex);
        }
        pthread_mutex_unlock(&mutex);

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
