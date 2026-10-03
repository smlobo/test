#!/usr/bin/python3

"""
Given n non-negative integers representing an elevation map where the 
width of each bar is 1, compute how much water it is able to trap after 
raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. 
In this case, 6 units of rain water (blue section) are being trapped.

Example:
Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

"""

class Solution:

	height = list()
	complete = list()
	ordered = list()

	def findNextLeft(self, i: int) -> int:
		(h,d) = self.ordered[i]

		if self.complete[d] == True:
			return -1

		j = i + 1
		while j < len(self.ordered):
			(hj,dj) = self.ordered[j]
			if dj < d:
				break
			j += 1
		return j

	def findNextRight(self, i: int) -> int:
		(h,d) = self.ordered[i]

		if self.complete[d+1] == True:
			return -1

		j = i + 1
		while j < len(self.ordered):
			(hj,dj) = self.ordered[j]
			if dj > d:
				break
			j += 1
		return j

	def calculateWater(self, left: int, right: int) -> int:
		trapped = 0

		(hi, di) = self.ordered[left]
		(hj, dj) = self.ordered[right]

		h = min(hi, hj)

		di += 1
		while di < dj:
			trapped += (h - self.height[di])
			self.complete[di] = True
			di += 1
		self.complete[di] = True

		#print("{} <-> {} trapped: {}".format(self.ordered[left], self.ordered[right], trapped))
		return trapped

	def trap(self, height: 'List[int]') -> int:

		# Cache the array
		self.height = height

		# Section completed array {i == border of i-1 to i}
		self.complete = [False] * (len(height)+1)
		self.complete[0] = True
		self.complete[len(height)] = True
		#print(self.complete)

		# Array of (height, distance) tuples
		self.ordered = list()
		for i in range(len(height)):
			self.ordered.append((height[i], i))
		self.ordered.sort(reverse=True)
		#print(self.ordered)

		waterTrapped = 0

		# Iterate from highest to lowest
		for i in range(len(self.ordered)):
			(h,d) = self.ordered[i]

			# Look to the left
			lIndex = self.findNextLeft(i)
			if lIndex != -1:
				#print("{} left found: {}".format(self.ordered[i], self.ordered[lIndex]))
				waterTrapped += self.calculateWater(lIndex, i)

			# Look to the right
			rIndex = self.findNextRight(i)
			if rIndex != -1:
				#print("{} right found: {}".format(self.ordered[i], self.ordered[rIndex]))
				waterTrapped += self.calculateWater(i, rIndex)

		return waterTrapped

s = Solution()

# test 1
a = [0,1,0,2,1,0,1,3,2,1,2,1]
b = s.trap(a)
print("{} -> {}".format(a, b))
assert b == 6

# test 2
a = [1,8,6,2,5,4,8,3,7]
b = s.trap(a)
print("{} -> {}".format(a, b))
assert b == 19

# test 3
a = [1,2,3,4,3,2,1,1,1]
b = s.trap(a)
print("{} -> {}".format(a, b))
assert b == 0

# test 4
a = [3,2,1,0,0,0,1,2,3]
b = s.trap(a)
print("{} -> {}".format(a, b))
assert b == 15

# test 5
a = [1,1,1,1,1,1,1]
b = s.trap(a)
print("{} -> {}".format(a, b))
assert b == 0

# test 6
a = [0]
b = s.trap(a)
print("{} -> {}".format(a, b))
assert b == 0

# test 7
a = [22]
b = s.trap(a)
print("{} -> {}".format(a, b))
assert b == 0

# test 8
a = [22,222]
b = s.trap(a)
print("{} -> {}".format(a, b))
assert b == 0

# test 9
a = [222,22,0,22]
b = s.trap(a)
print("{} -> {}".format(a, b))
assert b == 22
