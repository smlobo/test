#!/usr/bin/python3

import sys

class Solution:
    def divide(self, dividend, divisor):
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

        else:
            while dividend >= divisor:
                multiplier = 1
                mdivisor = divisor
                while dividend >= mdivisor:
                    dividend -= mdivisor
                    result += multiplier
                    multiplier <<= 1
                    mdivisor <<= 1

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
y = 3
print("{} / {} = {}".format(x, y, d.divide(x, y)))
