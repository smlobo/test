#!/usr/bin/python3

"""
Given a collection of intervals, merge all overlapping intervals.

Example 1:
Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into 
[1,6].

Example 2:
Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

"""

class Solution:
	def merge(self, intervals: 'List[List[int]]') -> 'List[List[int]]':
		intervals.sort()
		merge = list()

		for i in range(len(intervals)):

			if i == 0:
				previous = intervals[i]

			else:
				current = intervals[i]

				# Overlapping
				if previous[1] >= current[0]:
					if previous[1] < current[1]:
						previous[1] = current[1]

				# Not overlapping
				else:
					merge.append(previous)
					previous = current

			if i == len(intervals) - 1:
				merge.append(previous)

		return merge

s = Solution()

# test 1
interval = [[1,3], [2,6], [8,10], [15,18]]
print("{} -> ".format(interval), end="")
merge = s.merge(interval)
print(merge)
assert merge == [[1, 6], [8, 10], [15, 18]]

# test 2
interval = [[15,18], [1,3], [2,6], [8,10]]
print("{} -> ".format(interval), end="")
merge = s.merge(interval)
print(merge)
assert merge == [[1, 6], [8, 10], [15, 18]]

# test 3
interval = [[1,4],[4,5]]
print("{} -> ".format(interval), end="")
merge = s.merge(interval)
print(merge)
assert merge == [[1, 5]]

# test 4
interval = [[1,4],[2,3],[1,5]]
print("{} -> ".format(interval), end="")
merge = s.merge(interval)
print(merge)
assert merge == [[1,5]]

# test 5
interval = [[7,18],[1,2],[-1,-1],[6,6],[6,7],[-10,1]]
print("{} -> ".format(interval), end="")
merge = s.merge(interval)
print(merge)
assert merge == [[-10,2],[6,18]]

