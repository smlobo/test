/*
We run a preorder depth-first search (DFS) on the root of a binary tree.

At each node in this traversal, we output D dashes (where D is the depth of this 
node), then we output the value of this node.  If the depth of a node is D, the 
depth of its immediate child is D + 1.  The depth of the root node is 0.

If a node has only one child, that child is guaranteed to be the left child.

Given the output traversal of this traversal, recover the tree and return its root.
*/

#include <iostream>
#include <queue>
#include <vector>
#include <stack>
#include <string>
#include <cassert>

using namespace std;

// Definition for a binary tree node.
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
    TreeNode* getNode(string& traversal, int& index) {
        int orig = index;
        while (index >= 0 && traversal[index] != '-')
            index--;
        string num = traversal.substr(index+1, orig-index);
        return new TreeNode(stoi(num));
    }

    int getLevel(string& traversal, int& index) {
        if (index < 0 || traversal[index] != '-')
            return 0;

        int orig = index;
        while (traversal[index] == '-')
            index--;
        return orig-index;
    }

    TreeNode* recoverFromPreorder(string traversal) {
        // Stacks for each level
        vector<stack<TreeNode*>> levelStk(1000);

        int index = traversal.length()-1;

        while (index >= 0) {
            TreeNode* t = getNode(traversal, index);
            int level = getLevel(traversal, index);
            // cout << t->val << " -> " << level << endl;

            if (level < 999 && !levelStk[level+1].empty()) {
                t->left = levelStk[level+1].top();
                levelStk[level+1].pop();
            }
            if (level < 999 && !levelStk[level+1].empty()) {
                t->right = levelStk[level+1].top();
                levelStk[level+1].pop();
            }

            levelStk[level].push(t);
        }

        return levelStk[0].top();
    }
};

string tree2bfs(TreeNode* t) {
    string retVal = "";
    queue<TreeNode*> q;
    q.push(t);

    while (!q.empty()) {
        TreeNode* x = q.front();
        q.pop();

        if (x == nullptr) {
            retVal += "null,";
            continue;
        }

        retVal += to_string(x->val) + ",";

        // both children null
        if (x->left == nullptr && x->right == nullptr)
            continue;

        q.push(x->left);
        q.push(x->right);
    }
    return retVal;
}

int main() {
    Solution s;
    string i;
    TreeNode *root;
    string bfs;

    i = "1-2--3--4-5--6--7";
    root = s.recoverFromPreorder(i);
    bfs = tree2bfs(root);
    cout << i << " = " << bfs << endl;
    assert(bfs == "1,2,5,3,4,6,7,");

    i = "1-2--3---4-5--6---7";
    root = s.recoverFromPreorder(i);
    bfs = tree2bfs(root);
    cout << i << " = " << bfs << endl;
    assert(bfs == "1,2,5,3,null,6,null,4,null,7,null,");

    i = "1-401--349---90--88";
    root = s.recoverFromPreorder(i);
    bfs = tree2bfs(root);
    cout << i << " = " << bfs << endl;
    assert(bfs == "1,401,null,349,88,90,null,");
}
