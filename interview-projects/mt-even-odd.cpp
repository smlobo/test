#include <condition_variable>
#include <iostream>
#include <mutex>
#include <thread>
#include <vector>

class OrderedCounter {
private:
    int maximum_;
    int count_;
    std::mutex lock_;
    std::condition_variable turn_changed_;

    void common_loop(int parity);

public:
    explicit OrderedCounter(int maximum);

    void print_odd();
    void print_even();
};


OrderedCounter::OrderedCounter(int maximum) : maximum_(maximum), count_(1), 
    lock_(), turn_changed_() {}

void OrderedCounter::common_loop(int parity) {
    while (true) {
        std::unique_lock<std::mutex> lock(lock_);
        turn_changed_.wait(lock, [this, parity] {
            return count_ % 2 == parity || count_ > maximum_;
        });
        if (count_ > maximum_) {
            lock.unlock();
            turn_changed_.notify_all();
            break;
        }
        std::cout << count_ << " ";
        ++count_;
        lock.unlock();
        turn_changed_.notify_all();
    }    
}

void OrderedCounter::print_odd() {
    common_loop(1);
}

void OrderedCounter::print_even() {
    common_loop(0);
}

int main() {
    std::vector<int> maxs = {0, 1, 2, 3, 10};
    for (const int max : maxs) {
        std::cout << "max: " << max << "\n";
        OrderedCounter counter(max);

        std::thread odd(&OrderedCounter::print_odd, &counter);
        // std::thread even(&OrderedCounter::print_even, &counter);
        std::thread even([&counter] () -> void {
            counter.print_even();
        });

        odd.join();
        even.join();
        std::cout << "\n";
    }
}
