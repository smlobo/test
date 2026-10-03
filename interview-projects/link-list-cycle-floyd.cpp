#include <iostream>


struct ListNode {
    int val;
    ListNode* next;
};

ListNode* linkedListCycle(ListNode* ll) {
    // corner case
    if (!ll) {
        return nullptr;
    }

    // Detect cycle
    ListNode* slowP = ll->next;
    if (slowP == nullptr) {
        return nullptr;
    }
    ListNode* fastP = slowP->next;

    bool cycleFound = false;
    while (fastP != nullptr) {
        // Cycle found
        if (slowP == fastP) {
            cycleFound = true;
            break;
        }
        slowP = slowP->next;
        if (fastP->next) {
            fastP = fastP->next->next;
        } else {
            break;
        }
    }

    // No cycle
    if (!cycleFound) {
        return nullptr;
    }

    // Find entry
    slowP = ll;
    while (slowP != fastP) {
        slowP = slowP->next;
        fastP = fastP->next;
    }
    return slowP;
}

int main() {
    // corner cases
    if (!linkedListCycle(nullptr)) {
        std::cout << "no cycle for nullptr\n";
    }

    ListNode l0 = ListNode{0};
    ListNode l1 = ListNode{1};
    ListNode l2 = ListNode{2};
    ListNode l3 = ListNode{3};
    ListNode l4 = ListNode{4};
    ListNode l5 = ListNode{5};

    l0.next = &l0;
    if (ListNode *entry = linkedListCycle(&l0)) {
        std::cout << "cycle found for single cycle: " << entry->val << "\n";
    }

    // No cycle
    l0.next = &l1;
    l1.next = &l2;
    l2.next = &l3;
    l3.next = &l4;
    l4.next = &l5;
    l5.next = nullptr;

    if (!linkedListCycle(&l0)) {
        std::cout << "no cycle for l0\n";
    }

    // cycle entry l2
    l5.next = &l3;
    if (ListNode *entry = linkedListCycle(&l0)) {
        std::cout << "cycle found with entry: " << entry->val << "\n";
    }
}
