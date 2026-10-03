#include <iostream>
#include <functional>
#include <semaphore>
#include <thread>

class ZeroEvenOdd {
private:
    int n;
    std::atomic<int> c;
    std::counting_semaphore<> zeroDone;
    std::counting_semaphore<> otherDone;

public:
    ZeroEvenOdd(int n) : n(n), c(1), zeroDone(0), otherDone(1) {}

    // printNumber(x) outputs "x", where x is an integer.
    void zero(std::function<void(int)> printNumber) {
        while (c.load() < n) {
            otherDone.acquire();
            printNumber(0);
            zeroDone.release();
        }
    }

    void even(std::function<void(int)> printNumber) {
        while (c.load() <= n) {
            if (c.load()%2 == 0) {
                zeroDone.acquire();
                printNumber(c.load());
                otherDone.release();
                c.store(c.load()+1);
            }
        }
    }

    void odd(std::function<void(int)> printNumber) {        
        while (c.load() <= n) {
            if (c.load()%2 == 1) {
                zeroDone.acquire();
                printNumber(c.load());
                otherDone.release();
                c.store(c.load()+1);
            }
        }
    }
};

int main() {
    ZeroEvenOdd i{5};

    auto printNumber = ([](int x) -> void {
        std::cout << x;
    });

    std::thread tA{[&i, &printNumber]() -> void {
        i.zero(printNumber);
    }};
    std::thread tB{[&i, &printNumber]() -> void {
        i.even(printNumber);
    }};
    std::thread tC{[&i, &printNumber]() -> void {
        i.odd(printNumber);
    }};

    tA.join();
    tB.join();
    tC.join();

    std::cout << "\n";
    return 0;
}
