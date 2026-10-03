#!/usr/bin/python3

"""
Implement next permutation, which rearranges numbers into the 
lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest 
possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its 
corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

"""

class Solution:

	def nextPermutation(self, nums: 'List[int]') -> None:
		"""
		Do not return anything, modify nums in-place instead.
		"""

		# Iterate from high index to low looking for a big to small 
		# transition
		index = len(nums) - 1
		while index > 0:
			if nums[index] > nums[index-1]:
				break
			index -= 1

		# Sort this upper end of the array (by swapping first and last)
		# Keep track of the next highest number for swapping with the 
		# previous index
		lower = index
		upper = len(nums) - 1
		#print("<{}:{}>".format(lower, upper))
		targetIndex = upper
		current = nums[lower]
		# Need to check target for the middle element in the swap sort!!
		while lower <= upper:
			# Swap
			temp = nums[lower]
			nums[lower] = nums[upper]
			nums[upper] = temp

			# Search for the target number which will be next highest
			if nums[lower] <= current and lower < targetIndex and nums[lower] > nums[index-1]:
				current = nums[lower]
				targetIndex = lower
			if nums[upper] <= current and upper < targetIndex and nums[upper] > nums[index-1]:
				current = nums[upper]
				targetIndex = upper

			# Next iteration
			lower += 1
			upper -= 1

		# Corner case - index is 0
		if index == 0:
			return

		# Swap with the next highest number
		temp = nums[index-1]
		nums[index-1] = nums[targetIndex]
		nums[targetIndex] = temp

		return

s = Solution()

# test 1
a = [1, 2, 3]
print("{} -> ".format(a), end="")
s.nextPermutation(a)
print(a)
assert a == [1,3,2]

# test 2
a = [3, 2, 1]
print("{} -> ".format(a), end="")
s.nextPermutation(a)
print(a)
assert a == [1,2,3]

# test 3
a = [1, 1, 5]
print("{} -> ".format(a), end="")
s.nextPermutation(a)
print(a)
assert a == [1,5,1]

# test 4
a = [1,3,2]
print("{} -> ".format(a), end="")
s.nextPermutation(a)
print(a)
assert a == [2,1,3]

# test 5
a = [3,1,2]
print("{} -> ".format(a), end="")
s.nextPermutation(a)
print(a)
assert a == [3,2,1]

# test 6
a = [0,1,4,2,3]
print("{} -> ".format(a), end="")
s.nextPermutation(a)
print(a)
assert a == [0,1,4,3,2]

# test 7
a = [0,1,4,3,2]
print("{} -> ".format(a), end="")
s.nextPermutation(a)
print(a)
assert a == [0,2,1,3,4]

# test 8
a = [0,1,4,3,2,5]
print("{} -> ".format(a), end="")
s.nextPermutation(a)
print(a)
assert a == [0,1,4,3,5,2]

# test 9
a = [0,3,4,3,2]
print("{} -> ".format(a), end="")
s.nextPermutation(a)
print(a)
assert a == [0,4,2,3,3]

# test 10
a = [10,40,31,32,51,52,20]
print("{} -> ".format(a), end="")
s.nextPermutation(a)
print(a)
assert a == [10,40,31,32,52,20,51]

# test 11
a = [10,40,31,52,33,32,20]
print("{} -> ".format(a), end="")
s.nextPermutation(a)
print(a)
assert a == [10,40,32,20,31,33,52]

# test 12
a = [2,2,7,5,4,3,2,2,1]
print("{} -> ".format(a), end="")
s.nextPermutation(a)
print(a)
assert a == [2,3,1,2,2,2,4,5,7]

# test 13
a = [2,3,1,3,3]
print("{} -> ".format(a), end="")
s.nextPermutation(a)
print(a)
assert a == [2,3,3,1,3]

# test 14
a = [2,3,1,3,3,3,3,3]
print("{} -> ".format(a), end="")
s.nextPermutation(a)
print(a)
assert a == [2,3,3,1,3,3,3,3]

# test 15
a = [2,3,1,3,3,2,3,3]
print("{} -> ".format(a), end="")
s.nextPermutation(a)
print(a)
assert a == [2,3,1,3,3,3,2,3]

# test 16
a = [2,3,1,3,3,3,3,2]
print("{} -> ".format(a), end="")
s.nextPermutation(a)
print(a)
assert a == [2,3,2,1,3,3,3,3]
