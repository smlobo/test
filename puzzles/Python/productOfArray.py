#!/usr/bin/python3

"""

Given an array nums of n integers where n > 1,  return an array output such 
that output[i] is equal to the product of all the elements of nums except 
nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does 
not count as extra space for the purpose of space complexity analysis.)

"""

class Solution:
	def productExceptSelf(self, nums):

		size = len(nums)

		# The forward & backward accumulation array
		forward = [1] * size
		backward = [1] * size

		# Forward accumulative product
		for i in range(size-1):
			print('Forward iteration {}'.format(i))
			forward[i+1] = forward[i] * nums[i]
		print("Forward array: {}".format(forward))

		# Backward accumulative product
		for i in range(size-1, 0, -1):
			print('Backward iteration {}'.format(i))
			backward[i-1] = backward[i] * nums[i]
		print("Backward array: {}".format(backward))

		# Solution array
		z = [0] * size
		for i in range(size):
			z[i] = forward[i] * backward[i]
		return z

s = Solution()

# test 1
a = [1,2,3,4]
b = s.productExceptSelf(a)
print("For {} input, output is {}".format(a, b))
assert b == [24,12,8,6]

