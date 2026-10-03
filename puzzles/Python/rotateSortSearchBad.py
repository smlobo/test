#!/usr/bin/python3

#import pdb

# This solution uses iteration for finding the rotation point for the 
# rotated sorted array. This does not work for the case where we 
# need to search *both* the lower and upper half of the array since we 
# cannot determine for sure where the rotation point is.

# The correct solution uses recursion and searches both lower and upper 
# halves.

class Solution:

	def lowIndex(self, nums):
		#pdb.set_trace()

		# Not rotated
		if nums[0] < nums[len(nums)-1]:
			return 0

		lIndex = len(nums) - 1
		start = 0
		stop = len(nums)
		while True:
			mid = int((stop - start) / 2 + start)
			#print("  mid : {}".format(mid))

			# corner cases small arrays
			if mid <= 0 or start > stop or mid >= len(nums):
				break

			# Is this the lowest number
			if nums[mid] < nums[mid-1]:
				lIndex = mid
				break

			if nums[mid] <= nums[lIndex]:
				stop = mid
				lIndex = mid - 1
			else:
				start = mid + 1

		return lIndex

	def search(self, nums, target):
		"""
		:type nums: List[int]
		:type target: int
		:rtype: bool
		"""

		if len(nums) == 0:
			return False

		lIndex = self.lowIndex(nums)
		#print("Low index of {} : {}".format(nums, lIndex))

		# Binary search
		start = 0
		stop = len(nums)
		while (True):
			mid = int((stop - start) / 2 + start)

			# Translate the array reference to the rotated value
			midT = mid + lIndex
			if midT >= len(nums):
				midT -= len(nums)

			if nums[midT] == target:
				return True

			if nums[midT] > target:
				stop = mid
			else:
				start = mid + 1

			if start >= stop:
				break

		return False

s = Solution()

# test 0
n0 = [1,1,1,3,1]
print("Search {} in {} : {}".format(3, n0, s.search(n0, 3)))
assert s.search(n0, 3) == True

# test 0
n0 = [2,2,2,3,1]
print("Search {} in {} : {}".format(1, n0, s.search(n0, 1)))
assert s.search(n0, 1) == True

# test 0
n0 = [1,2,2,2,0,1,1]
print("Search {} in {} : {}".format(0, n0, s.search(n0, 0)))
assert s.search(n0, 0) == True

# test 0
n0 = []
print("Search {} in {} : {}".format(5, n0, s.search(n0, 5)))
assert s.search(n0, 5) == False

# test 0
n0 = [1,1]
print("Search {} in {} : {}".format(0, n0, s.search(n0, 0)))
assert s.search(n0, 0) == False

# test 1
n1 = [2,5,6,0,0,1,2]
print("Search {} in {} : {}".format(3, n1, s.search(n1, 3)))
assert s.search(n1, 3) == False

# test 1.5
n15 = [2,5,6,0,1,2]
print("Search {} in {} : {}".format(3, n15, s.search(n15, 3)))
assert s.search(n15, 3) == False

# test 2
n2 = [11,0,1,2,3,4,5,5,6,7,8,9,9]
print("Search {} in {} : {}".format(3, n2, s.search(n2, 3)))
assert s.search(n2, 3) == True

# test 3
n3 = [1,2,3,4,5,5,6,7,8,9,9,10,0]
print("Search {} in {} : {}".format(3, n3, s.search(n3, 3)))
assert s.search(n3, 3) == True

# test 4
n4 = [11,0,1,2,3,5,5,6,7,8,9,9]
print("Search {} in {} : {}".format(3, n4, s.search(n4, 3)))
assert s.search(n4, 3) == True

# test 4
n4 = [11,0,1,2,3,5,5,6,7,8,9,9]
print("Search {} in {} : {}".format(12, n4, s.search(n4, 12)))
assert s.search(n4, 12) == False

# test 5
n5 = [1,2,3,4,5,5,6,7,9,9,10,0]
print("Search {} in {} : {}".format(3, n5, s.search(n5, 3)))
assert s.search(n5, 3) == True

# test 5
n5 = [1,2,3,4,5,5,6,7,9,9,10,0]
print("Search {} in {} : {}".format(8, n5, s.search(n5, 8)))
assert s.search(n5, 8) == False
