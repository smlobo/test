#include <iostream>
#include <functional>
#include <semaphore>
#include <thread>

class ZeroEvenOdd {
private:
    int n;
    std::binary_semaphore sZero;
    std::binary_semaphore sEven;
    std::binary_semaphore sOdd;

public:
    ZeroEvenOdd(int n) : n(n), sZero(1), sEven(0), sOdd(0) {}

    // printNumber(x) outputs "x", where x is an integer.
    void zero(std::function<void(int)> printNumber) {
        for (int i = 1; i <= n; i++) {
            sZero.acquire();
            printNumber(0);
            if (i%2) {
                sOdd.release();
            } else {
                sEven.release();
            }
        }
    }

    void even(std::function<void(int)> printNumber) {
        for (int i = 2; i <= n; i += 2) {
            sEven.acquire();
            printNumber(i);
            sZero.release();
        }
    }

    void odd(std::function<void(int)> printNumber) {
        for (int i = 1; i <= n; i += 2) {
            sOdd.acquire();
            printNumber(i);
            sZero.release();
        }
    }
};

int main() {
    std::vector<int> tests = {5, 1, 2};

    auto printNumber = [](int x) -> void {
        std::cout << x;
    };

    for (int p : tests) {
        ZeroEvenOdd i{p};

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
    }
    return 0;
}
