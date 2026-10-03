#!/usr/bin/env python3

import re

# Squaring numbers
def square(match):
    number = int(match.group(0))
    return str(number**2)

orig = "1 2 3 4 5 6 7 8 9"
squared = re.sub(r"\d+", square, orig)
print(f"{orig} -> {squared}")
