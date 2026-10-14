#include <iostream>
#include <set>

int main() {
    std::multiset<int> mSet = {80, 90, 80, 70, 95};
    std::cout << "Multiset:\n\t";
    for (int i : mSet) {
        std::cout << i << ", ";
    }
    std::cout << "\n";
    std::cout << "# of 80's: " << mSet.count(80) << "\n";
    auto [begin, end] = mSet.equal_range(80);
    std::cout << "The range of 80's: ";
    for (auto it = begin; it != end; ++it) {
        std::cout << *it << ", ";
    }
    std::cout << "\n";
    mSet.erase(begin);
    std::cout << "# of 80's after erase(begin): " << mSet.count(80) << "\n";
    mSet.erase(80);
    std::cout << "# of 80's after erase(80): " << mSet.count(80) << "\n";
}