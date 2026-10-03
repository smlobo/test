#!/usr/bin/python3

import sys
import re

if len(sys.argv) != 3:
	print("regex.py <filename> <starting-char>")
	quit()

try:
	fhandle = open(sys.argv[1])
except:
	print("Not a valid file:", sys.argv[1])
	quit()

schar = sys.argv[2]
if len(schar) != 1 or re.search('[a-z]', schar) is None:
	print("'{}' is not a char".format(schar))
	quit()

print("All words starting with {} in: {}".format(schar, sys.argv[1]))

print("Parse:")
pcounts = dict()
for line in fhandle:
	for word in line.split():
		if schar in word[0]:
			#print("\t:", word)
			pcounts[word] = pcounts.get(word, 0) + 1
if len(pcounts) <= 10:
	for i in pcounts:
		print("\t: {} -> {}".format(i, pcounts[i]))
print("Total {} words by parsing: {}".format(schar, len(pcounts)))

fhandle.seek(0)

print("RegEx:")
tarray = fhandle.read()
recounts = dict()
# The pattern is:
# * a negative lookbehind for anything other than a char (first word in file),
# * the starting char, 
# * zero or more letters, 
# * a lookahead for a white space (so that successive 'schar' words are caught)
pattern = '(?<![a-z]){}[a-z]*(?=\s)'.format(schar)
blist = re.findall(pattern, tarray)
for word in blist:
	#print("\t#", w[1:len(w)-1])
	recounts[word] = recounts.get(word, 0) + 1
if len(recounts) <= 10:
	for i in recounts:
		print("\t: {} -> {}".format(i, recounts[i]))
print("Total {} words by regex: {}".format(schar, len(recounts)))

fhandle.close()

# Check if the 2 dictionaries match
if pcounts != recounts:
	if len(pcounts) != len(recounts):
		print("Number of {} words from parsing ({}) != regex ({})".
			format(schar, len(pcounts), len(recounts)))
	for i in pcounts:
		if i not in recounts:
			print("Word {} in parse not in regex".format(i))
		elif pcounts[i] != recounts[i]:
			print("Word {} counts differ: parse ({}) != regex({})".
				format(i, pcounts[i], recounts[i]))
	for i in recounts:
		if i not in pcounts:
			print("Word {} in regex not in parse".format(i))
		elif pcounts[i] != recounts[i]:
			print("Word {} counts differ: parse ({}) != regex({})".
				format(i, pcounts[i], recounts[i]))
