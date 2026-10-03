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

public class OptimalDivisionSimple {

	public String optimalDivision(int[] nums) {
		StringBuilder sb = new StringBuilder(nums.length*5);

		if (nums.length == 1) {
			sb.append(nums[0]);
		}
		else if (nums.length == 2) {
			sb.append(nums[0]);
			sb.append("/");
			sb.append(nums[1]);	
		}
		else {
			for (int i = 0; i < nums.length; i++) {
				sb.append(nums[i]);
				if (i != nums.length-1) {
					sb.append("/");
				}
				if (i == 0) {
					sb.append("(");
				}
			}
			sb.append(")");
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		OptimalDivisionSimple s = new OptimalDivisionSimple();
		int[] a;
		String r;

		// test 1
		a = new int[] {1000, 100, 10, 2};
		r = s.optimalDivision(a);
		System.out.println(Arrays.toString(a) + ", solution: " + r);
		assert r.equals("1000/(100/10/2)");

		// test 2
		a = new int[] {2};
		r = s.optimalDivision(a);
		System.out.println(Arrays.toString(a) + ", solution: " + r);
		assert r.equals("2");

		// test 3
		a = new int[] {2, 3};
		r = s.optimalDivision(a);
		System.out.println(Arrays.toString(a) + ", solution: " + r);
		assert r.equals("2/3");
	}
}