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
        self.d = dict()
        index = 0
        for value in nums:
        	if value in self.d:
        		chance = random.randint(0, self.d[value].count)
        		if chance == self.d[value].count:
        			self.d[value].index = index
        		self.d[value].count += 1
        	else:
        		self.d[value] = Entry(index)
        	index += 1
        print(self.d)
        #self.d.sort()
        #print(self.d)

    def pick(self, target):
        """
        :type target: int
        :rtype: int
        """
        return self.d[target].index


# Your Solution object will be instantiated and called as such:
# obj = Solution(nums)
# param_1 = obj.pick(target)

# 1
a = [1, 2, 3, 3, 3]
print("{} -> {}".format(a, Solution(a).pick(3)))

# 2
a = [1, 2, 3, 3, 3]
print("{} -> {}".format(a, Solution(a).pick(1)))
