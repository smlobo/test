#include <ios>
#include <iostream>
#include <map>
#include <ostream>
#include <string>
#include <vector>

struct Product {
    int id;
    std::string name;
};

std::ostream& operator<<(std::ostream& os, const Product& p) {
    os << "{" << p.id << ", " << p.name << "}";
    return os;
}

std::ostream& operator<<(std::ostream& os, const std::map<int,Product>& m) {
    for (const auto& e : m) {
        os << "[" << e.first << ", " << e.second << "], ";
    }
    return os;
}

int main() {
    std::vector<Product> products = {
        {1, "A"},
        {2, "B"},
        {1, "dupA"},        
    };
    std::map<int, Product> productMap = {};
    for (const auto& product : products) {
        const auto& emplaceRet = productMap.emplace(product.id, product);
        if (emplaceRet.second) {
            std::cout << "Unique product: [" << emplaceRet.first->first 
                << "] " << emplaceRet.first->second << "\n";
        } else {
            std::cout << "Existing ID: [" << emplaceRet.first->first 
                << "] existing: " << emplaceRet.first->second
                << ", duplicate: " << product << "\n";
        }
    }
    std::cout << "Ordered product map:\n\t" << productMap << "\n";

    // Replace the matching key
    auto [it, inserted] = 
        productMap.insert_or_assign(1, Product{1, "replaceA"});
    std::cout << std::boolalpha << inserted << ": [" << it->first << "] "
        << it->second << "\n";
    std::cout << "Replaced map:\n\t" << productMap << "\n";
}