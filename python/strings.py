#!/usr/bin/env python3

# Index
fruit = 'apple'
for i in range(len(fruit)):
	print('[{}] : {}'.format(i, fruit[i]))

# Range
for i in range(len(fruit)):
	print(fruit[0:i+1])
for i in range(len(fruit)):
	print(fruit[i:len(fruit)])
for i in range(len(fruit)):
	print(fruit[:i+1])
for i in range(len(fruit)):
	print(fruit[i:])

# In
if 'a' in fruit:
	print('a is in {}'.format(fruit))
if 'b' not in fruit:
	print(f'b not in {fruit}')

# Comparison
if 'apple' == fruit:
	print('fruit is an apple')
if 'banana' > fruit:
	print('banana is after {}'.format(fruit))

# String library
print('uppercase of {} is {}'.format(fruit, fruit.upper()))
print('String function list: {}'.format(dir('')))

# Find
ff = 'pl'
print("position of {} in {} is: {}".format(ff, fruit, fruit.find(ff)))
rr = 'qw'
print("replace {} in {} with {} yields: {}".format(ff, fruit, rr, 
	fruit.replace(ff, rr)))

# Strip whitespace
blah = "    qwerty    ";
print("^{}^".format(blah))
print("^{}^".format(blah.lstrip()))
print("^{}^".format(blah.rstrip()))
print(f"^{blah.strip()}^")

# Concatenate
test_concatenate = "foo" + "bar"
print(test_concatenate)
