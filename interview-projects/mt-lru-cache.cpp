#include <cstddef>
#include <iostream>
#include <mutex>
#include <stdexcept>
#include <thread>
#include <unordered_map>
#include <optional>
#include <list>
#include <utility>
#include <string>


class Key {
public:
    std::string key;

    bool operator==(const Key&) const = default;
};

struct KeyHash {
    std::size_t operator()(const Key& key) const noexcept
    {
        return std::hash<std::string>{}(key.key);
    }
};

class Value {
public:
    std::string value;
};

class MTLRUCache {
private:
    std::size_t capacity_;
    std::unordered_map<Key, std::list<std::pair<Key, Value>>::iterator, KeyHash> 
        index_;
    std::list<std::pair<Key, Value>> entries_;
    std::mutex lock_;

public:
    explicit MTLRUCache(std::size_t capacity);

    std::optional<Value> get(const Key& key);
    void put(Key key, Value value);
};

MTLRUCache::MTLRUCache(std::size_t capacity) : capacity_{capacity}, index_{}, 
    entries_{}, lock_{} {
        if (capacity == 0) {
            throw std::invalid_argument("capacity cannot be 0");
        }
    }

std::optional<Value> MTLRUCache::get(const Key& key) {
    // Lock the cache
    std::lock_guard<std::mutex> lock(lock_);

    // Check if in cache
    const auto found = index_.find(key);
    if (found == index_.end()) {
        return std::nullopt;
    }

    // Get a pointer to the entry
    const auto listEntry = found->second;

    // Move it to the front
    entries_.splice(entries_.begin(), entries_, listEntry);

    return listEntry->second;
}

void MTLRUCache::put(Key key, Value value) {
    // Lock the cache
    std::lock_guard<std::mutex> lock(lock_);

    // Already in cache
    const auto found = index_.find(key);
    if (found != index_.end()) {
        // Get a pointer to the entry
        auto listEntry = found->second;

        // Value changed, update
        if (listEntry->second.value != value.value) {
            listEntry->second = value;
        }

        // Move it to the front
        entries_.splice(entries_.begin(), entries_, listEntry);

        return;
    }

    // Not in cache
    // Does not fit, remove LRU
    if (capacity_ == entries_.size()) {
        // Get LRU
        const auto lruEntry = entries_.back();
        // Remove it from the index
        index_.erase(lruEntry.first);
        // Remove from the entries
        entries_.pop_back();
    }

    // Update size
    // Create the entry
    entries_.emplace_front(std::pair<Key, Value>{key, value});
    // Add it to the index
    const auto listEntry = entries_.begin();
    index_.emplace(key, listEntry);
}

int main() {
    MTLRUCache l(3);
    std::mutex coutMutex{};

    std::thread t1{[&l]() -> void {
        l.put(Key{"A"}, Value{"aaa"});        
    }};
    std::thread t2{[&l]() -> void {
        l.put(Key{"B"}, Value{"bbb"});
    }};
    t1.join();
    std::thread t3{[&l, &coutMutex]() -> void {
        auto r = l.get(Key{"A"});
        if (r != std::nullopt) {
            std::lock_guard<std::mutex> coutL(coutMutex);
            std::cout << "cache hit: " << r->value << "\n";
        }
    }};
    std::thread t4{[&l]() -> void {
        l.put(Key{"C"}, Value{"ccc"});
    }};
    std::thread t5{[&l]() -> void {
        l.put(Key{"D"}, Value{"ddd"});
    }};
    std::thread t7{[&l]() -> void {
        l.put(Key{"A"}, Value{"aXaXaX"});
    }};
    std::thread t6{[&l, &coutMutex]() -> void {
        auto r = l.get(Key{"B"});
        if (r == std::nullopt) {
            std::lock_guard<std::mutex> coutL(coutMutex);
            std::cout << "cache miss for B\n";
        }
    }};
    t7.join();
    std::thread t8{[&l, &coutMutex]() -> void {
        auto r = l.get(Key{"A"});
        if (r != std::nullopt) {
            std::lock_guard<std::mutex> coutL(coutMutex);
            std::cout << "cache hit: " << r->value << "\n";
        }
    }};

    t2.join();
    t3.join();
    t4.join();
    t5.join();
    t6.join();
    t8.join();

    return 0;
}
