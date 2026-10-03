#!/usr/bin/python3

import sys

class Solution:
    def divide(self, dividend, divisor):
        """
        :type dividend: int
        :type divisor: int
        :rtype: int
        """

        result = 0
        maxpositive = 2147483647
        maxnegative = -2147483648

        # Sign
        negative = False
        if ((dividend < 0 and divisor >= 0) or 
        	(divisor < 0 and dividend >= 0)):
        	negative = True

        dividend = abs(dividend)
        divisor = abs(divisor)

        # Divide by 0
        if divisor == 0:
        	result = sys.maxsize

        # Divide by 1
        elif divisor == 1:
        	result = dividend

        else:
	        # Check multiple of 2
        	factor = 0
        	while not (divisor & 1):
        		divisor >>= 1
        		factor += 1
        	dividend >>= factor

        	if divisor == 1:
        		result = dividend

        	else:
        		while (divisor > 0) and (dividend >= divisor):
        			dividend -= divisor
        			result += 1

        if negative:
        	result = -result

        return min(max(maxnegative, result), maxpositive)

d = Solution()

for i in range(20):
	print("{} / 2 = {}".format(i, d.divide(i, 2)))

#print("Divide by 0 : {}".format(int(10/0)))
print("Divide by 0 : {}".format(d.divide(10, 0)))

for i in range(20):
	print("{} / -3 = {}".format(i, d.divide(i, -3)))

# Check leet code 2^32
x = -2147483648
y = -1
print("{} / {} = {}".format(x, y, int(x/y)))

x = 2147483647
y = 2
print("{} / {} = {}".format(x, y, d.divide(x, y)))
