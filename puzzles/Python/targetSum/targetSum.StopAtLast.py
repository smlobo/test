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

import testCase

class Solution:
	def doSearch(self, nums: 'List[int]', s: int, i: int) -> int:
		# End condition
		if i == len(nums) - 1:
			if s == 0 and nums[i] == 0:
				return 2
			elif abs(s) == nums[i]:
				return 1
			else:
				return 0

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
