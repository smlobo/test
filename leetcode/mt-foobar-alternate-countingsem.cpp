#include <iostream>
#include <functional>
#include <semaphore>
#include <thread>

class FooBar {
private:
    int n;
    std::counting_semaphore<> fooDone;
    std::counting_semaphore<> barDone;

public:
    FooBar(int n) : n(n), fooDone(0), barDone(1) {
        // barDone.release();
    }

    void foo(std::function<void()> printFoo) {
        
        for (int i = 0; i < n; i++) {
            barDone.acquire();
            // printFoo() outputs "foo". Do not change or remove this line.
            printFoo();
            fooDone.release();
        }
    }

    void bar(std::function<void()> printBar) {
        
        for (int i = 0; i < n; i++) {
            fooDone.acquire();
            // printBar() outputs "bar". Do not change or remove this line.
            printBar();
            barDone.release();
        }
    }
};

int main() {
    FooBar fb{3};

    std::thread tA{[&fb]() -> void {
        fb.foo([]() -> void {
            std::cout << "foo";
        });
    }};
    std::thread tB{[&fb]() -> void {
        fb.bar([]() -> void {
            std::cout << "bar";
        });
    }};

    tA.join();
    tB.join();

    std::cout << "\n";
    return 0;
}
