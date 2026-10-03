#!/usr/bin/python3

"""
Given a string containing digits from 2-9 inclusive, return all possible 
letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given 
below. Note that 1 does not map to any letters.
	2 - abc
	3 - def
	4 - ghi
	5 - jkl
	6 - mno
	7 - pqrs
	8 - tuv
	9 - wxyz

Example:
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note:
Although the above answer is in lexicographical order, your answer could 
be in any order you want.
"""

class Solution:

	keyPadMap = ["", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"]

	def generateNext(self, digits, cArray, currentStr, i):
		# Recursion end condition (phone number end)
		if i >= len(digits):
			cArray.append(currentStr)
			return

		# Iterate over all combinations of this number
		for p in self.keyPadMap[int(digits[i])]:
			self.generateNext(digits, cArray, currentStr + p, i + 1)


	def letterCombinations(self, digits: 'str') -> 'List[str]':
		combinations = list()

		# Stupid corner case - empty string
		if len(digits) == 0:
			return combinations

		self.generateNext(digits, combinations, "", 0)

		return combinations

s = Solution()

# test 1
a = "23"
b = s.letterCombinations(a)
print("{} -> {}".format(a, b))
assert b == ['ad', 'ae', 'af', 'bd', 'be', 'bf', 'cd', 'ce', 'cf']

# test 2
a = "234"
b = s.letterCombinations(a)
print("{} -> {}".format(a, b))
assert b == ['adg', 'adh', 'adi', 'aeg', 'aeh', 'aei', 'afg', 'afh', 'afi', 'bdg', 'bdh', 'bdi', 'beg', 'beh', 'bei', 'bfg', 'bfh', 'bfi', 'cdg', 'cdh', 'cdi', 'ceg', 'ceh', 'cei', 'cfg', 'cfh', 'cfi']

# test 3
a = "99"
b = s.letterCombinations(a)
print("{} -> {}".format(a, b))
assert b == ['ww', 'wx', 'wy', 'wz', 'xw', 'xx', 'xy', 'xz', 'yw', 'yx', 'yy', 'yz', 'zw', 'zx', 'zy', 'zz']

# test 4
a = "789"
b = s.letterCombinations(a)
print("{} -> {}".format(a, b))
assert b == ['ptw', 'ptx', 'pty', 'ptz', 'puw', 'pux', 'puy', 'puz', 'pvw', 'pvx', 'pvy', 'pvz', 'qtw', 'qtx', 'qty', 'qtz', 'quw', 'qux', 'quy', 'quz', 'qvw', 'qvx', 'qvy', 'qvz', 'rtw', 'rtx', 'rty', 'rtz', 'ruw', 'rux', 'ruy', 'ruz', 'rvw', 'rvx', 'rvy', 'rvz', 'stw', 'stx', 'sty', 'stz', 'suw', 'sux', 'suy', 'suz', 'svw', 'svx', 'svy', 'svz']

# test 5
a = ""
b = s.letterCombinations(a)
print("{} -> {}".format(a, b))
assert b == []