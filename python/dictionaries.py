#!/usr/bin/python3

ddd = dict();
ddd['foo'] = 100;
ddd['bar'] = 200;
ddd['moo'] = 200;
print("Dictionary at start: ", ddd)
ddd['bar'] = 300;
print("Dictionary after modify bar: ", ddd)

d2 = {'a':'Apple', 'b':'Ball', 'c':'Cat'}
print("Another dictionary: ", d2)
for i in d2:
	print("  -> [{}] : {}".format(i, d2[i]))

# Dictionary library
print("Dictionary function list: {}".format(dir(d2)))

# CANNOT Concatenate
d3 = {'x':'Xylophone', 'y':'Yatch', 'z':'Zebra'}
print("YAD: ", d3)
#print("{} + {} = {}".format(d2, d3, d2+d3))

# in / not in
if 'x' in d3:
	print("Value of x in d3: ", d3['x'])
if 'z' not in d2:
	print("z not in d2")

# Lists of keys, values, both
print("List of d2: ", list(d2))
print("Keys of d2: ", d2.keys())
print("Values of d2: ", d2.values())
print("Tuples of d2: ", d2.items())

# Iterate thru key & value
for k, v in d3.items():
	print("=> <{}> : ({})".format(k, v))
	d3[k] = 'Blah'
for k, v in d3.items():
	print("=> <{}> : ({})".format(k, v))

# Count occurance
counts = dict()
fname = input("Enter the file name: ")
try:
	fhandle = open(fname)
except:
	print('Invalid file: ', fname)
	quit()
for line in fhandle:
	for word in line.split():
		counts[word] = counts.get(word, 0) + 1
#for x in counts:
#	print("~~> {} : {}".format(x, counts[x]))
tList = list()
for k, v in counts.items() :
	tList.append((v, k))
tList = sorted(tList, reverse=True)
for v, k in tList:
	print("^^^ {} : {}".format(k, v))
