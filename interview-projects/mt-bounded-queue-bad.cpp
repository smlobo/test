#include <cstddef>
#include <iostream>
#include <mutex>
#include <queue>
#include <semaphore>
#include <thread>

template<typename T>
class MTBoundedQueue {
private:
    std::queue<T> queue_;
    std::size_t capacity_;
    std::mutex qLock_;
    std::binary_semaphore pushBlock_;
    std::binary_semaphore popBlock_;
    bool isShutdown;

public:
    explicit MTBoundedQueue(std::size_t capacity);

    bool push(T item);
    bool pop(T& item);
    void shutdown();
};

// Constructor
// - queue is unlocked
// - push is not blocked
// - pop is blocked
template<typename T>
MTBoundedQueue<T>::MTBoundedQueue(std::size_t capacity) : queue_(),
    capacity_(capacity), qLock_(), pushBlock_(0), popBlock_(1), 
    isShutdown(false) {
        if (capacity == 0) {
            throw std::invalid_argument("MTBoundedQueue capacity cannot be 0");
        }
    }

template<typename T>
bool MTBoundedQueue<T>::push(T item) {
    if (isShutdown) {
        return false;
    }
    pushBlock_.acquire();
    std::size_t qSize = 0;
    {
        std::lock_guard<std::mutex> lock(qLock_);
        queue_.push(std::move(item));
        qSize = queue_.size();
    }
    popBlock_.release();
    if (qSize < capacity_) {
        pushBlock_.release();
    }
    return true;
}

template<typename T>
bool MTBoundedQueue<T>::pop(T& item) {
    if (isShutdown && queue_.empty()) {
        return false;
    }
    popBlock_.acquire();
    std::size_t qSize = 0;
    {
        std::lock_guard<std::mutex> lock(qLock_);
        item = std::move(queue_.front());
        queue_.pop();
        qSize = queue_.size();
    }
    pushBlock_.release();
    if (qSize > 0) {
        popBlock_.release();
    }
    return true;
}

template<typename T>
void MTBoundedQueue<T>::shutdown() {
    isShutdown = true;
}

int main() {
    MTBoundedQueue<std::string> mtbq(4);

}