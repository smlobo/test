#include <functional>
#include <ios>
#include <iostream>
#include <unordered_map>
#include <string>
#include <vector>

struct ProductId {
    int store;
    int number;
    struct Hash {
        bool operator()(const ProductId& p) const {
            return std::hash<int>{}(p.store) ^ std::hash<int>{}(p.number);
        }
    };
    struct Equals {
        bool operator()(const ProductId& a, const ProductId& b) const {
            return a.number == b.number && a.store == b.store;
        }
    };
};

std::ostream& operator<<(std::ostream& os, const ProductId& p) {
    os << "{" << p.store << "," << p.number << "}";
    return os;
}

template <typename H, typename E>
std::ostream& operator<<(std::ostream& os, 
    const std::unordered_map<ProductId, std::string, H, E>& m) {
    for (const auto& [p, n] : m) {
        os << p << " -> " << n << ", ";
    }
    return os;
}

int main() {
    std::vector<std::pair<ProductId, std::string>> products = {
        {{1, 1000}, "apple1"},
        {{2, 1000}, "apple2"},
        {{1, 1001}, "banana"},
        {{1, 1000}, "appleX"},
    };
    std::unordered_map<ProductId, std::string, ProductId::Hash, ProductId::Equals> 
        productMap = {};
    for (const auto& product : products) {
        const auto& [id, name] = product;
        const auto& [it, inserted] = productMap.try_emplace(id, name);
        if (inserted) {
            std::cout << "Unique id inserted: [" << it->first << "] " 
                << it->second << "\n";
        } else {
            std::cout << "Existing id: [" << it->first << "] " 
                << it->second << "\n";
            const auto& [it2, inserted2] = productMap.insert_or_assign(
                id, name);
            std::cout << "Overwrite existing: <" << std::boolalpha
                << inserted2 << "> [" << it2->first << "] " << it2->second
                << "\n";
        }
    }
    std::cout << "Unordered product id map:\n\t" << productMap << "\n";
}