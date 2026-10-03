#!/usr/bin/python3

'''
There is an array of n integers. There are also 2 disjoint sets, M and N, 
each containing m integers. You like all the integers in set A and dislike all 
the integers in set B. Your initial happiness is 0. For each  integer i in the 
array, if i is in A, you add 1 to your happiness. If i in B, you add -1 to your 
happiness. Otherwise, your happiness does not change. Output your final 
happiness at the end.

Note: Since A and B are sets, they have no repeated elements. However, the 
array might contain duplicate elements.

Input Format

The first line contains integers n and m separated by a space. 
The second line contains n integers, the elements of the array. 
The third and fourth lines contain m integers, A and B, respectively.

Output Format

Output a single integer, your total happiness.

Sample Input
3 2
1 5 3
3 1
5 7

Sample Output
1

'''

import sys

# Read n & m
nums = sys.stdin.readline().split(" ")
n = int(nums[0])
m = int(nums[1])
print("n = {}, m = {}".format(n, m))

# Read input array
inputArray = sys.stdin.readline().split(" ")
print(inputArray)
inputArray = list(map(int, inputArray))
print(inputArray)

# Read happy set
happySet = sys.stdin.readline().split(" ")
happySet = set(map(int, happySet))
print(happySet)

# Read sad set
sadSet = sys.stdin.readline().split(" ")
sadSet = set(map(int, sadSet))
print(sadSet)

# Calculate score
score = 0
for n in inputArray:
	if n in happySet:
		score += 1
	if n in sadSet:
		score -= 1
print(score)
