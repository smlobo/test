#!/usr/bin/python3

"""
You are given a list of non-negative integers, a1, a2, ..., an, and a 
target, S. Now you have 2 symbols + and -. For each integer, you should 
choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to 
target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5

Explanation: 
-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.

Note:
* The length of the given array is positive and will not exceed 20.
* The sum of elements in the given array will not exceed 1000.
* Your output answer is guaranteed to be fitted in a 32-bit integer.
"""

import math
import testCase

class Solution:
	def doSearch(self, nums: 'List[int]', s: int, i: int) -> int:
		# End condition
		if i == len(nums) - 1:
			# Corner case +/-0 results in 2 possibilities
			if s == 0 and nums[i] == 0:
				return 2
			elif abs(s) == nums[i]:
				return 1
			else:
				return 0

		# Corner case all 0's left
		if nums[i] == 0:
			# Target does not match
			if s != 0:
				return 0
			# Target matches - calculate the permutations remaining
			else:
				return int(math.pow(2, len(nums) - i))

		# Corner case all same numbers
		if nums[i] == nums[len(nums)-1] and s == 0:
			count = len(nums) - i
			
			# Odd number
			if count % 2 == 1:
				return 0
			# Even number
			else:
				# Cannot figure out the relationship - hard code some
				if count == 2:
					return 2
				elif count == 4:
					return 6
				elif count == 6:
					return 20
				elif count == 8:
					return 70
				elif count == 10:
					return 252

		# Max possible from remaining array elements
		theoreticalHigh = nums[i] * (len(nums) - i)

		# Target sum is not viable
		if abs(s) > theoreticalHigh:
			return 0

		# Array index is assigned +ve
		plusPath = self.doSearch(nums, s - nums[i], i + 1)

		# Array index is assigned -ve
		minusPath = self.doSearch(nums, s + nums[i], i + 1)

		return plusPath + minusPath

	def findTargetSumWays(self, nums: 'List[int]', S: int) -> int:
		nums.sort(reverse=True)
		return self.doSearch(nums, S, 0)

s = Solution()
testCase.main(s)
