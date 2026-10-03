/*
Given the coordinates of four points in 2D space, return whether the 
four points could construct a square.

The coordinate (x,y) of a point is represented by an integer array with 
two integers.

Example:
Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: True

Note:
* All the input integers are in the range [-10000, 10000].
* A valid square has four equal sides with positive length and four 
  equal angles (90-degree angles).
* Input points have no order.
*/

import java.util.Arrays;

class ValidSquare {

	private int squaredDistance(int[] a, int[] b) {
		int dX = Math.abs(a[0] - b[0]);
		int dY = Math.abs(a[1] - b[1]);
		return dX*dX + dY*dY;
	}

	private boolean isHypotenuse(int h, int s) {
		if (h == s * 2) {
			return true;
		}
		return false;
	}

	private boolean checkAdjacent(int[] o, int[] d, int[] a, int s) {
		// Squared distance from origin to the diagonal point and the 
		// adjacent point
		int diagonal = squaredDistance(o, d);
		int side = squaredDistance(o, a);

		// Check side and diagonal against previously calculated
		if (s == side && diagonal == s*2) {
			return true;
		}

		return false;
	}

	public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {

		// Squared distance between p1 and p2, p3, p4
		int d12 = squaredDistance(p1, p2);
		int d13 = squaredDistance(p1, p3);
		int d14 = squaredDistance(p1, p4);
		//System.out.println("Distances: " + d12 + ", " + d13 + ", " + 
		//	d14);

		// 0 distance
		if (d12 == 0 || d13 == 0 || d14 == 0) {
			return false;
		}

		// 2 & 3 are adjacent to 1
		if (d12 == d13 && d14 == d12*2) {
			return checkAdjacent(p2, p3, p4, d12) && 
				checkAdjacent(p3, p2, p4, d12);
		}

		// 3 & 4 are adjacent to 1
		else if (d13 == d14 && d12 == d13*2) {
			return checkAdjacent(p3, p4, p2, d13) && 
				checkAdjacent(p4, p3, p2, d13);
		}

		// 2 & 4 are adjacent to 1
		else if (d14 == d12 && d13 == d14*2) {
			return checkAdjacent(p2, p4, p3, d14) && 
				checkAdjacent(p4, p2, p3, d14);
		}

		return false;
	}

	public static void main(String[] args) {
		ValidSquare vs = new ValidSquare();
		int[] p1, p2, p3, p4;
		boolean result;

		// test 1
		p1 = new int[] {0,0}; p2 = new int[] {0,1}; 
		p3 = new int[] {1,0}; p4 = new int[] {1,1};
		result = vs.validSquare(p1, p2, p3, p4);
		System.out.println(Arrays.toString(p1) + ":" + 
			Arrays.toString(p2) + ":" + Arrays.toString(p3) + ":" + 
			Arrays.toString(p4) + " = " + result);
		assert result == true;

		// test 2
		p1 = new int[] {0,0}; p2 = new int[] {3,4}; 
		p3 = new int[] {-4,-3}; p4 = new int[] {0,7};
		result = vs.validSquare(p1, p2, p3, p4);
		System.out.println(Arrays.toString(p1) + ":" + 
			Arrays.toString(p2) + ":" + Arrays.toString(p3) + ":" + 
			Arrays.toString(p4) + " = " + result);
		assert result == false;

		// test 3
		p1 = new int[] {2,3}; p2 = new int[] {3,2}; 
		p3 = new int[] {1,2}; p4 = new int[] {2,1};
		result = vs.validSquare(p1, p2, p3, p4);
		System.out.println(Arrays.toString(p1) + ":" + 
			Arrays.toString(p2) + ":" + Arrays.toString(p3) + ":" + 
			Arrays.toString(p4) + " = " + result);
		assert result == true;

		// test 4
		p1 = new int[] {4,3}; p2 = new int[] {6,4}; 
		p3 = new int[] {7,2}; p4 = new int[] {5,1};
		result = vs.validSquare(p1, p2, p3, p4);
		System.out.println(Arrays.toString(p1) + ":" + 
			Arrays.toString(p2) + ":" + Arrays.toString(p3) + ":" + 
			Arrays.toString(p4) + " = " + result);
		assert result == true;

		// test 5
		p1 = new int[] {4,3}; p2 = new int[] {6,4}; 
		p3 = new int[] {6,2}; p4 = new int[] {5,1};
		result = vs.validSquare(p1, p2, p3, p4);
		System.out.println(Arrays.toString(p1) + ":" + 
			Arrays.toString(p2) + ":" + Arrays.toString(p3) + ":" + 
			Arrays.toString(p4) + " = " + result);
		assert result == false;

		// test 6
		p1 = new int[] {0,3}; p2 = new int[] {-2,4}; 
		p3 = new int[] {-1,1}; p4 = new int[] {-3,2};
		result = vs.validSquare(p1, p2, p3, p4);
		System.out.println(Arrays.toString(p1) + ":" + 
			Arrays.toString(p2) + ":" + Arrays.toString(p3) + ":" + 
			Arrays.toString(p4) + " = " + result);
		assert result == true;

		// test 7
		p1 = new int[] {-1,-2}; p2 = new int[] {-4,-1}; 
		p3 = new int[] {-2,-5}; p4 = new int[] {-5,-4};
		result = vs.validSquare(p1, p2, p3, p4);
		System.out.println(Arrays.toString(p1) + ":" + 
			Arrays.toString(p2) + ":" + Arrays.toString(p3) + ":" + 
			Arrays.toString(p4) + " = " + result);
		assert result == true;

		// test 8
		p1 = new int[] {2,1}; p2 = new int[] {2,6}; 
		p3 = new int[] {5,1}; p4 = new int[] {5,6};
		result = vs.validSquare(p1, p2, p3, p4);
		System.out.println(Arrays.toString(p1) + ":" + 
			Arrays.toString(p2) + ":" + Arrays.toString(p3) + ":" + 
			Arrays.toString(p4) + " = " + result);
		assert result == false;

		// test 9
		p1 = new int[] {0,0}; p2 = new int[] {0,0};
		p3 = new int[] {0,0}; p4 = new int[] {0,0};
		result = vs.validSquare(p1, p2, p3, p4);
		System.out.println(Arrays.toString(p1) + ":" + 
			Arrays.toString(p2) + ":" + Arrays.toString(p3) + ":" + 
			Arrays.toString(p4) + " = " + result);
		assert result == false;

		// test 10
		p1 = new int[] {0,1}; p2 = new int[] {1,2};
		p3 = new int[] {0,0}; p4 = new int[] {0,2};
		result = vs.validSquare(p1, p2, p3, p4);
		System.out.println(Arrays.toString(p1) + ":" + 
			Arrays.toString(p2) + ":" + Arrays.toString(p3) + ":" + 
			Arrays.toString(p4) + " = " + result);
		assert result == false;
	}
}