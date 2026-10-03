#!/usr/bin/python3

fname = input('Enter the text file name: ');
try:
	fhandle = open(fname)
except:
	print('File {} cannot be opened.'.format(fname))
	quit()

lcount = 0
for line in fhandle:
	lcount = lcount + 1
print('{} has {} lines'.format(fhandle.name, lcount))

fhandle.close()

fhandle = open(fname)
tarray = fhandle.read()
print("{} has {} characters".format(fname, len(tarray)))
print("First 20 chars:\n{}".format(tarray[:20]))

fhandle.close()
