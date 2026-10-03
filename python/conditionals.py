#!/usr/bin/python3

x = -1
while x < 0:
	try:
		x = int(input('Enter a +ve integer: '));
	except:
		x = -1

if x <= 5:
	print('{} <= 5'.format(x));
	print('still in if')
elif x >= 10:
	print('{} >= 10'.format(x));
	print("still in elif")
else:
	print('{} is somewhere in between'.format(x));
	print("still in else")

for i in range(10):
	if i > 2:
		print('{} is > 2'.format(i))
	else:
		print("Done with {}".format(i))
print('Goodbye')
