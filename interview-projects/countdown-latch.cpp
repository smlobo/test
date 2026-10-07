#include <chrono>
#include <cstddef>
#include <iostream>
#include <mutex>
#include <thread>
#include <vector>
#include <condition_variable>

class CountDownLatch {
private:
    mutable std::mutex mutex_;
    std::condition_variable completed_;
    std::size_t count_;

public:
    explicit CountDownLatch(std::size_t initial_count);

    void count_down();
    void wait();

    std::size_t remaining() const;
};

CountDownLatch::CountDownLatch(std::size_t initial_count) : 
    count_(initial_count) {}

void CountDownLatch::count_down() {
    std::unique_lock<std::mutex> lock(mutex_);
    if (count_ == 0) {
        return;
    }
    --count_;
    bool reached_zero = count_ == 0;
    lock.unlock();
    if (reached_zero) {
        completed_.notify_all();
    }
}

void CountDownLatch::wait() {
    std::unique_lock<std::mutex> lock(mutex_);
    completed_.wait(lock, [this] {
        return count_ == 0;
    });
}

std::size_t CountDownLatch::remaining() const {
    std::lock_guard<std::mutex> lock(mutex_);
    return count_;
}

int main() {
    constexpr int worker_count = 3;
    CountDownLatch latch(worker_count);
    std::mutex coutMutex;

    std::vector<std::thread> workers;

    for (int id = 1; id <= worker_count; ++id) {
        workers.emplace_back([id, &latch, &coutMutex] {
            std::this_thread::sleep_for(std::chrono::milliseconds(100 * id));
            std::unique_lock<std::mutex> lock(coutMutex);
            std::cout << "Worker " << id << " finished\n";
            lock.unlock();
            latch.count_down();
        });
    }

    std::unique_lock<std::mutex> lock(coutMutex);
    std::cout << "Main waiting\n";
    lock.unlock();
    latch.wait();
    lock.lock();
    std::cout << "All workers finished\n";
    lock.unlock();

    for (auto& worker : workers) {
        worker.join();
    }
}
