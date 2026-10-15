#include <algorithm>
#include <iostream>
#include <memory>
#include <functional>

int main() {
    std::unique_ptr<int> data = std::make_unique<int>(42);
    std::move_only_function<void()> job = [p = std::move(data)] {
        std::cout << "data = " << *p << "\n";
    };
    std::cout << std::boolalpha << "data does not own int: " 
        << (data == nullptr) << '\n';
    job();
}