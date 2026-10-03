#!/usr/bin/env python3

nums = [1, 2, 3, 4, 5, 6]
print(nums)

even_num_to_squares = {x: x ** 2 for x in nums if x % 2 == 0}
print(even_num_to_squares)

x = {"one": 1, "two": 2, "three": 3}
y = {'twenty-'+k: v+20 for k, v in x.items()}
print(f'x = {x}')
print(f'y = {y}')

