#include <iostream>

// Definition for singly-linked list.
struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    ListNode* oddEvenList(ListNode* head) {
        ListNode* oddHead = nullptr;
        ListNode* oddPtr = oddHead;
        ListNode* evenHead = nullptr;
        ListNode* evenPtr = evenHead;

        bool isOdd = true;
        while (head) {
            if (isOdd) {
                if (!oddHead) {
                    oddPtr = head;
                    oddHead = oddPtr;
                } else {
                    oddPtr->next = head;
                    oddPtr = oddPtr->next;
                }
            } else {
                if (!evenHead) {
                    evenPtr = head;
                    evenHead = evenPtr;
                } else {
                    evenPtr->next = head;
                    evenPtr = evenPtr->next;
                }
            }
            isOdd = !isOdd;
            head = head->next;
        }

        // Attach odd to even list
        if (oddPtr)
            oddPtr->next = evenHead;

        // Clear even tail
        if (evenPtr)
            evenPtr->next = nullptr;

        return oddHead;
    }
};

std::ostream& operator<<(std::ostream& os, const ListNode* r) {
    while (r) {
        os << r->val << ", ";
        r = r->next;
    }
    return os;
}

int main() {
    Solution s;

    ListNode i1a = ListNode(5);
    ListNode i1b = ListNode(4, &i1a);
    ListNode i1c = ListNode(3, &i1b);
    ListNode i1d = ListNode(2, &i1c);
    ListNode i1 = ListNode(1, &i1d);
    std::cout << &i1 << " -> " << s.oddEvenList(&i1) << "\n";

    ListNode i2a = ListNode(7);
    ListNode i2b = ListNode(4, &i2a);
    ListNode i2c = ListNode(6, &i2b);
    ListNode i2d = ListNode(5, &i2c);
    ListNode i2e = ListNode(3, &i2d);
    ListNode i2f = ListNode(1, &i2e);
    ListNode i2 = ListNode(2, &i2f);
    std::cout << &i2 << " -> " << s.oddEvenList(&i2) << "\n";

    ListNode* i3 = nullptr;
    std::cout << i3 << " -> " << s.oddEvenList(i3) << "\n";

    ListNode i4 = ListNode(7);
    std::cout << &i4 << " -> " << s.oddEvenList(&i4) << "\n";
    
    i1a = ListNode(5);
    i1b = ListNode(4, &i1a);
    i1c = ListNode(3, &i1b);
    i1 = ListNode(2, &i1c);
    std::cout << &i1 << " -> " << s.oddEvenList(&i1) << "\n";
}

