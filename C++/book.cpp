#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <chrono>
#include <random>
#include <set>

struct Book {
    std::string title;
    int pages;
    bool operator<(const Book& other) const {
        if (pages != other.pages) {
            return pages < other.pages;
        }
        return title < other.title;
    }
};

std::ostream& operator<<(std::ostream& os, const Book& b) {
    os << "{" << b.title << "," << b.pages << "}";
    return os;
}

std::ostream& operator<<(std::ostream& os, const std::vector<Book>& v) {
    for (const auto& book : v) {
        os << book << ", ";
    }
    return os;
}

std::ostream& operator<<(std::ostream& os, const std::set<Book>& s) {
    for (const auto& book : s) {
        os << book << ", ";
    }
    return os;
}

int main() {
    // Random for shuffle
    std::default_random_engine engine;
    engine.seed(static_cast<std::default_random_engine::result_type>(
        std::chrono::system_clock::now().time_since_epoch().count()));

    // Sort vector of Books by pages & then title
    std::vector<Book> books = {{"Zebra", 120}, {"Atlas", 200}, {"Amber", 120}};
    std::cout << "Original order:\n\t" << books << "\n";
    std::sort(books.begin(), books.end(), [](const Book& a, const Book& b) {
        if (a.pages != b.pages) {
            return a.pages < b.pages;
        }
        return a.title < b.title;
    });
    std::cout << "Sorted (pages,title) order:\n\t" << books << "\n";

    // Sort using default comparator
    std::shuffle(books.begin(), books.end(), engine);
    std::cout << "Random order:\n\t" << books << "\n";
    std::sort(books.begin(), books.end());
    std::cout << "Sorted (pages,title) default order:\n\t" << books << "\n";

    // Add identical book & sort
    books.emplace_back(Book{"Amber", 120});
    books.emplace_back(Book{"Zebra", 120});
    std::shuffle(books.begin(), books.end(), engine);
    std::cout << "Random order (duplicates):\n\t" << books << "\n";
    std::sort(books.begin(), books.end());
    std::cout << "Sorted default order (duplicates):\n\t" << books << "\n";

    // Set of books
    std::set<Book> bookSet = {};
    for (const auto& book : books) {
        bookSet.emplace(book);
    }
    std::cout << "Set (duplicates removed):\n\t" << bookSet << "\n";
}
