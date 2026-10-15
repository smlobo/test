#include <algorithm>
#include <cstddef>
#include <functional>
#include <iostream>
#include <vector>

#include "utilities.h"

std::function<int(int)> make_adder(int amount) {
    return [amount](int x) -> int {
        return amount + x;
    };
}

int main() {
    // Capture by value
    std::vector<int> nums = {1, 2, 3};
    int offset = 10;
    std::function<int(int)> addOffset = [offset] (int x) -> int {
        return x + offset;
    };
    offset = 20;
    std::cout << "Original vector: " << nums << "\n";
    std::vector<int> new_nums(nums.size());
    // for (size_t i = 0; i < nums.size(); i++) {
    //     new_nums[i] = addOffset(nums[i]);
    // }
    std::transform(nums.begin(), nums.end(), new_nums.begin(), addOffset);
    std::cout << "New vector: " << new_nums << "\n";

    // Capture by ref
    nums = {4, 5, 6};
    int total = 0;
    std::function<void(int)> sumVector = [&total] (int x) {
        total += x;
    };
    std::for_each(nums.begin(), nums.end(), sumVector);
    std::cout << "Sum of: " << nums << " = " << total << "\n";

    // mutable capture
    int count = 0;
    std::function<int()> incrCapture = [count] () mutable -> int {
        return ++count;
    };
    for (int i = 0; i < 3; i++) {
        std::cout << "[" << i << "] outer: " << count << ", capture return: "
            << incrCapture() << "\n";
    }

    // Lambda variable
    std::function<int(int)> addTo7 = make_adder(7);
    std::cout << "run addTo7 for 3: " << addTo7(3) << "\n";
    std::cout << "run addTo7 for 10: " << addTo7(10) << "\n";

    // Capture in a loop
    std::vector<std::function<void()>> lmbdas;
    for (int i = 0; i < 3; i++) {
        lmbdas.emplace_back([i] {
            std::cout << "Lambda capture index: " << i << "\n";
        });
    }
    for (const auto& lmbda : lmbdas) {
        lmbda();
    }
}
