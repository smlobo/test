#!/usr/bin/python3

'''
A valid credit card from ABCD Bank has the following characteristics: 

► It must start with a 4, 5, or 6. 
► It must contain exactly 16 digits. 
► It must only consist of digits (0-9). 
► It may have digits in groups of 4, separated by one hyphen "-". 
► It must NOT use any other separator like ' ' , '_', etc. 
► It must NOT have 4 or more consecutive repeated digits.

Examples:

Valid Credit Card Numbers
4253625879615786
4424424424442444
5122-2368-7954-3214

Invalid Credit Card Numbers
42536258796157867       #17 digits in card number → Invalid 
4424444424442444        #Digits are repeating 4 or more times → Invalid
5122-2368-7954 - 3214   #Separators other than '-' are used → Invalid
44244x4424442444        #Contains non digit characters → Invalid
0525362587961578        #Doesn't start with 4, 5 or 6 → Invalid
'''

import sys
import re

n = int(sys.stdin.readline())

for i in range(n):
	ccn = sys.stdin.readline()
	#print(ccn)
	# Main match:
	# * starts with 4, 5, 6
	# * may have 1 hyphen between groups of 4
	m = re.match('[456]\d{3}(-?\d{4}){3}$', ccn)
	#m = re.match('[456][0-9]{3}(-?[0-9]{4}){3}$', ccn)
	if m:
		# Secondary match:
		# * No 4 or more repeating digits
		noHyphen = ccn.replace('-', '')
		#print(noHyphen)
		#for i in range(len(noHyphen)-4):
		#	print("{} {}".format(i, noHyphen[i]))
		invalid = False
		for i in range(10):
			#pattern = '{}\{4\}'.format(i)
			pattern = str(i) + '{4}'
			#p2 = r'{}'.format(pattern)
			#print("{} -> {}, {}".format(i, pattern, p2))
			m2 = re.search(pattern, noHyphen)
			if m2:
				print("Invalid")
				invalid = True
				break
		if not invalid:
			print("Valid")
	else:
		print("Invalid")