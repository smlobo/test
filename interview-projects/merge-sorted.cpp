#include <cstddef>
#include <iostream>
#include <vector>

std::vector<int> mergeSorted(const std::vector<int>& a, 
    const std::vector<int>& b) {
    std::vector<int> r = {};
    r.reserve(a.size() + b.size());

    std::size_t aIndex = 0;
    std::size_t bIndex = 0;
    while (aIndex < a.size() && bIndex < b.size()) {
        if (a[aIndex] < b[bIndex]) {
            r.push_back(a[aIndex++]);
        } else {
            r.push_back(b[bIndex++]);
        }
    }
    if (aIndex < a.size()) {
        for (std::size_t i = aIndex; i < a.size(); i++) {
            r.push_back(a[i]);
        }
    }
    if (bIndex < b.size()) {
        for (std::size_t i = bIndex; i < b.size(); i++) {
            r.push_back(b[i]);
        }
    }
    return r;
}

std::ostream& operator<<(std::ostream& os, const std::vector<int>& v) {
    for (int i : v) {
        os << i << ", ";
    }
    return os;
}

int main() {
    std::vector<int> v1 = {1, 3, 5};
    std::vector<int> v2 = {2, 3, 6};
    std::vector<int> a = {1, 2, 3, 3, 5, 6};
    std::vector<int> aa = mergeSorted(v1, v2);
    std::cout << v1 << "}+{" << v2 << "}={" << aa << "\n";

    v1 = {};
    v2 = {2, 4};
    a = {2, 4};
    aa = mergeSorted(v1, v2);
    std::cout << v1 << "}+{" << v2 << "}={" << aa << "\n";
}