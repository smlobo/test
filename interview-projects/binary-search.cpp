#include <iostream>
#include <vector>

// binary search
int anyOccurence(std::vector<int>& v, int t, int low, int high) {
    // base case
    if (low + 1 == high) {
        if (v[low] == t) {
            return low;
        } else {
            return -1;
        }
    }

    // divide
    int mid = (low + high) / 2;

    if (v[mid] > t) {
        // search lower
        return anyOccurence(v, t, low, mid);
    } else {
        // search higher
        return anyOccurence(v, t, mid, high);
    }
}

int firstOccurence(std::vector<int>& v, int t) {
    if (v.empty()) {
        return -1;
    }
    int r = anyOccurence(v, t, 0, v.size());
    if (r == -1) {
        return r;
    }

    // scan backwards to find the first
    while (r > 0) {
        if (v[r-1] != t) {
            return r;
        }
        r--;
    }
    // corner case: r == 0
    if (v[r] == t) {
        return r;
    }
    return r + 1;
}

int main() {
    std::vector<int> nums = {1, 2, 2, 2, 4, 7};
    int x = firstOccurence(nums, 2);
    std::cout << "[1] first occ 2: " << x << "\n";

    nums = {1, 2, 2, 2, 4, 7};
    x = firstOccurence(nums, 3);
    std::cout << "[2] first occ 3: " << x << "\n";

    nums = {};
    x = firstOccurence(nums, 2);
    std::cout << "empty first occ 2: " << x << "\n";

    nums = {2, 2, 2, 2, 4, 7};
    x = firstOccurence(nums, 2);
    std::cout << "[3] first occ 2: " << x << "\n";
}