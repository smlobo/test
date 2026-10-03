#!/usr/bin/python3

"""

A virus is spreading rapidly, and your task is to quarantine the infected 
area by installing walls.

The world is modeled as a 2-D array of cells, where 0 represents uninfected 
cells, and 1 represents cells contaminated with the virus. A wall (and only 
one wall) can be installed between any two 4-directionally adjacent cells, on 
the shared boundary.

Every night, the virus spreads to all neighboring cells in all four 
directions unless blocked by a wall. Resources are limited. Each day, you 
can install walls around only one region -- the affected area (continuous 
block of infected cells) that threatens the most uninfected cells the 
following night. There will never be a tie.

Can you save the day? If so, what is the number of walls required? If not, 
and the world becomes fully infected, return the number of walls used.
"""

import copy

class Solution:

	grid = None
	marked = None

	def containVirus(self, grid):
		"""
		:type grid: List[List[int]]
		:rtype: int
		"""

		# Cache
		self.grid = grid

		# doors used so far
		doors = 0

		# loop over each day simulation
		#for i in range(1):
		while True:

			printGrid(self.grid)
			print("********* NEW DAY ********")

			# Check if the virus is everywhere
			if self.allVirus():
				printGrid(self.grid)
				print("All virus!!!")
				break

			# For DFS - blank grid
			s.blankGrid()

			# Identify regions
			regions = []
			mostDoors = s.findRegions(regions)

			# Virus contained!!
			if mostDoors == 0:
				print("### CONTAINED ###")
				break

			doors += mostDoors

			# Iterate over regions to setup the grid for the next day
			for region in regions:
				(rDoors, rGrid) = region
				#print("Iterate regions: {}".format(rDoors))

				# Contained area - setup grid with contained marker (2)
				if rDoors == mostDoors:
					self.copyContainedArea(rGrid)
				else:
					self.copyNewInfection(rGrid)

		return doors

	def findRegion(self, i, j, rGrid):
		doors = 0

		if self.marked[i][j]:
			return doors

		self.marked[i][j] = True

		# Down
		if (i+1) < len(self.grid):
			if self.grid[i+1][j] == 1:
				doors += self.findRegion(i+1, j, rGrid)
			elif self.grid[i+1][j] == 0:
				doors += 1
				rGrid[i+1][j] = 1

		# Right
		if (j+1) < len(self.grid[0]):
			if self.grid[i][j+1] == 1:
				doors += self.findRegion(i, j+1, rGrid)
			elif self.grid[i][j+1] == 0:
				doors += 1
				rGrid[i][j+1] = 1

		# Up
		if (i-1) >= 0:
			if self.grid[i-1][j] == 1:
				doors += self.findRegion(i-1, j, rGrid)
			elif self.grid[i-1][j] == 0:
				doors += 1
				rGrid[i-1][j] = 1

		# Left
		if (j-1) >= 0:
			if self.grid[i][j-1] == 1:
				doors += self.findRegion(i, j-1, rGrid)
			elif self.grid[i][j-1] == 0:
				doors += 1
				rGrid[i][j-1] = 1

		# cell previously infected - mark in case walled off
		rGrid[i][j] = 2

		return doors

	def findRegions(self, regions):
		mostDoors = 0

		for i in range(len(self.grid)):
			for j in range(len(self.grid[i])):
				if self.grid[i][j] == 1 and not self.marked[i][j]:
					rGrid = copy.deepcopy(self.grid)
					doors = self.findRegion(i, j, rGrid)
					if doors > mostDoors:
						mostDoors = doors
					regions.append((doors, rGrid))
					#printGrid(rGrid)
					print("Found region with {} doors (most: {})".
						format(doors, mostDoors))

		return mostDoors

	def copyContainedArea(self, rGrid):
		#printGrid(rGrid)
		#print("Above is contained")
		for i in range(len(self.grid)):
			for j in range(len(self.grid[i])):
				if rGrid[i][j] == 2:
					self.grid[i][j] = 2

	def copyNewInfection(self, rGrid):
		#printGrid(rGrid)
		#print("Above is infected")
		for i in range(len(self.grid)):
			for j in range(len(self.grid[i])):
				if rGrid[i][j] == 1 and self.grid[i][j] != 2:
					self.grid[i][j] = 1

	def allVirus(self):
		for i in range(len(self.grid)):
			for j in range(len(self.grid[i])):
				if self.grid[i][j] == 0:
					return False
		return True

	def blankGrid(self):
		self.marked = [[False for j in self.grid[0]] for i in self.grid]

def printGrid(g):
	for i in range(len(g)):
		print("{}".format(g[i]))

s = Solution()

# test
g = [[0,1,0,0,0,0,0,1],
	 [0,1,0,0,0,0,0,1],
	 [0,0,0,0,0,0,0,1],
	 [0,0,0,0,0,0,0,0]]
doors = s.containVirus(g)
printGrid(g)
print("Test #1 uses {} doors".format(doors))
assert doors == 10

# test 2
g = []
doors = s.containVirus(g)
printGrid(g)
print("Test #2 uses {} doors".format(doors))
assert doors == 0

# test 3
g = [[1,1,1],
	 [1,0,1],
	 [1,1,1]]
doors = s.containVirus(g)
printGrid(g)
print("Test #3 uses {} doors".format(doors))
assert doors == 4

# test 4
g = [[1,1,1,0,0,0,0,0,0],
	 [1,0,1,0,1,1,1,1,1],
	 [1,1,1,0,0,0,0,0,0]]
doors = s.containVirus(g)
printGrid(g)
print("Test #4 uses {} doors".format(doors))
assert doors == 13

# test 5
g = [[1,1,0,0,1,0,0,0,0],
	 [1,0,1,0,0,0,1,0,1],
	 [1,1,1,0,0,0,0,0,0]]
doors = s.containVirus(g)
printGrid(g)
print("Test #4 uses {} doors".format(doors))
assert doors == 20

# test 6
g = [[0,1,0,0,0,0,0,1],
	 [0,1,0,0,0,0,0,1],
	 [0,0,0,0,1,0,0,1],
	 [0,1,0,0,0,0,0,0]]
doors = s.containVirus(g)
printGrid(g)
print("Test #6 uses {} doors".format(doors))
assert doors == 21

# test 7
g = [[0,1,0,0,1,1,0,1,0],
	 [0,1,0,0,1,0,0,1,0],
	 [0,0,0,0,1,0,0,1,0]]
doors = s.containVirus(g)
printGrid(g)
print("Test #7 uses {} doors".format(doors))
assert doors == 13

# test 8
g = [[0,1,1,0,1,0,0,1,0,1],
	 [0,1,1,0,1,0,1,1,0,1],
	 [0,1,1,0,1,0,1,1,0,1]]
doors = s.containVirus(g)
printGrid(g)
print("Test #8 uses {} doors".format(doors))
assert doors == 8

# test 8
g = [[1,1,1,0,1,1,0,1,0,1],
	 [1,1,1,0,1,0,1,1,0,1],
	 [1,1,1,0,0,1,1,1,0,1]]
doors = s.containVirus(g)
printGrid(g)
print("Test #8 uses {} doors".format(doors))
assert doors == 8
