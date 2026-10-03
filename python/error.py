#!/usr/bin/python3

def validInteger(msg):
	cmsg = 'Enter'
	x = None
	while x == None:
		try:
			x = int(input('{} a {} integer: '.format(cmsg, msg)));
		except:
			x = None
			cmsg = 'Entry not valid, re-enter'
	return x

a = validInteger("first")
b = validInteger("second")

def max(x, y)::
	if (x >= y):
		return x
	else:
		return y

print('Max of {} & {} = {}'.format(a, b, max(a, b)))


