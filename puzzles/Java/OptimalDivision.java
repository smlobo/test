/*

Given a list of positive integers, the adjacent integers will perform the 
float division. For example, [2,3,4] -> 2 / 3 / 4.

However, you can add any number of parenthesis at any position to change 
the priority of operations. You should find out how to add parenthesis to 
get the maximum result, and return the corresponding expression in string 
format. Your expression should NOT contain redundant parenthesis.

Example:

Input: [1000,100,10,2]

Output: "1000/(100/10/2)"

Explanation:
1000/(100/10/2) = 1000/((100/10)/2) = 200
However, the bold parenthesis in "1000/((100/10)/2)" are redundant, 
since they don't influence the operation priority. So you should return 
"1000/(100/10/2)". 

Other cases:
1000/(100/10)/2 = 50
1000/(100/(10/2)) = 50
1000/100/10/2 = 0.5
1000/100/(10/2) = 2

Note:
* The length of the input array is [1, 10].
* Elements in the given array will be in range [2, 1000].
* There is only one optimal division for each test case.

*/

import java.util.Arrays;

public class OptimalDivision {
	private int[] nums;
	private double currentMax;
	private int[] correspondingPermutation;

	private static int permutations(int x) {
		int result = 1;
		for (int i = 2; i <= x; i++) {
			result *= i;
		}
		return result;
	}

	private static void swap(int[] x, int p, int q) {
		int temp = x[p];
		x[p] = x[q];
		x[q] = temp;
	}

	private static void putback(int[] x, int n, int i) {
		int temp = x[x.length-n];
		x[x.length-n] = x[x.length-n+i];
		x[x.length-n+i] = temp;
	}

	private void generateNextPermutation(int[] x, int n) {
		for (int i = 0; i < n; i++) {
			if (n == 1) {
				//System.out.println(Arrays.toString(x));
				calculateDivision(x);
			}
			else {
				generateNextPermutation(x, n-1);
				if (i > 0) {
					putback(x, n, i);
				}
				if (i < (n-1)) {
					swap(x, x.length-n, x.length-n+i+1);
				}
			}
		}
	}

	private class DoubleObject {
		double value;
	}

	private void calculateDivision(int[] x) {
		// Result array
		DoubleObject[] result = new DoubleObject[nums.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = new DoubleObject();
			result[i].value = nums[i];
		}

		// Calculate this permutation division
		for (int b : x) {
			result[b].value = result[b].value / result[b+1].value;
			result[b+1] = result[b];
		}

		int resultIndex = x[x.length-1];
		//System.out.println(Arrays.toString(x) + " -> " + 
		//	result[resultIndex].value);

		// Compare agains previous
		if (correspondingPermutation == null || 
			currentMax < result[resultIndex].value) {
			currentMax = result[resultIndex].value;
			correspondingPermutation = Arrays.copyOf(x, x.length);
		}
	}

	public String optimalDivision(int[] nums) {

		// Corner case
		if (nums.length == 1) {
			return Integer.toString(nums[0]);
		}

		// Revert to the API from the problem
		this.nums = Arrays.copyOf(nums, nums.length);
		currentMax = 0.0;
		correspondingPermutation = null;

		// Permutations of order of division
		int p = nums.length - 1;

		// Initialize permutation array
		int[] pa = new int[p];
		for (int i = 0; i < p; i++) {
			pa[i] = i;
		}

		//int n = permutations(p);

		// Generate all permutations; save the optimal one
		generateNextPermutation(pa, p);

		//System.out.println("Optimal order: " + 
		//	Arrays.toString(correspondingPermutation));

		return generateString();
	}

	private String generateString() {
		// Initialize array of numbers as string builders
		StringBuilder[] result = new StringBuilder[nums.length];
		for (int i = 0; i < nums.length; i++) {
			result[i] = new StringBuilder();
			result[i].append(nums[i]);
		}
		//System.out.println(Arrays.toString(result));

		// Iterate over desired order populating the result array
		for (int i : correspondingPermutation) {
			// Append a / to the left string
			result[i].append("/");

			// If string to the right has been solved a bracket needs to 
			// be added
			if (result[i+1].indexOf("/") > 0) {
				result[i].append("(");
			}
			result[i].append(result[i+1]);
			if (result[i+1].indexOf("/") > 0) {
				result[i].append(")");
			}

			// Store a copy in the denominator location too
			result[i+1] = result[i];

			//System.out.println(i + " complete: " + Arrays.toString(result));
		}

		int resultIndex = correspondingPermutation
			[correspondingPermutation.length-1];
		return result[resultIndex].toString();
	}

	public static void main(String[] args) {
		OptimalDivision s = new OptimalDivision();
		int[] a;
		String r;

		// Util test
		for (int i = 0; i <= 6; i++) {
			//System.out.println(i + "! = " + permutations(i));
		}

		// test 1
		a = new int[] {1000, 100, 10, 2};
		r = s.optimalDivision(a);
		System.out.println(Arrays.toString(a) + ", solution: " + r);
		assert r.equals("1000/(100/10/2)");

		// test 2
		a = new int[] {1000, 1000, 1000, 1000};
		r = s.optimalDivision(a);
		System.out.println(Arrays.toString(a) + ", solution: " + r);
		assert r.equals("1000/(1000/1000/1000)");

		// test 3
		a = new int[] {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
		r = s.optimalDivision(a);
		System.out.println(Arrays.toString(a) + ", solution: " + r);
		assert r.equals("2/(2/2/2/2/2/2/2/2/2)");

		// test 4
		a = new int[] {1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000};
		r = s.optimalDivision(a);
		System.out.println(Arrays.toString(a) + ", solution: " + r);
		assert r.equals("1000/(1000/1000/1000/1000/1000/1000/1000/1000/1000)");

		// test 5
		a = new int[] {10, 2, 1000};
		r = s.optimalDivision(a);
		System.out.println(Arrays.toString(a) + ", solution: " + r);
		assert r.equals("10/(2/1000)");

		// test 6
		a = new int[] {100, 2, 1000, 2, 1000, 2, 1000};
		r = s.optimalDivision(a);
		System.out.println(Arrays.toString(a) + ", solution: " + r);
		assert r.equals("100/(2/1000/2/1000/2/1000)");

		// test 7
		a = new int[] {2};
		r = s.optimalDivision(a);
		System.out.println(Arrays.toString(a) + ", solution: " + r);
		assert r.equals("2");

		// test 8
		a = new int[] {2, 3};
		r = s.optimalDivision(a);
		System.out.println(Arrays.toString(a) + ", solution: " + r);
		assert r.equals("2/3");
	}
}