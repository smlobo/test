#include <iostream>
#include <queue>
#include <vector>

// Definition for a binary tree node.
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution {
public:
    void dfs(TreeNode* r, std::vector<int>& r2lVector, int n) {
        int shiftedN = n*10 + r->val;
        // A leaf
        if (r->left == nullptr && r->right == nullptr) {
            r2lVector.push_back(shiftedN);
            return;
        }
        // Not a leaf
        if (r->left) {
            dfs(r->left, r2lVector, shiftedN);
        }
        if (r->right) {
            dfs(r->right, r2lVector, shiftedN);
        }
    }

    int sumNumbers(TreeNode* root) {
        std::vector<int> r2lVector = {};
        dfs(root, r2lVector, 0);
        int sum = 0;
        for (const int n : r2lVector) {
            sum += n;
        }
        return sum;
    }
};

TreeNode* listToTree(std::vector<int>& l, int index) {
    int vIndex = index-1;
    if (vIndex >= l.size() || l[vIndex] < 0) {
        return nullptr;
    }
    // std::cout << "[" << index << "] " << l[vIndex] << "\n";
    TreeNode *root = new TreeNode(l[vIndex]);
    TreeNode *left = listToTree(l, index*2);
    TreeNode *right = listToTree(l, index*2+1);
    root->left = left;
    root->right = right;
    return root;
}

// Print BFS
std::ostream& operator<<(std::ostream& os, const TreeNode* t) {
    os << "{";
    std::queue<const TreeNode*> bfsQ;
    bfsQ.push(t);

    while (!bfsQ.empty()) {
        const TreeNode* curr = bfsQ.front();
        bfsQ.pop();
        if (curr != nullptr) {
            bfsQ.push(curr->left);
            bfsQ.push(curr->right);
            os << curr->val << ",";
        } else {
            os << "∅,";            
        }
    }
    os << "}";
    return os;
}

int main() {
    Solution s;
    std::vector<int> l1 = {1,2,3};
    TreeNode *t1 = listToTree(l1, 1);
    std::cout << "t1 = " << t1 << "\n";
    int a1 = s.sumNumbers(t1);
    std::cout << "a1 = " << a1 << "\n";

    std::vector<int> l2 = {4,9,0,5,1};
    TreeNode *t2 = listToTree(l2, 1);
    std::cout << "t2 = " << t2 << "\n";
    int a2 = s.sumNumbers(t2);
    std::cout << "a2 = " << a2 << "\n";

    std::vector<int> l3 = {1,2,3,-1,4,5};
    TreeNode *t3 = listToTree(l3, 1);
    std::cout << "t3 = " << t3 << "\n";
    int a3 = s.sumNumbers(t3);
    std::cout << "a3 = " << a3 << "\n";

    std::vector<int> l4 = {0,1};
    TreeNode *t4 = listToTree(l4, 1);
    std::cout << "t4 = " << t4 << "\n";
    int a4 = s.sumNumbers(t4);
    std::cout << "a4 = " << a4 << "\n";

}