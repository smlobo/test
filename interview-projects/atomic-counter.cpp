#include <iostream>
#include <thread>
#include <atomic>
#include <vector>

class AtomicCounter {
private:
    std::atomic<int> counter_;

public:
    explicit AtomicCounter(int initial_value = 0) : counter_(initial_value) {}

    void increment();
    bool increment_if_less_than(int limit);
    int get() const;
};

void AtomicCounter::increment() {
    counter_.fetch_add(1);
}

bool AtomicCounter::increment_if_less_than(int limit) {
    int current = counter_.load();
    while (current < limit) {
        if (counter_.compare_exchange_weak(current, current+1)) {
            return true;
        }
    }
    return false;
}

int AtomicCounter::get() const {
    return counter_.load();
}

int main() {
    AtomicCounter counter;

    constexpr int thread_count = 4;
    constexpr int increments_per_thread = 100000;

    std::vector<std::thread> threads;

    for (int i = 0; i < thread_count; ++i) {
        threads.emplace_back([&counter] {
            for (int j = 0; j < increments_per_thread; ++j) {
                counter.increment();
            }
        });
    }

    for (auto& thread : threads) {
        thread.join();
    }

    std::cout << "Expected: "
              << thread_count * increments_per_thread << '\n';

    std::cout << "Actual: " << counter.get() << '\n';

    // increment if less than
    AtomicCounter c2(8);
    int t2Count = 64;
    std::atomic<int> successfulCount{0};
    threads.clear();
    for (int i = 0; i < t2Count; i++) {
        threads.emplace_back([&c2, &successfulCount] {
            if (c2.increment_if_less_than(10)) {
                successfulCount.fetch_add(1);
            }
        });
    }
    for (auto& thread : threads) {
        thread.join();
    }
    std::cout << "Final value (expected 10): " << c2.get() << "\n";
    std::cout << "Successful increment_if_less_than (expected 2): " << 
        successfulCount.load() << "\n";
}
