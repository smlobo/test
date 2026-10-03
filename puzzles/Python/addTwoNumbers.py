#!/usr/bin/python3

import sys

# Definition for singly-linked list.
class ListNode:
    def __init__(self, x, n=None):
        self.val = x
        self.next = n

    def printList(self):
        sys.stdout.write("({}".format(self.val))
        while self.next != None:
            self = self.next
            sys.stdout.write(" -> {}".format(self.val))
        sys.stdout.write(")")

class Solution:
    def addTwoNumbers(self, l1, l2, carry=0):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        if l1 == None:
            a = 0
            l1n = None
        else:
            a = l1.val
            l1n = l1.next
        if l2 == None:
            b = 0
            l2n = None
        else:
            b = l2.val
            l2n = l2.next
        c = a + b + carry

        if l1n == None and l2n == None and c < 10:
            return ListNode(c%10)

        return ListNode(c%10, self.addTwoNumbers(l1n, l2n, int(c/10)))

s = Solution()

tl1 = ListNode(2, ListNode(4, ListNode(3)))
tl1.printList()
sys.stdout.write(" + ")
tl2 = ListNode(5, ListNode(6, ListNode(4)))
tl2.printList()
sys.stdout.write(" = ")
s.addTwoNumbers(tl1, tl2).printList()
print()

tl1 = ListNode(0)
tl1.printList()
sys.stdout.write(" + ")
tl2 = ListNode(0)
tl2.printList()
sys.stdout.write(" = ")
s.addTwoNumbers(tl1, tl2).printList()
print()

tl1 = ListNode(0)
tl1.printList()
sys.stdout.write(" + ")
tl2 = ListNode(9, ListNode(9, ListNode(9)))
tl2.printList()
sys.stdout.write(" = ")
s.addTwoNumbers(tl1, tl2).printList()
print()

tl1 = ListNode(9, ListNode(8))
tl1.printList()
sys.stdout.write(" + ")
tl2 = ListNode(7, ListNode(8, ListNode(9, ListNode(9))))
tl2.printList()
sys.stdout.write(" = ")
s.addTwoNumbers(tl1, tl2).printList()
print()
