#include <atomic>
#include <chrono>
#include <cstddef>
#include <iostream>
#include <mutex>
#include <semaphore>
#include <thread>
#include <vector>

class CountDownLatch {
private:
    std::binary_semaphore completed_;
    std::atomic<std::size_t> count_;

public:
    explicit CountDownLatch(std::size_t initial_count);

    void count_down();
    void wait();

    std::size_t remaining() const;
};

CountDownLatch::CountDownLatch(std::size_t initial_count) : 
    completed_(initial_count == 0 ? 1 : 0),
    count_(initial_count) {}

void CountDownLatch::count_down() {
    std::size_t current = count_.load();
    while (current != 0) {
        if (count_.compare_exchange_weak(current, current - 1)) {
            break;
        }
    }

    if (current == 1) {
        completed_.release();
    }
}

void CountDownLatch::wait() {
    completed_.acquire();
    completed_.release();
}

std::size_t CountDownLatch::remaining() const {
    return count_.load();
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
