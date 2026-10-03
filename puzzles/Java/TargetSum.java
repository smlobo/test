/*
You are given a list of non-negative integers, a1, a2, ..., an, and a 
target, S. Now you have 2 symbols + and -. For each integer, you should 
choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to 
target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5

Explanation: 
-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.

Note:
* The length of the given array is positive and will not exceed 20.
* The sum of elements in the given array will not exceed 1000.
* Your output answer is guaranteed to be fitted in a 32-bit integer.
*/

import java.util.Arrays;

class TargetSum {

	private int doSearch(int[] nums, int t, int i) {
		// End condition
		if (i == nums.length)
			if (	t == 0)
				return 1;
			else
				return 0;

		// Array index is assigned +ve
		int plusPath = doSearch(nums, t - nums[i], i + 1);

		// Array index is assigned -ve
		int minusPath = doSearch(nums, t + nums[i], i + 1);

		return plusPath + minusPath;
	}

	public int findTargetSumWays(int[] nums, int S) {
    	return doSearch(nums, S, 0);    
    }

    public static void main(String[] args) {

    	TargetSum s = new TargetSum();
    	int[] n;
    	int t, a;

		// test 1
		n = new int [] {1, 1, 1, 1, 1};
		t = 3;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 5;

		// test 2
		n = new int[] {1, 2, 3, 4};
		t = 5;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 0;

		// test 3
		n = new int[] {1, 2, 3, 4};
		t = 4;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 2;
	
		// test 4
		n = new int[] {27,22,39,22,40,32,44,45,46,8,8,21,27,8,11,29,16,15,41,0};
		t = 10;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 0;
	
		// test 5
		n = new int[] {31,4,45,3,44,49,28,6,22,24,40,25,13,46,17,10,2,38,25,15};
		t = 25;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 6290;
	
		// test 6
		n = new int[] {1,0};
		t = 1;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 2;
	
		// test 7
		n = new int[] {11,31,37,36,43,40,50,18,10,15,10,35,43,25,41,43,6,22,38,38};
		t = 44;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 5381;
	
		// test 8
		n = new int[] {11, 0, 0};
		t = 11;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 4;
	
		// test 9
		n = new int[] {11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		t = 11;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 1024 * 512;
	
		// test 10
		n = new int[] {10,1,1};
		t = 10;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 2;
	
		// test 11
		n = new int[] {10,1,1,1,1};
		t = 10;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 6;
	
		// test 12
		n = new int[] {10,1,1,1};
		t = 10;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 0;
	
		// test 13
		n = new int[] {10,1,1,1,1,1,1};
		t = 10;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 20;
	
		// test 14
		n = new int[] {11,1,1,1,1,1,1,1,1};
		t = 11;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 70;
	
		// test 15
		n = new int[] {12,1,1,1,1,1,1,1,1,1,1};
		t = 12;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 252;
	
		// test 16
		n = new int[] {12,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9};
		t = 12;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 0;

		// test 17
		n = new int[] {12,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9};
		t = 12;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 48620;

		// test 18
		n = new int[] {10,1,1,1,1};
		t = 9;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 0;

		// test 19
		n = new int[] {41,6,16,24,31,40,44,22,15,9,29,3,31,10,50,44,39,47,45,47};
		t = 39;
		a = s.findTargetSumWays(n, t);
		System.out.println(Arrays.toString(n) + " + " + t + " = " + a);
		assert a == 5294;
	}
}
