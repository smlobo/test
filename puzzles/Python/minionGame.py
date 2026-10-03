#!/usr/bin/python3

"""
Kevin and Stuart want to play the 'The Minion Game'.

Game Rules

Both players are given the same string, .
Both players have to make substrings using the letters of the string .
Stuart has to make words starting with consonants.
Kevin has to make words starting with vowels. 
The game ends when both players have made all possible substrings. 

Scoring
A player gets +1 point for each occurrence of the substring in the 
string .

For Example:
String  = BANANA
Kevin's vowel beginning word = ANA
Here, ANA occurs twice in BANANA. Hence, Kevin will get 2 Points. 

Input Format

A single line of input containing the string . 
Note: The string  will contain only uppercase letters: .

Constraints


Output Format

Print one line: the name of the winner and their score separated by a space.

If the game is a draw, print Draw.

Sample Input

BANANA
Sample Output

Stuart 12

"""

import re

def isVowel(c):
	m = re.match(r'[aeiou]', c)
	if m:
		return True
	return False

def minion_game(string):
	kCount = 0
	sCount = 0
	sLength = len(string)

	for i in range(sLength):
		#if isVowel(string[i]):
		#if bool(re.search('[aeiou]', string[i])):
		if re.search('[aeiou]', string[i]):
			kCount += sLength - i;
		else:
			sCount += sLength - i;

	if kCount > sCount:
		print("Kevin {}".format(kCount))
	elif sCount > kCount:
		print("Stuart {}".format(sCount))
	else:
		print("Draw")

if __name__ == '__main__':
	s = input()
	minion_game(s)