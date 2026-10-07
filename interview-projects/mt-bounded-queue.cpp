#include <atomic>
#include <cstddef>
#include <condition_variable>
#include <iostream>
#include <mutex>
#include <queue>
#include <stdexcept>
#include <thread>
#include <utility>
#include <vector>

template<typename T>
class MTBoundedQueue {
private:
    std::queue<T> queue_;
    const std::size_t capacity_;
    std::mutex qLock_;
    std::condition_variable notFull_;
    std::condition_variable notEmpty_;
    bool isShutdown_;

public:
    explicit MTBoundedQueue(std::size_t capacity);

    bool push(T item);
    bool pop(T& item);
    void shutdown();
};

// Constructor
template<typename T>
MTBoundedQueue<T>::MTBoundedQueue(std::size_t capacity)
    : capacity_(capacity), isShutdown_(false) {
    if (capacity == 0) {
        throw std::invalid_argument("MTBoundedQueue capacity cannot be 0");
    }
}

template<typename T>
bool MTBoundedQueue<T>::push(T item) {
    std::unique_lock<std::mutex> lock(qLock_);
    // while (!(isShutdown_ || queue_.size() < capacity_)) {
    //     notFull_.wait(lock);
    // }
    notFull_.wait(lock, [this] {
        return isShutdown_ || queue_.size() < capacity_;
    });

    if (isShutdown_) {
        return false;
    }

    queue_.push(std::move(item));
    lock.unlock();
    notEmpty_.notify_one();
    return true;
}

template<typename T>
bool MTBoundedQueue<T>::pop(T& item) {
    std::unique_lock<std::mutex> lock(qLock_);
    notEmpty_.wait(lock, [this] {
        return isShutdown_ || !queue_.empty();
    });

    // Once shutdown begins, consumers drain queued items and then stop.
    if (queue_.empty()) {
        return false;
    }

    item = std::move(queue_.front());
    queue_.pop();
    lock.unlock();
    notFull_.notify_one();
    return true;
}

template<typename T>
void MTBoundedQueue<T>::shutdown() {
    {
        std::lock_guard<std::mutex> lock(qLock_);
        isShutdown_ = true;
    }

    // Wake producers waiting for space and consumers waiting for data.
    notFull_.notify_all();
    notEmpty_.notify_all();
}

int main() {
    constexpr int producerCount = 4;
    constexpr int consumerCount = 3;
    constexpr int itemsPerProducer = 1000;
    constexpr int totalItems = producerCount * itemsPerProducer;

    MTBoundedQueue<int> queue(4);
    std::atomic<int> consumedCount{0};
    std::atomic<long long> consumedSum{0};
    std::vector<std::thread> producers;
    std::vector<std::thread> consumers;

    for (int i = 0; i < consumerCount; ++i) {
        consumers.emplace_back([&] {
            int item;
            while (queue.pop(item)) {
                consumedCount.fetch_add(1, std::memory_order_relaxed);
                consumedSum.fetch_add(item, std::memory_order_relaxed);
            }
        });
    }

    for (int producer = 0; producer < producerCount; ++producer) {
        producers.emplace_back([&, producer] {
            const int first = producer * itemsPerProducer;
            const int last = first + itemsPerProducer;

            for (int item = first; item < last; ++item) {
                if (!queue.push(item)) {
                    std::terminate();
                }
            }
        });
    }

    for (auto& producer : producers) {
        producer.join();
    }

    queue.shutdown();

    for (auto& consumer : consumers) {
        consumer.join();
    }

    const auto expectedSum =
        static_cast<long long>(totalItems - 1) * totalItems / 2;
    if (consumedCount.load() != totalItems ||
        consumedSum.load() != expectedSum) {
        std::cerr << "Queue verification failed\n";
        return 1;
    }

    std::cout << "Consumed " << consumedCount.load()
              << " items with sum " << consumedSum.load() << '\n';

}
