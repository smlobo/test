#!/usr/bin/python3

"""
You are given a sequence whose nth term is:
	Tn = n^2 - (n - 1)^2

You have to evaluate the series:
	Sn = T1 + T2 + ... + Tn

Find:
	Sn mod (10^9 + 7)
Input Format:
The first line of input contains T, the number of test cases. 
Each test case consists of one line containing a single integer n.

Constraints:
* 1 <= T <= 10
* 1 <= n <= 10^16

Output Format:
For each test case, print the required answer in a line.

Sample Input 0:
2
2
1

Sample Output 0:
4
1

Explanation 0:
Case 1: We have 4 = 1 + 3
Case 2: We have 1 = 1
"""

import os
import sys

#
# Complete the summingSeries function below.
#
# Sum of arithmetic series: Sn = n * (T1 + Tn) / 2
# t1 = 1
# tn = 2n - 1
# sn = n.(1 + 2n - 1)/2 = n^2
def summingSeries(n):
	sn = n * n
	return sn % 1000000007

#fptr = open(os.environ['OUTPUT_PATH'], 'w')
t = int(input())

for t_itr in range(t):
	n = int(input())
	result = summingSeries(n)
	print(result)
	#fptr.write(str(result) + '\n')
	#fptr.close()
