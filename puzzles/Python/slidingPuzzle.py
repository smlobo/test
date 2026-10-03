#!/usr/bin/python3

"""
On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, 
and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and 
swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the 
state of the board is solved. If it is impossible for the state of the board 
to be solved, return -1.
"""

import collections
import copy

class Solution:

	def copyAndSwap(self, board, x1, y1, x2, y2):
		newBoard = copy.deepcopy(board)
		temp = newBoard[y1][x1]
		newBoard[y1][x1] = newBoard[y2][x2]
		newBoard[y2][x2] = temp
		return newBoard

	def getNewBoards(self, board):
		boardList = list()

		# Coordinates of 0
		x0 = -1
		y0 = -1
		for i in [0,1]:
			for j in [0,1,2]:
				if board[i][j] == 0:
					x0 = j
					y0 = i

		# Move up or down
		boardList.append(self.copyAndSwap(board, x0, 0, x0, 1))

		# Move right
		if x0 != 2:
			boardList.append(self.copyAndSwap(board, x0, y0, x0+1, y0))

		# Move left
		if x0 != 0:
			boardList.append(self.copyAndSwap(board, x0, y0, x0-1, y0))

		return boardList

	def slidingPuzzle(self, board):
		"""
		:type board: List[List[int]]
		:rtype: int
		"""

		# String representation of the target board
		target = '[[1, 2, 3], [4, 5, 0]]'

		# Already at the target
		if (repr(board) == target):
			return 0

		# Dictionary to store unique states
		strDict = dict()
		strDict[repr(board)] = False

		# Queue for BFS
		boardQueue = collections.deque()
		boardQueue.append((board, 1))

		# Iterate over the board tuples in the queue
		while (boardQueue):
			current = boardQueue.popleft()
			currentBoard = current[0]
			currentMove = current[1]
			#print("Working on: {}, moves: {}".
			#	format(currentBoard, currentMove))

			# Iterate over all possible new boards
			for newBoard in self.getNewBoards(currentBoard):
				# Reached the target
				if (repr(newBoard) == target):
					return currentMove

				# Unique board
				if (repr(newBoard) not in strDict):
					strDict[repr(newBoard)] = False
					boardQueue.append((newBoard, currentMove+1))

		return -1

s = Solution()

# test
b = [[4,1,2],[5,0,3]]
moves = s.slidingPuzzle(b)
print("{} solved in {} moves".format(b, moves))
assert moves == 5

# test
b = [[1,2,3],[4,0,5]]
moves = s.slidingPuzzle(b)
print("{} solved in {} moves".format(b, moves))
assert moves == 1

# test
b = [[1,2,3],[5,4,0]]
moves = s.slidingPuzzle(b)
print("{} solved in {} moves".format(b, moves))
assert moves == -1

# test
b = [[3,2,4],[1,5,0]]
moves = s.slidingPuzzle(b)
print("{} solved in {} moves".format(b, moves))
assert moves == 14
