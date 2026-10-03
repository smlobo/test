#include <iostream>

class Solution {
public:
    std::vector<int> lexicalOrder(int n) {
        std::vector<int> v = {};

        // Iterate lexicographically
        for (int i = 1; i <= 9; i++) {
            int multiplier = 1;
            for (int j = 1; j <= 4; j++) {
                int lower = i * multiplier;
                int upper = (i+1) * multiplier - 1;
                bool done = false;
                for (int k = lower; k <= upper; k++) {
                    if (n >= k) {
                        v.push_back(k);
                    } else {
                        done = true;
                        break;
                    }
                }
                if (done) {
                    break;
                }
                multiplier *= 10;
            }
        }
        return v;
    }
};

std::ostream& operator<<(std::ostream& os, const std::vector<int>& v) {
    for (int i = 0; i < v.size(); i++) {
        os << v[i] << ", ";
    }
    return os;
}

int main() {
    Solution s;

    std::cout << "13 = " << s.lexicalOrder(13) << "\n";
    std::cout << "2 = " << s.lexicalOrder(2) << "\n";
    std::cout << "1 = " << s.lexicalOrder(1) << "\n";
    std::cout << "10 = " << s.lexicalOrder(10) << "\n";
    std::cout << "9 = " << s.lexicalOrder(9) << "\n";

    return 0;
}

