/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */

#include <iostream>

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

std::ostream& operator<<(std::ostream& os, const TreeNode* tn) {
    if (!tn) {
        return os << "null";
    }
    return os << tn->val << ", " << tn->left << ", " << tn->right;
}

class Solution {
public:
    int minDepth(TreeNode* root) {
        if (root == nullptr) {
            return 0;
        }
        if (root->left == nullptr && root->right == nullptr) {
            return 1;
        } else if (root->left == nullptr) {
            return minDepth(root->right) + 1;
        } else if (root->right == nullptr) {
            return minDepth(root->left) + 1;
        }
        int l = minDepth(root->left);
        int r = minDepth(root->right);
        return std::min(l, r) + 1;
    }
};

int main() {
    Solution s;

    TreeNode i13 = TreeNode(15);
    TreeNode i14 = TreeNode(7);
    TreeNode i12 = TreeNode(20, &i13, &i14);
    TreeNode i11 = TreeNode(9);
    TreeNode i1 = TreeNode(3, &i11, &i12);
    std::cout << &i1 << " = " << s.minDepth(&i1) << "\n";

    TreeNode i2a = TreeNode(6);
    TreeNode i2b = TreeNode(5, nullptr, &i2a);
    TreeNode i2c = TreeNode(4, nullptr, &i2b);
    TreeNode i2d = TreeNode(3, nullptr, &i2c);
    TreeNode i2 = TreeNode(2, nullptr, &i2d);
    std::cout << s.minDepth(&i2) << "\n";

    TreeNode i3;
    std::cout << s.minDepth(&i3) << "\n";

    std::cout << s.minDepth(nullptr) << "\n";

    return 0;
}

