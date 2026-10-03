#!/usr/bin/python3

"""
Given an array of integers with possible duplicates, randomly output the index 
of a given target number. You can assume that the given target number must 
exist in the array.
"""

import bisect
import random

class Solution:

    def __init__(self, nums):
        """
        :type nums: List[int]
        self.n = nums
        self.n.sort()
        """
        self.d = dict()
        index = 0
        for value in nums:
            if value not in self.d:
                self.d[value] = list()
            self.d[value].append(index)
            index += 1
        print(self.d)

    def pick(self, target):
        """
        :type target: int
        :rtype: int
        """
        tl = self.d[target]
        ti = random.randrange(len(tl))
        return tl[ti]

# Your Solution object will be instantiated and called as such:
# obj = Solution(nums)
# param_1 = obj.pick(target)

# 1
a = [1, 2, 3, 3, 3]
s = Solution(a)
print("{} -> {}, {}, {}, {}".format(a, s.pick(3), s.pick(3), s.pick(3), 
    s.pick(3)))

# 2
a = [1, 2, 3, 3, 3]
print("{} -> {}".format(a, Solution(a).pick(1)))
