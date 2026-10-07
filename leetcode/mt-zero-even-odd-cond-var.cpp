#include <condition_variable>
#include <iostream>
#include <functional>
#include <mutex>
#include <thread>
#include <vector>

class ZeroEvenOdd {
private:
    int n_;
    int count_;
    bool print_zero_;
    std::mutex mutex_;
    std::condition_variable state_changed_;

public:
    ZeroEvenOdd(int n) : n_(n), count_(1), print_zero_(true) {}

    // printNumber(x) outputs "x", where x is an integer.
    void zero(std::function<void(int)> printNumber) {
        while (true) {
            std::unique_lock<std::mutex> lock(mutex_);
            state_changed_.wait(lock, [this] {
                return print_zero_ || count_ > n_;
            });
            if (count_ > n_) {
                lock.unlock();
                state_changed_.notify_all();
                break;
            }
            printNumber(0);
            print_zero_ = false;
            lock.unlock();
            state_changed_.notify_all();
        }
    }

    void even(std::function<void(int)> printNumber) {
        while (true) {
            std::unique_lock<std::mutex> lock(mutex_);
            state_changed_.wait(lock, [this] {
                return (count_%2 == 0 && !print_zero_) || count_ > n_;
            });
            if (count_ > n_) {
                lock.unlock();
                state_changed_.notify_all();
                break;
            }
            printNumber(count_);
            print_zero_ = true;
            ++count_;
            lock.unlock();
            state_changed_.notify_all();
        }
    }

    void odd(std::function<void(int)> printNumber) {
        while (true) {
            std::unique_lock<std::mutex> lock(mutex_);
            state_changed_.wait(lock, [this] {
                return (count_%2 == 1 && !print_zero_) || count_ > n_;
            });
            if (count_ > n_) {
                lock.unlock();
                state_changed_.notify_all();
                break;
            }
            printNumber(count_);
            print_zero_ = true;
            ++count_;
            lock.unlock();
            state_changed_.notify_all();
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
