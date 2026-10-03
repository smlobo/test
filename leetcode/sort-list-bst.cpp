#include <iostream>

// Definition for singly-linked list.
struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};
// Definition for a binary tree node.
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

std::ostream& operator<<(std::ostream& os, const std::vector<int>& v) {
    for (int i = 0; i < v.size(); i++) {
        os << v[i] << ", ";
    }
    return os;
}

class Solution {
public:
    TreeNode* sortedVectorToBST(std::vector<int>& v, int lb, int ub) {
        // Base case - empty
        if (ub < lb) {
            return nullptr;
        }
        // Base case - single element
        if (lb == ub) {
            return new TreeNode(v[lb]);
        }

        // Root
        int mid = (ub - lb) / 2 + lb;
        TreeNode *r = new TreeNode(v[mid]);

        r->left = sortedVectorToBST(v, lb, mid-1);
        r->right = sortedVectorToBST(v, mid+1, ub);

        return r;
    }

    TreeNode* sortedListToBST(ListNode* head) {
        // Link list to vector
        std::vector<int> vec = {};
        while (head) {
            vec.push_back(head->val);
            head = head->next;
        }
        std::cout << vec << "; ";

        return sortedVectorToBST(vec, 0, vec.size()-1);
    }
};

std::ostream& operator<<(std::ostream& os, const ListNode* ln) {
    if (!ln) {
        return os << "nullptr";
    }
    return os << ln->val << " -> " << ln->next;
}

std::ostream& operator<<(std::ostream& os, const TreeNode* tn) {
    if (!tn) {
        return os << "null";
    }
    return os << tn->val << ", " << tn->left << ", " << tn->right;
}

int main() {
    Solution s;

    ListNode l1a = ListNode(9);
    ListNode l1b = ListNode(5, &l1a);
    ListNode l1c = ListNode(0, &l1b);
    ListNode l1d = ListNode(-3, &l1c);
    ListNode l1 = ListNode(-10, &l1d);
    std::cout << &l1 << " = " << s.sortedListToBST(&l1) << "\n";
}

