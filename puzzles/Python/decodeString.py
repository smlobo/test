#!/usr/bin/python3

"""
Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string 
inside the square brackets is being repeated exactly k times. Note 
that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white 
spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain 
any digits and that digits are only for those repeat numbers, k. For 
example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
"""

class Solution:

	def decodeNumber(self, s, endIndex):
		# Multi char number
		i = 0
		n = 0
		while i < len(s) and s[i] != '[':
			n = n*10 + int(s[i])
			i += 1
		# chars parsed == loop count. Now at [ char
		endIndex[0] = i
		return n

	def decodeSubString(self, s, endIndex):

		# Return string
		rstr = ""

		# Scan the input string
		j = 0
		while j < len(s):

			# Repeat number - by default 1
			rn = 1

			# Repeat string
			rs = ""

			# Number
			if s[j].isdigit():
				# parse the number until the [
				end = [0]
				n = self.decodeNumber(s[j:], end)
				j += end[0] + 1

				# parse the substring
				end = [0]
				sstr = self.decodeSubString(s[j:], end)

				# add substring to return string 'n' times
				for k in range(n):
					rstr += sstr
				j += end[0]

			# end of this recursive call - return the decode 
			# portion, and the size parsed
			elif s[j] == ']':
				endIndex[0] = j + 1
				return rstr

			# normal char, add to our list and goto next char
			else:
				rstr += s[j]
				j += 1

		return rstr

	def decodeString(self, s):
		return self.decodeSubString(s, [0])

s = Solution()
x = ""

# test 1
a = "3[a]2[bc]"
x = s.decodeString(a)
print("{} == {}".format(a, x))
assert x == "aaabcbc"

# test 2
a = "3[a2[c]]"
x = s.decodeString(a)
print("{} == {}".format(a, x))
assert x == "accaccacc"

# test 3
a = "2[abc]3[cd]ef"
x = s.decodeString(a)
print("{} == {}".format(a, x))
assert x == "abcabccdcdcdef"

# test 4
a = "10[a]2[bc]"
x = s.decodeString(a)
print("{} == {}".format(a, x))
assert x == "aaaaaaaaaabcbc"

# test 5
a = "0[a]2[bc]"
x = s.decodeString(a)
print("{} == {}".format(a, x))
assert x == "bcbc"

# test 6
a = "11[a12[bc2[]]]"
x = s.decodeString(a)
print("{} == {}".format(a, x))
assert x == "abcbcbcbcbcbcbcbcbcbcbcbcabcbcbcbcbcbcbcbcbcbcbcbcabcbcbcbcbcbcbcbcbcbcbcbcabcbcbcbcbcbcbcbcbcbcbcbcabcbcbcbcbcbcbcbcbcbcbcbcabcbcbcbcbcbcbcbcbcbcbcbcabcbcbcbcbcbcbcbcbcbcbcbcabcbcbcbcbcbcbcbcbcbcbcbcabcbcbcbcbcbcbcbcbcbcbcbcabcbcbcbcbcbcbcbcbcbcbcbcabcbcbcbcbcbcbcbcbcbcbcbc"

# test 7
a = "11[a12[bc2[xy]1[z]]]"
x = s.decodeString(a)
print("{} == {}".format(a, x))
assert x == "abcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzabcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzabcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzabcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzabcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzabcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzabcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzabcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzabcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzabcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzabcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyzbcxyxyz"

# test 8
a = "123[a]z"
x = s.decodeString(a)
print("{} == {}".format(a, x))
assert x == "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaz"

# test 9
a = "abc"
x = s.decodeString(a)
print("{} == {}".format(a, x))
assert x == "abc"

# test 10
a = "abc2[d]e3[f]gh4[i]"
x = s.decodeString(a)
print("{} == {}".format(a, x))
assert x == "abcddefffghiiii"
