#!/usr/bin/python3

t1 = ('Curly', 'Moe', 'Larry')
for i in range(len(t1)):
	print("[{}] {}".format(i, t1[i]))
print("Max: {}; Min: {}".format(max(t1), min(t1)))

# Tuples are immutable
#t1[1] = 'Sally'

# Tuple library
print("Tuple function list: {}".format(dir(t1)))

# Variable assignment
(x,y) = (3.14, "Fred")
print('x = {}, y = {}'.format(x, y))

# Compare
t2 = (0, 1, 200)
t3 = (0, 2, 3)
if t2 < t3:
	print("{} < {}".format(t2, t3))

# Sorting dictionary
d1 = {'b':22, 'f':11, 'd':6}
print("d1 items: ", d1.items())
print("d1 items reverse sorted: ", sorted(d1.items(), reverse=True))
for p, q in sorted(d1.items()):
	print("[{}] ... {}".format(p, q))

# Sort by value
tmp = list()
for k, v in d1.items() :
	tmp.append((v, k))
print("Original order list of tuples: ", tmp)
print("Sorted order list of tuples: ", sorted(tmp))
