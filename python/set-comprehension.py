#!/usr/bin/env python3

import math

nums = list(range(30))
print(nums)

int_sq_roots = {int(math.sqrt(x)) for x in nums}
print(int_sq_roots)