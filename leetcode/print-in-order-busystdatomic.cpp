#include <atomic>
#include <functional>
#include <iostream>
#include <unistd.h>

class Foo {
    std::atomic<bool> firstDone = {false};
    std::atomic<bool> secondDone = {false};

public:
    Foo() {
    }

    ~Foo() {
    }

    void first(std::function<void()> printFirst) {
        
        // printFirst() outputs "first". Do not change or remove this line.
        printFirst();

        firstDone.store(true);
    }

    void second(std::function<void()> printSecond) {
        while (!firstDone.load()) {}

        // printSecond() outputs "second". Do not change or remove this line.
        printSecond();

        secondDone.store(true);
    }

    void third(std::function<void()> printThird) {
        while (!secondDone.load()) {}

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
