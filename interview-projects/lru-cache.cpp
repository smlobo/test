#include <cstddef>
#include <iostream>
#include <unordered_map>
#include <optional>
#include <list>
#include <utility>


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

class LRUCache {
private:
    std::size_t capacity;
    std::unordered_map<Key, std::list<std::pair<Key, Value>>::iterator, KeyHash> index;
    std::list<std::pair<Key, Value>> entries;

public:
    explicit LRUCache(std::size_t capacity);

    std::optional<Value> get(const Key& key);
    void put(Key key, Value value);
};

LRUCache::LRUCache(std::size_t capacity) : capacity(capacity) {}

std::optional<Value> LRUCache::get(const Key& key) {
    // Check if in cache
    if (!index.contains(key)) {
        return std::nullopt;
    }

    // Get a pointer to the entry
    const auto listEntry = index.at(key);

    // Move it to the front
    entries.splice(entries.begin(), entries, listEntry);

    return listEntry->second;
}

void LRUCache::put(Key key, Value value) {
    // Already in cache
    if (index.contains(key)) {
        // Get a pointer to the entry
        const auto listEntry = index.at(key);

        // Move it to the front
        entries.splice(entries.begin(), entries, listEntry);

        return;
    }

    // Not in cache
    // Does not fit, remove LRU
    if (capacity == entries.size()) {
        // Get LRU
        const auto lruEntry = entries.back();
        // Remove it from the index
        index.erase(lruEntry.first);
        // Remove from the entries
        entries.pop_back();
    }

    // Update size
    // Create the entry
    entries.emplace_front(std::pair<Key, Value>{key, value});
    // Add it to the index
    const auto listEntry = entries.begin();
    index.emplace(key, listEntry);
}

int main() {
    LRUCache l(3);

    l.put(Key{"A"}, Value{"aaa"});
    l.put(Key{"B"}, Value{"bbb"});
    auto r1 = l.get(Key{"A"});
    if (r1 != std::nullopt) {
        std::cout << "cache hit: " << r1->value << "\n";
    }
    l.put(Key{"C"}, Value{"ccc"});
    l.put(Key{"D"}, Value{"ddd"});
    auto r2 = l.get(Key{"B"});
    if (r2 == std::nullopt) {
        std::cout << "cache miss for B\n";
    }

}
