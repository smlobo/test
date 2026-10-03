#!/usr/bin/python3

"""
You are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays where the 
product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: 
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not 
strictly less than k.

Note:
0 < nums.length <= 50000.
0 < nums[i] < 1000.
0 <= k < 10^6.
"""

class Solution:
    def numSubarrayProductLessThanK(self, nums, k):

        result = 0

        # corner case k == 0
        if k == 0:
            return 0

        # corner case - all ones
        allOnes = True
        for i in nums:
            if i > 1:
                allOnes = False
                break
        if allOnes and k > 1:
            n = len(nums)
            return int(n*(n+1)/2)

        print("no corner case")

        # copy to a new (product) array
        pnums = list(nums)

        # Loop for multiply offset
        for i in range(len(pnums)):

            #print("Iteration {} : {} -> k = {}".format(i, result, k))

            temp = result

            # Loop for multiplying and checking less than
            r = len(pnums) - i
            for j in range(r):

                # Already >= k, skip
                if pnums[j] >= k or nums[j+i] >= k:
                    continue

                # First iteration is special
                if i != 0 and (pnums[j] != 1 or nums[j+i] != 1):
                    pnums[j] = pnums[j] * nums[j+i]

                if (pnums[j] < k):
                    result += 1

                #print("\t[{}] [{}] {}, result={}".format(i, j, pnums[j], result))

            if result == temp:
                break

        return result

s = Solution()

a = [10, 5, 2, 6]
k = 100
print("{} with {} = {}".format(a, k, s.numSubarrayProductLessThanK(a, k)))

a = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
k = 2
print("{} with {} = {}".format(a, k, s.numSubarrayProductLessThanK(a, k)))

a = [10,3,3,7,2,9,7,4,7,2,8,6,5,1,5]
k = 30
print("{} with {} = {}".format(a, k, s.numSubarrayProductLessThanK(a, k)))

a = [9,4,3,2,6,2,5,4,2,6,7]
k = 144
print("{} with {} = {}".format(a, k, s.numSubarrayProductLessThanK(a, k)))
