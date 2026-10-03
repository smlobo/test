#!/usr/bin/python3

print('First few primes')
for i in [1,2,3,5,7,11,13,17,19]:
	print('\t{}'.format(i))

pals = ['Tom', 'Bob', 'Jack']
pals.append('New')
for pal in pals:
	print('  Hi {}'.format(pal))
print('Done')

# is 
smallest = None
for value in [100, 90, 80, 55, 70, 60, 50]:
	if smallest is None:
		smallest = value
	elif smallest > value:
		smallest = value
	print('-> value is: {}; smallest is: {}'.format(value, smallest))
print('~~~> Final: {}'.format(smallest))
