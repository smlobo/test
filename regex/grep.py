#!/usr/bin/python3

import sys
import re

if len(sys.argv) != 3:
	print("grep.py <pattern-string> <filename>")
	quit()

try:
	fhandle = open(sys.argv[2])
except:
	print("Not a valid file:", sys.argv[2])
	quit()

tarray = fhandle.read()
blist = re.findall(sys.argv[1], tarray)
for index, word in enumerate(blist):
	print("[{}] {}".format(index, word.replace('\n', ' ')))
