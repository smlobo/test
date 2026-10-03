#!/usr/bin/python3

"""
* len >= 5
* first lower case
* 2nd +ve digit
* 3rd not lower case
* 4th not upper case
* 5th upper case
"""

import re

Regex_Pattern = r'^[a-z][1-9][^a-z][^A-Z][A-Z].*'

answer = str(bool(re.search(Regex_Pattern, input()))).lower()
print(answer)

assert answer == input()

