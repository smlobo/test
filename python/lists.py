#!/usr/bin/python3

l1 = ['red', 3.1415, 22, ['green', 7], 'yellow']
print(l1)
for i in l1:
    print(i)
    if (type(i) is list):
        for j in i:
            print('\t', j)

# Range
x = range(11)
print(x)
for i in x:
    print("x[{}] = {}".format(i, x[i]))

# Concatenate
a = [1, 3, 5]
b = [2, 4, 6]
c = a + b
print("{} + {} = {}".format(a, b, c))
print("c[3] = {}, size = {}".format(c[3], len(c)))

# Slice
print("First 3: {}".format(c[:3]))
print("Last 3: {}".format(c[len(c)-3:]))
print("Middle 3: {}".format(c[2:5]))

# Functions that take lists
print("Max: {}, Min: {}, Sum: {}, Average: {}".format(max(c), min(c), sum(c),
    sum(c)/len(c)))

# List library
print("List function list: {}".format(dir(a)))

# Build a list
stuff = list()
stuff.append(99)
stuff.append('shoe')
print("stuff after append: ", stuff)
stuff.pop()
print("stuff after pop: ", stuff)

# in / not in
if 99 in stuff:
	print("99 is in stuff")
if 999 not in stuff:
	print("999 is not in stuff")

stuff.append('foo')
print("stuff before reverse: ", stuff)
stuff.reverse()
print("stuff after reverse: ", stuff)

# String to list
myStr = "it was the best of times"
myList = myStr.split()
for myWord in myList:
	print("  ", myWord)
myNewStr = "you,fill,up,my,senses"
myList = myNewStr.split(",")
for myWord in myList:
	print("- ", myWord)
