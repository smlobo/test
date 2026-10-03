#!/usr/bin/python3

"""
Given an array nums containing n + 1 integers where each integer is between 
1 and n (inclusive), prove that at least one duplicate number must exist. 
Assume that there is only one duplicate number, find the duplicate one.

Example 1:
Input: [1,3,4,2,2]
Output: 2

Example 2:
Input: [3,1,3,4,2]
Output: 3

Note:
* You must not modify the array (assume the array is read only).
* You must use only constant, O(1) extra space.
* Your runtime complexity should be less than O(n2).
* There is only one duplicate number in the array, but it could be repeated 
  more than once.

"""

class Solution:
	def findDuplicate(self, nums: 'List[int]') -> 'int':
		c = [None] * (len(nums)-1)
		for i in nums:
			#print(c)
			if c[i-1] == i:
				return i
			c[i-1] = i
		return None

s = Solution()

# test 1
x = [1,3,4,2,2]
y = s.findDuplicate(x)
print("{} duplicate: {}".format(x, y))
assert y == 2

# test 2
x = [3,1,3,4,2]
y = s.findDuplicate(x)
print("{} duplicate: {}".format(x, y))
assert y == 3

# test 3
x = [1,1]
y = s.findDuplicate(x)
print("{} duplicate: {}".format(x, y))
assert y == 1

# test 4
x = [1,1,1]
y = s.findDuplicate(x)
print("{} duplicate: {}".format(x, y))
assert y == 1

# test 5
x = [2,2,1]
y = s.findDuplicate(x)
print("{} duplicate: {}".format(x, y))
assert y == 2

