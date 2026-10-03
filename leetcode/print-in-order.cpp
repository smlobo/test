#include <functional>
#include <iostream>
#include <semaphore>
#include <thread>

class Foo {
    std::counting_semaphore<> firstDone;
    std::counting_semaphore<> secondDone;

public:
    Foo() : firstDone(0), secondDone(0) {}

    ~Foo() {}

    void first(std::function<void()> printFirst) {
        
        // printFirst() outputs "first". Do not change or remove this line.
        printFirst();

        firstDone.release();
    }

    void second(std::function<void()> printSecond) {
        firstDone.acquire();

        // printSecond() outputs "second". Do not change or remove this line.
        printSecond();

        secondDone.release();
    }

    void third(std::function<void()> printThird) {
        secondDone.acquire();

        // printThird() outputs "third". Do not change or remove this line.
        printThird();
    }
};

int main() {
    Foo f;

    std::thread t3{([&f]() -> void {
        f.third([]() -> void {
            std::cout << "third";
        });
    })};
    std::thread t2{([&f]() -> void {
        f.second([]() -> void {
            std::cout << "second";
        });
    })};
    std::thread t1{([&f]() -> void {
        f.first([]() -> void {
            std::cout << "first";
        });
    })};

    t1.join();
    t2.join();
    t3.join();

    std::cout << "\n";
    return 0;
}
