#include <iostream>
#include <vector>

int main() {
    std::vector<int> numbers;
    numbers.reserve(4);
    for (size_t i = 0; i < 4; i++) {
        numbers.emplace_back(i*i);
        std::cout << "[" << i << "] " << numbers[i] << "; " << numbers.size()
            << "/" << numbers.capacity() << "\n";
    }
    auto itBefore = numbers.begin();
    std::cout << "Iterator before addition: " << *itBefore << "\n";
    std::cout << "Before: " << static_cast<const void*>(numbers.data()) << '\n';
    numbers.emplace_back(444);
    auto itAfter = numbers.begin();
    std::cout << "Iterator after addition: " << *itAfter << "\n";
    std::cout << "After: " << static_cast<const void*>(numbers.data()) << '\n';
}