/*
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes 
in the sequence has an edge connecting them. A node can only appear in the 
sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.
*/

#include <iostream>
#include <utility>
#include <cassert>

using namespace std;

// * Definition for a binary tree node.
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : 
        val(x), left(left), right(right) {}
};

class Solution {
public:
    void subtreeMaxSum(TreeNode *n, pair<int,int>& iNiP) {
        if (n == nullptr)
            return;

        pair<int,int> leftINiP = {0,0};
        subtreeMaxSum(n->left, leftINiP);
        pair<int,int> rightINiP = {0,0};
        subtreeMaxSum(n->right, rightINiP);

        // Report incremental val as either just this node OR 
        //   this node + max(L, R)
        iNiP.first = max(n->val, n->val + max(leftINiP.first, rightINiP.first));

        // Non-incremental is:
        // Max of left+node+right, left+node, node+right, left, node, right
        iNiP.second = n->val;
        if (n->left != nullptr) {
            iNiP.second = max(iNiP.second, leftINiP.second);
            iNiP.second = max(iNiP.second, n->val + leftINiP.first);
        }
        if (n->right != nullptr) {
            iNiP.second = max(iNiP.second, rightINiP.second);
            iNiP.second = max(iNiP.second, n->val + rightINiP.first);
        }
        if (n->left != nullptr && n->right != nullptr) {
            iNiP.second = max(iNiP.second, n->val + leftINiP.first + rightINiP.first);
        }

        // cout << "[" << n->val << "] " << iNiP.first << "," << iNiP.second << endl;
    }

    int maxPathSum(TreeNode* root) {
        pair<int,int> iNiP;
        subtreeMaxSum(root, iNiP);
        return iNiP.second;        
    }
};

int main() {
    Solution s;
    TreeNode *t;
    int a;

    t = new TreeNode(1, new TreeNode(2), new TreeNode(3));
    a = s.maxPathSum(t);
    cout << a << endl;
    assert(a == 6);

    t = new TreeNode(-10, new TreeNode(9), new TreeNode(20, new TreeNode(15), 
        new TreeNode(7)));
    a = s.maxPathSum(t);
    cout << a << endl;
    assert(a == 42);

    t = new TreeNode(-3);
    a = s.maxPathSum(t);
    cout << a << endl;
    assert(a == -3);

    t = new TreeNode(1, new TreeNode(-2, 
        new TreeNode(1, new TreeNode(-1), nullptr), new TreeNode(3)), 
        new TreeNode(-3, new TreeNode(-2), nullptr));
    a = s.maxPathSum(t);
    cout << a << endl;
    assert(a == 3);

    t = new TreeNode(1, new TreeNode(-2, 
        new TreeNode(1, new TreeNode(-1), nullptr), new TreeNode(3)), 
        new TreeNode(3, new TreeNode(-2), nullptr));
    a = s.maxPathSum(t);
    cout << a << endl;
    assert(a == 5);

    t = new TreeNode(9, new TreeNode(6), 
        new TreeNode(-3, new TreeNode(-6), 
            new TreeNode(2, new TreeNode(2, 
                new TreeNode(-6, new TreeNode(-6), nullptr), 
                new TreeNode(-6)), 
            nullptr)));
    a = s.maxPathSum(t);
    cout << a << endl;
    assert(a == 16);

}
