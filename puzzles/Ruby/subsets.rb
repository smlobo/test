#!/usr/bin/ruby

def factorial(x)
	f = 1
	for i in 1..x
		f *= i
	end
	return f
end

def permutations(n, k)
	nByK = 1
	for i in k+1..n
		if (k > 0)
			nByK *= i
		end
	end
	return nByK / factorial(n-k)
end

def generateNPK(nums, k)
	n = nums.length

	# Initialize array for indices
	a = Array.new(k)
	for x in 0..k-1
		a[x] = x
	end

	# Do not need to calculate nPk since iterator below figures out when 
	# all combinations are done
	#n = permutations(nums.length, i)
	#puts("#{nums.length} P #{i} = #{n}")

	# Returned array of arrays
	rArray = Array.new()

	# Iterate until done
	loop do

		# Generate the unique combination setup in the previous iteration
		lArray = Array.new()
		for x in 0..k-1
			lArray.push(nums[a[x]])
		end
		rArray.push(lArray)

		# Reverse iterate to generate the next unique combination
		# For 5P3 we generate 012, 013, 014, 023, ..., 134, 234
		allDone = true
		for x in (0..k-1).to_a.reverse
			# max value of a[x]
			max = n - k + x

			if a[x] != max
				a[x] += 1
				for y in x+1..k-1
					a[y] = a[y-1] + 1
				end
				allDone = false
				break
			end

			# Implies all array k elements are at max - we are done
		end

		break if allDone
	end

	return rArray
end

# @param {Integer[]} nums
# @return {Integer[][]}
def subsets(nums)
    rArray = Array.new()

    # Iterate over set length - null to all elements (nP0 to nPn)
    (0..nums.length).each do |k|
    	tArray = generateNPK(nums, k)
    	for j in tArray
	    	rArray.push(j)
	    end
    end

    return rArray
end

def printSet(set, indent)
	(0..indent).each do |j|
		print " "
	end
	print "["
	for i in 0..set.length-1
		print("#{set[i]}")
		if i != set.length-1
			print(", ")
		end
	end
	print "]"
end

def printSubsets(ssets)
	puts("[")
	for set in ssets
		printSet(set, 2)
		puts(",")
	end
	puts("]")
end

s1 = [2, 4, 6]
print "Subset for Set: "
printSet(s1, 0)
puts()
ss1 = subsets(s1)
printSubsets(ss1)

s2 = [1, 2, 3, 4]
print "Subset for Set: "
printSet(s2, 0)
puts()
ss2 = subsets(s2)
printSubsets(ss2)

s3 = [1, 2, 3, 4, 5]
print "Subset for Set: "
printSet(s3, 0)
puts()
ss3 = subsets(s3)
printSubsets(ss3)
