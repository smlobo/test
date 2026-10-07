#include <atomic>
#include <condition_variable>
#include <iostream>
#include <mutex>
#include <semaphore>
#include <thread>

constexpr int iterations = 100000;

void mutex_demo()
{
    std::mutex mutex;
    int counter = 0;
    auto increment = [&] {
        for (int iteration = 0; iteration < iterations; ++iteration) {
            std::lock_guard<std::mutex> lock(mutex);
            ++counter;
        }
    };
    {
        std::jthread first(increment);
        std::jthread second(increment);
    }
    std::cout << "mutex: counter = " << counter
              << " (expected " << 2 * iterations << ")\n";
}

void semaphore_demo()
{
    std::counting_semaphore<3> tokens(0);
    tokens.release(3);
    {
        std::jthread consumer([&] {
            for (int token = 0; token < 3; ++token) {
                tokens.acquire();
            }
        });
    }
    std::cout << "semaphore: consumed 3 permits posted before the consumer started\n";
}

void condition_demo()
{
    std::mutex mutex;
    std::condition_variable condition;
    bool ready = false;
    int payload = 0;

    std::jthread consumer([&] {
        std::unique_lock<std::mutex> lock(mutex);
        condition.wait(lock, [&] { return ready; });
        int observed = payload;
        lock.unlock();
        std::cout << "condition variable: payload = " << observed << " (expected 42)\n";
    });
    {
        std::lock_guard<std::mutex> lock(mutex);
        payload = 42;
        ready = true;
    }
    condition.notify_one();
}

void atomic_demo()
{
    std::atomic<int> counter{0};
    auto increment = [&] {
        for (int iteration = 0; iteration < iterations; ++iteration) {
            counter.fetch_add(1, std::memory_order_relaxed);
        }
    };
    {
        std::jthread first(increment);
        std::jthread second(increment);
    }
    std::cout << "atomic: counter = " << counter.load(std::memory_order_relaxed)
              << " (expected " << 2 * iterations << ")\n";

    std::atomic<bool> published{false};
    int payload = 0;
    std::jthread producer([&] {
        payload = 42;
        published.store(true, std::memory_order_release);
    });
    while (!published.load(std::memory_order_acquire)) {
    }
    std::cout << "atomic release/acquire: payload = " << payload << " (expected 42)\n";
}

int main()
{
    mutex_demo();
    semaphore_demo();
    condition_demo();
    atomic_demo();
}
