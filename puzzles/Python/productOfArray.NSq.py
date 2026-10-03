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

		# Create the solution array
		z = [1] * size

		# Offset index from the real index
		offset = 1

		# Iterate (n-1) times to get (n-1) products for each index
		for i in range(size-1):
			print('Iteration {} with offset {}'.format(i, offset))

			# Iterate over each element
			for j in range(size):
				roffset = offset + j
				if roffset >= size:
					roffset -= size
				z[j] *= nums[roffset]

			offset += 1

		return z

s = Solution()

# test 1
a = [1,2,3,4]
b = s.productExceptSelf(a)
print("For {} input, output is {}".format(a, b))
assert b == [24,12,8,6]

