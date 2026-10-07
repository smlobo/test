#include <algorithm>
#include <iostream>

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), 
        right(right) {}
};

std::ostream& operator<<(std::ostream& os, const TreeNode* tn) {
    if (!tn) {
        return os << "null";
    }
    return os << tn->val << ", " << tn->left << ", " << tn->right;
}

int maxDepth(const TreeNode* root) {
    // base case
    if (root == nullptr) {
        return 0;
    }

    int maxLeft = 1 + maxDepth(root->left);
    int maxRight = 1 + maxDepth(root->right);

    return std::max(maxLeft, maxRight);
}

int main() {
    TreeNode i13 = TreeNode(15);
    TreeNode i14 = TreeNode(7);
    TreeNode i12 = TreeNode(20, &i13, &i14);
    TreeNode i11 = TreeNode(9);
    TreeNode i1 = TreeNode(3, &i11, &i12);
    std::cout << &i1 << " = " << maxDepth(&i1) << "\n";

    TreeNode i2a = TreeNode(6);
    TreeNode i2b = TreeNode(5, nullptr, &i2a);
    TreeNode i2c = TreeNode(4, nullptr, &i2b);
    TreeNode i2d = TreeNode(3, nullptr, &i2c);
    TreeNode i2 = TreeNode(2, nullptr, &i2d);
    std::cout << &i2 << " = " << maxDepth(&i2) << "\n";

    TreeNode i3;
    std::cout << &i3 << " = " << maxDepth(&i3) << "\n";

    std::cout << maxDepth(nullptr) << "\n";
}
