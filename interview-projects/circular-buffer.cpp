#include <cassert>
#include <iostream>
#include <optional>
#include <stdexcept>
#include <vector>

class CircularBuffer {
private:
    std::vector<std::string> buffer_;
    std::size_t head_;
    std::size_t tail_;
    std::size_t count_;

    std::size_t increment(std::size_t x);

public:
    CircularBuffer(std::size_t size) : buffer_(size), head_(0), tail_(0), 
        count_(0) {
            if (size == 0) {
                throw std::invalid_argument("CircularBuffer size cannot be 0");
            }
        }

    std::optional<std::string> get();
    void put(std::string data);
};

std::size_t CircularBuffer::increment(std::size_t x) {
    return (x + 1) % buffer_.size();
}

std::optional<std::string> CircularBuffer::get() {
    // no data
    if (count_ == 0) {
        return std::nullopt;
    }

    std::string retVal = buffer_[head_];
    head_ = increment(head_);
    count_--;
    return retVal;
}

void CircularBuffer::put(std::string data) {
    buffer_[tail_] = data;
    if (count_ == buffer_.size()) {
        // overwrote data, move the oldest data to be read up
        head_ = increment(head_);
    } else {
        count_++;
    }
    tail_ = increment(tail_);
}

int main() {
    CircularBuffer cb = {4};

    // no data
    assert(cb.get() == std::nullopt);

    cb.put("aaa");
    cb.put("bbb");
    std::cout << "1: " << *cb.get() << "\n";
    cb.put("ccc");
    std::cout << "2: " << *cb.get() << "\n";
    std::cout << "3: " << *cb.get() << "\n";
    assert(cb.get() == std::nullopt);
    cb.put("11");
    cb.put("22");
    cb.put("33");
    cb.put("44");
    std::cout << "4: " << *cb.get() << "\n";
    cb.put("55");
    cb.put("66");
    std::cout << "5 lost 6: " << *cb.get() << "\n";
}
