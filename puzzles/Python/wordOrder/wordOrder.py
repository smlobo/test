#!/usr/bin/python3

'''
You are given n words. Some words may repeat. For each word, output its number 
of occurrences. The output order should correspond with the input order of 
appearance of the word. See the sample input/output for clarification.

Note: Each input line ends with a "\n" character.

Constraints: 
 
The sum of the lengths of all the words do not exceed  
All the words are composed of lowercase English letters only.

Input Format

The first line contains the integer, n. 
The next n lines each contain a word.

Output Format

Output 2 lines. 
On the first line, output the number of distinct words from the input. 
On the second line, output the number of occurrences for each distinct word 
according to their appearance in the input.

Sample Input
4
bcdef
abcdefg
bcde
bcdef

Sample Output
3
2 1 1
'''

import sys

n = int(sys.stdin.readline())
#print(n)

wordDict = dict()
for i in range(n):
	word = sys.stdin.readline().rstrip()
	#print("{} -> {}".format(i, word))
	if word in wordDict:
		wordDict[word] += 1
	else:
		wordDict[word] = 1

print(len(wordDict))
for i in wordDict.values():
	print("{} ".format(i), end="")
print()
