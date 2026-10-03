/*
Given the head of a linked list, rotate the list to the right by k places.
*/

#include <iostream>

using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    ListNode* rotateRight(ListNode* head, int k) {
        // null list or 0 rotations
        if (k == 0 || head == nullptr)
            return head;

        // Get list size & get pointer to tail
        ListNode* node = head;
        int size = 0;
        ListNode* tail = nullptr;
        while (node != nullptr) {
            size++;
            tail = node;
            node = node->next;
        }

        // Real rotations
        k = k%size;

        // size 1 or rotations 0
        if (size == 1 || k == 0)
            return head;

        // Move the head (size-k) spots
        ListNode* newHead = head;
        ListNode *newTail = head;
        for (int i = 0; i < (size-k); i++) {
            newTail = newHead;
            newHead = newHead->next;
        }

        // Connect old tail to head
        tail->next = head;

        // Break newTail from newHead
        newTail->next = nullptr;

        return newHead;
    }
};

ostream& operator<<(ostream& strm, ListNode*& head) {
    ListNode *node = head;
    while (node != nullptr) {
        strm << node->val << ",";
        node = node->next;
    }
    return strm;
}

int main() {
    Solution s;
    ListNode* head;
    int k;
    ListNode* rotated;

    head = new ListNode(1, 
        new ListNode(2, 
            new ListNode(3, 
                new ListNode(4, 
                    new ListNode(5)))));
    cout << head << endl;
    k = 2;
    rotated = s.rotateRight(head, k);
    cout << rotated << endl;


    head = new ListNode(0, 
        new ListNode(1, 
            new ListNode(2)));
    cout << head << endl;
    k = 4;
    rotated = s.rotateRight(head, k);
    cout << rotated << endl;

    head = nullptr;
    cout << head << endl;
    k = 4;
    rotated = s.rotateRight(head, k);
    cout << rotated << endl;

    head = new ListNode(0);
    cout << head << endl;
    k = 100;
    rotated = s.rotateRight(head, k);
    cout << rotated << endl;
}
