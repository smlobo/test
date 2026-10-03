#!/usr/bin/python3

"""
Find the kth largest element in an unsorted array. Note that it is the kth 
largest element in the sorted order, not the kth distinct element.

Example 1:
Input: [3,2,1,5,6,4] and k = 2
Output: 5

Example 2:
Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
"""

class Solution:
	def findKthLargest(self, nums: 'List[int]', k: int) -> int:
		nums.sort(reverse=True)
		return nums[k-1]

s = Solution()

# test 1
a = [3, 2, 1, 5, 6, 4]
b = 2
c = s.findKthLargest(a, b)
print("{} largest of {} = {}".format(b, a, c))
assert c == 5

# test 2
a = [3,2,3,1,2,4,5,5,6]
b = 4
c = s.findKthLargest(a, b)
print("{} largest of {} = {}".format(b, a, c))
assert c == 4

# test 3
a = [3,1,2,3,1,2,4,5,5,6]
b = 4
c = s.findKthLargest(a, b)
print("{} largest of {} = {}".format(b, a, c))
assert c == 4

# test 4
a = [1,1,1,1,1,1]
b = 4
c = s.findKthLargest(a, b)
print("{} largest of {} = {}".format(b, a, c))
assert c == 1

# test 5
a = [1,1,1,100,1,100,1,100]
b = 2
c = s.findKthLargest(a, b)
print("{} largest of {} = {}".format(b, a, c))
assert c == 100

# test 5
a = [100,200,100,88,3,354,12,65]
b = 1
c = s.findKthLargest(a, b)
print("{} largest of {} = {}".format(b, a, c))
assert c == 354
