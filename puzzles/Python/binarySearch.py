#!/usr/bin/python3

import bisect
import random

class Entry:
	index = -1
	count = 0

	def __init__(self, i):
		self.index = i
		self.count = 1

class Solution:

    def __init__(self, nums):
        """
        :type nums: List[int]
        self.n = nums
        self.n.sort()
        """
        self.n = nums
        print(self.n)
        self.n.sort()
        print(self.n)

    def pick(self, target):
        """
        :type target: int
        :rtype: int
        """
        print("{} <-> {}".format(bisect.bisect_left(self.n, target), 
        	bisect.bisect_right(self.n, target)))
        left = bisect.bisect_left(self.n, target)
        right = bisect.bisect_right(self.n, target)
        return random.randint(left, right)


# Your Solution object will be instantiated and called as such:
# obj = Solution(nums)
# param_1 = obj.pick(target)

# 1
a = [1, 2, 3, 3, 3]
print("{} -> {}".format(a, Solution(a).pick(3)))

# 2
a = [1, 2, 3, 3, 3]
print("{} -> {}".format(a, Solution(a).pick(1)))
