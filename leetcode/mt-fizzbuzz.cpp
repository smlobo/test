#include <functional>
#include <semaphore>
#include <thread>
#include <iostream>

class FizzBuzz {
private:
    int n;
    std::binary_semaphore sF, sB, sFB, sN;

public:
    FizzBuzz(int n) : n(n), sF(0), sB(0), sFB(0), sN(1) {}

    // printFizz() outputs "fizz".
    void fizz(std::function<void()> printFizz) {
        for (int i = 3; i <= n; i += 3) {
            if (i%5 == 0) {
                continue;
            }
            sF.acquire();
            printFizz();
            sN.release();
        }
    }

    // printBuzz() outputs "buzz".
    void buzz(std::function<void()> printBuzz) {
        for (int i = 5; i <= n; i += 5) {
            if (i%3 == 0) {
                continue;
            }
            sB.acquire();
            printBuzz();
            sN.release();
        }
    }

    // printFizzBuzz() outputs "fizzbuzz".
    void fizzbuzz(std::function<void()> printFizzBuzz) {
        for (int i = 15; i <= n; i += 15) {
            sFB.acquire();
            printFizzBuzz();
            sN.release();
        }
    }

    // printNumber(x) outputs "x", where x is an integer.
    void number(std::function<void(int)> printNumber) {
        for (int i = 1; i <= n; i++) {
            sN.acquire();
            if (i%3 == 0 && i%5 == 0) {
                sFB.release();
            } else if (i%3 == 0) {
                sF.release();
            } else if (i%5 == 0) {
                sB.release();
            } else {
                printNumber(i);
                sN.release();
            }
        }
    }
};

int main() {
    std::vector<int> tests = {15, 1, 2, 3, 5, 6};

    for (int i : tests) {
        FizzBuzz fb{i};

        std::thread tA{[&fb] {
            fb.fizz([] {
                std::cout << "fizz,";
            });
        }};
        std::thread tB{[&fb] {
            fb.buzz([] {
                std::cout << "buzz,";
            });
        }};
        std::thread tC{[&fb] {
            fb.fizzbuzz([] {
                std::cout << "fizzbuzz,";
            });
        }};
        std::thread tD{[&fb] {
            fb.number([](int x) {
                std::cout << x << ",";
            });
        }};

        tA.join();
        tB.join();
        tC.join();
        tD.join();

        std::cout << "\n";
    }

    return 0;
}
