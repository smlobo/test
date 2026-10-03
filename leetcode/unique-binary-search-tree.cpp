#include <iostream>
#include <string>

class Solution {
public:
    int numTrees(int n) {
        // base cases
        if (n <= 1) {
            return 1;
        }

        int sum = 0;

        for (int i = 1; i <= n/2; i++) {
            // left subtree
            int lTreeSum = this->numTrees(i-1);
            // right subtree
            int rTreeSum = this->numTrees(n-i);
            // sum for this index + its reflective index after n/2
            sum += lTreeSum*rTreeSum*2;
        }
        // middle tree
        if (n%2 == 1) {
            int singleSide = this->numTrees(n/2);
            sum += singleSide*singleSide;
        }

        return sum;
    }
};

int main(int argc, char* argv[]) {
    if (argc != 2) {
        std::cout << "Usage: " << argv[0] << " <n>\n";
        return -1;
    }
    int n = 0;
    try {
        n = std::stoi(argv[1]);
    } catch (...) {
        std::cout << "Error parsing: " << argv[1] << "\n";
        return -1;
    }
    std::cout << "n = " << n << "\n";
    Solution s;
    int x = s.numTrees(n);
    std::cout << "solution: " << x << "\n";
    return 0;
}
