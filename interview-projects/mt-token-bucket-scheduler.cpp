#include <algorithm>
#include <array>
#include <chrono>
#include <cmath>
#include <cstddef>
#include <functional>
#include <future>
#include <iostream>
#include <mutex>
#include <queue>
#include <stdexcept>
#include <thread>
#include <utility>
#include <vector>
#include <condition_variable>

class TokenBucketScheduler {
public:
    TokenBucketScheduler(std::size_t capacity, double tokensPerSecond,
                         std::size_t workerCount)
        : capacity_(capacity), tokens_(static_cast<double>(capacity)),
          tokensPerSecond_(tokensPerSecond), lastRefill_(Clock::now()) {
        if (capacity == 0 || workerCount == 0 ||
            !std::isfinite(tokensPerSecond) || tokensPerSecond <= 0.0) {
            throw std::invalid_argument("capacity, rate, and worker count must be positive");
        }

        try {
            for (std::size_t i = 0; i < workerCount; ++i) {
                workers_.emplace_back([this] { workerLoop(); });
            }
        } catch (...) {
            shutdown();
            throw;
        }
    }

    ~TokenBucketScheduler() {
        shutdown();
    }

    TokenBucketScheduler(const TokenBucketScheduler&) = delete;
    TokenBucketScheduler& operator=(const TokenBucketScheduler&) = delete;

    std::future<void> submit(std::function<void()> work) {
        std::packaged_task<void()> task(std::move(work));
        auto result = task.get_future();
        {
            std::lock_guard<std::mutex> lock(mutex_);
            if (stopping_) {
                throw std::runtime_error("scheduler is shut down");
            }
            tasks_.push(std::move(task));
        }
        ready_.notify_one();
        return result;
    }

    // Stop accepting tasks, finish queued tasks, and join the workers.
    // Call from the owning thread after producers have finished submitting.
    void shutdown() {
        {
            std::lock_guard<std::mutex> lock(mutex_);
            stopping_ = true;
        }
        ready_.notify_all();
        for (auto& worker : workers_) {
            if (worker.joinable()) {
                worker.join();
            }
        }
    }

private:
    using Clock = std::chrono::steady_clock;

    void refill() { // Called with mutex_ held.
        const auto now = Clock::now();
        const std::chrono::duration<double> elapsed = now - lastRefill_;
        tokens_ = std::min(static_cast<double>(capacity_),
                           tokens_ + elapsed.count() * tokensPerSecond_);
        lastRefill_ = now;
    }

    void workerLoop() {
        for (;;) {
            std::packaged_task<void()> task;
            {
                std::unique_lock<std::mutex> lock(mutex_);
                ready_.wait(lock, [this] { return stopping_ || !tasks_.empty(); });

                if (tasks_.empty()) {
                    return; // Shutdown with no work left.
                }

                refill();
                if (tokens_ < 1.0) {
                    const std::chrono::duration<double> delay(
                        (1.0 - tokens_) / tokensPerSecond_);
                    ready_.wait_for(lock, delay);
                    continue; // Recheck time and queue after waking.
                }

                tokens_ -= 1.0;
                task = std::move(tasks_.front());
                tasks_.pop();
            }
            task(); // Run outside the lock; exceptions are stored in its future.
        }
    }

    const std::size_t capacity_;
    double tokens_;
    const double tokensPerSecond_;
    Clock::time_point lastRefill_;
    std::mutex mutex_;
    std::condition_variable ready_;
    std::queue<std::packaged_task<void()>> tasks_;
    std::vector<std::thread> workers_;
    bool stopping_ = false;
};

int main() {
    TokenBucketScheduler scheduler(2, 2.0, 3); // Burst of 2; refill 2/s.
    std::mutex outputMutex;
    const auto start = std::chrono::steady_clock::now();
    std::array<std::vector<std::future<void>>, 2> results;
    std::vector<std::thread> producers;

    for (int producer = 0; producer < 2; ++producer) {
        producers.emplace_back([&, producer] {
            for (int i = 0; i < 3; ++i) {
                const int taskId = producer * 3 + i;
                results[producer].push_back(scheduler.submit([&, taskId] {
                    const auto elapsed = std::chrono::steady_clock::now() - start;
                    const auto ms = std::chrono::duration_cast<
                        std::chrono::milliseconds>(elapsed).count();
                    {
                        std::lock_guard<std::mutex> lock(outputMutex);
                        std::cout << "task " << taskId << " started at "
                                  << ms << " ms\n";
                    }
                    std::this_thread::sleep_for(std::chrono::milliseconds(200));
                }));
            }
        });
    }

    for (auto& producer : producers) {
        producer.join();
    }
    scheduler.shutdown();
    for (auto& group : results) {
        for (auto& result : group) {
            result.get(); // Rethrow an exception from a task, if any.
        }
    }
}
