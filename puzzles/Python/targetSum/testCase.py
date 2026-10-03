#!/usr/bin/python3
	
def main(s):

	# test 1
	n = [1, 1, 1, 1, 1]
	t = 3
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 5

	# test 2
	n = [1, 2, 3, 4]
	t = 5
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 0

	# test 3
	n = [1, 2, 3, 4]
	t = 4
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 2
	
	# test 4
	n = [27,22,39,22,40,32,44,45,46,8,8,21,27,8,11,29,16,15,41,0]
	t = 10
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 0
	
	# test 5
	n = [31,4,45,3,44,49,28,6,22,24,40,25,13,46,17,10,2,38,25,15]
	t = 25
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 6290
	
	# test 6
	n = [1,0]
	t = 1
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 2
	
	# test 7
	n = [11,31,37,36,43,40,50,18,10,15,10,35,43,25,41,43,6,22,38,38]
	t = 44
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 5381
	
	# test 8
	n = [11, 0, 0]
	t = 11
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 4
	
	# test 9
	n = [11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
	t = 11
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 1024 * 512
	
	# test 10
	n = [10,1,1]
	t = 10
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 2
	
	# test 11
	n = [10,1,1,1,1]
	t = 10
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 6
	
	# test 12
	n = [10,1,1,1]
	t = 10
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 0
	
	# test 13
	n = [10,1,1,1,1,1,1]
	t = 10
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 20
	
	# test 14
	n = [11,1,1,1,1,1,1,1,1]
	t = 11
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 70
	
	# test 15
	n = [12,1,1,1,1,1,1,1,1,1,1]
	t = 12
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 252
	
	# test 16
	n = [12,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9]
	t = 12
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 0

	# test 17
	n = [12,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9]
	t = 12
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 48620

	# test 18
	n = [10,1,1,1,1]
	t = 9
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 0

	# test 19
	n = [41,6,16,24,31,40,44,22,15,9,29,3,31,10,50,44,39,47,45,47]
	t = 39
	a = s.findTargetSumWays(n, t)
	print("{} + {} -> {}".format(n, t, a))
	assert a == 5294
