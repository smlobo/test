/*

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity 
should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5

*/

import java.util.Arrays;

class Median2Arrays {

	private double findMedianSingleArray(int[] n) {
		if (n.length % 2 == 0) {
			return (n[n.length/2-1] + n[n.length/2]) / 2.0;
		}
		else {
			return n[n.length/2];
		}
	}

	private int getConsecutiveArrayValue(int index, int[] n1, int[] n2) {
		if (index < n1.length) {
			return n1[index];
		}
		else {
			return n2[index - n1.length];
		}
	}

	private double findMedianConsecutiveArrays(int[] n1, int[] n2) {
		int length = n1.length + n2.length;

		if (length % 2 == 0) {
			int v1 = getConsecutiveArrayValue(length/2 - 1, n1, n2);
			int v2 = getConsecutiveArrayValue(length/2, n1, n2);
			return (v1 + v2) / 2.0;
		}
		else {
			return getConsecutiveArrayValue(length/2, n1, n2);
		}
	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {

		// nums2 is empty
		if (nums2.length == 0) {
			return findMedianSingleArray(nums1);
		}

		// nums1 is empty
		else if (nums1.length == 0) {
			return findMedianSingleArray(nums2);
		}

		// nums1 <= nums2
		else if (nums1[nums1.length-1] <= nums2[0]) {
			return findMedianConsecutiveArrays(nums1, nums2);
		}

		// nums1 >= nums2
		else if (nums2[nums2.length-1] <= nums1[0]) {
			return findMedianConsecutiveArrays(nums2, nums1);
		}

		// Sequentially scan until the median value
		int length = nums1.length + nums2.length;

		if (length % 2 == 0) {
			int count = 0;
			int i1 = 0;
			int i2 = 0;
			int v1 = 0;
			int v2 = 0;
			while (count <= length/2) {

				// End of nums1
				if (i1 == nums1.length) {
					if (count == length/2) {
						return (v1 + nums2[length/2 - count + i2]) / 2.0;						
					}
					else {
						return (nums2[length/2-1 - count + i2] + 
							nums2[length/2 - count + i2]) / 2.0;
					}
				}

				// End of nums2
				if (i2 == nums2.length) {
					if (count == length/2) {
						return (v1 + nums1[length/2 - count + i1]) / 2.0;
					}
					else {
						return (nums1[length/2-1 - count + i1] + 
							nums1[length/2 - count + i1]) / 2.0;
					}
				}

				if (nums1[i1] < nums2[i2]) {
					v2 = nums1[i1];
					i1++;
				}
				else {
					v2 = nums2[i2];
					i2++;
				}

				if (count == length/2 - 1) {
					v1 = v2;
				}
				count++;
			}
			return (v1 + v2) / 2.0;
		}
		else {
			int count = 0;
			int i1 = 0;
			int i2 = 0;
			int value = 0;
			while (count <= length/2) {

				// End of nums1
				if (i1 == nums1.length) {
					return nums2[length/2 - count + i2];
				}

				// End of nums2
				if (i2 == nums2.length) {
					return nums1[length/2 - count + i1];
				}

				if (nums1[i1] < nums2[i2]) {
					value = nums1[i1];
					i1++;
				}
				else {
					value = nums2[i2];
					i2++;
				}
				count++;
			}
			return value;
		}
	}

	public static void main(String[] args) {
		Median2Arrays x = new Median2Arrays();
		int[] n1, n2;
		double a;

		// test 1
		n1 = new int[] {1, 2};
		n2 = new int[] {3, 4};
		a = x.findMedianSortedArrays(n1, n2);
		System.out.println(Arrays.toString(n1) + " + " + 
			Arrays.toString(n2) + " = " + a);
		assert a == 2.5;

		// test 2
		n1 = new int[] {};
		n2 = new int[] {3, 4};
		a = x.findMedianSortedArrays(n1, n2);
		System.out.println(Arrays.toString(n1) + " + " + 
			Arrays.toString(n2) + " = " + a);
		assert a == 3.5;

		// test 3
		n1 = new int[] {1, 2, 3};
		n2 = new int[] {};
		a = x.findMedianSortedArrays(n1, n2);
		System.out.println(Arrays.toString(n1) + " + " + 
			Arrays.toString(n2) + " = " + a);
		assert a == 2.0;

		// test 4
		n1 = new int[] {1, 2};
		n2 = new int[] {2, 3, 4};
		a = x.findMedianSortedArrays(n1, n2);
		System.out.println(Arrays.toString(n1) + " + " + 
			Arrays.toString(n2) + " = " + a);
		assert a == 2.0;

		// test 5
		n1 = new int[] {1, 2, 4};
		n2 = new int[] {5, 6, 7};
		a = x.findMedianSortedArrays(n1, n2);
		System.out.println(Arrays.toString(n1) + " + " + 
			Arrays.toString(n2) + " = " + a);
		assert a == 4.5;

		// test 6
		n1 = new int[] {1, 2, 4, 5};
		n2 = new int[] {6, 7};
		a = x.findMedianSortedArrays(n1, n2);
		System.out.println(Arrays.toString(n1) + " + " + 
			Arrays.toString(n2) + " = " + a);
		assert a == 4.5;

		// test 7
		n1 = new int[] {1, 4, 6};
		n2 = new int[] {2, 3};
		a = x.findMedianSortedArrays(n1, n2);
		System.out.println(Arrays.toString(n1) + " + " + 
			Arrays.toString(n2) + " = " + a);
		assert a == 3.0;

		// test 8
		n1 = new int[] {1, 4, 6, 7};
		n2 = new int[] {2, 3, 5};
		a = x.findMedianSortedArrays(n1, n2);
		System.out.println(Arrays.toString(n1) + " + " + 
			Arrays.toString(n2) + " = " + a);
		assert a == 4.0;

		// test 9
		n1 = new int[] {1, 3};
		n2 = new int[] {2};
		a = x.findMedianSortedArrays(n1, n2);
		System.out.println(Arrays.toString(n1) + " + " + 
			Arrays.toString(n2) + " = " + a);
		assert a == 2.0;

		// test 10
		n1 = new int[] {1, 4};
		n2 = new int[] {2, 3};
		a = x.findMedianSortedArrays(n1, n2);
		System.out.println(Arrays.toString(n1) + " + " + 
			Arrays.toString(n2) + " = " + a);
		assert a == 2.5;

		// test 11
		n1 = new int[] {1, 4, 5};
		n2 = new int[] {2, 3, 6};
		a = x.findMedianSortedArrays(n1, n2);
		System.out.println(Arrays.toString(n1) + " + " + 
			Arrays.toString(n2) + " = " + a);
		assert a == 3.5;

		// test 12
		n1 = new int[] {1, 4, 5, 6, 7, 8};
		n2 = new int[] {2, 3};
		a = x.findMedianSortedArrays(n1, n2);
		System.out.println(Arrays.toString(n1) + " + " + 
			Arrays.toString(n2) + " = " + a);
		assert a == 4.5;

		// test 13
		n1 = new int[] {1, 4, 5, 6, 7};
		n2 = new int[] {1, 2, 3};
		a = x.findMedianSortedArrays(n1, n2);
		System.out.println(Arrays.toString(n1) + " + " + 
			Arrays.toString(n2) + " = " + a);
		assert a == 3.5;

		// test 14
		n1 = new int[] {1, 2, 3};
		n2 = new int[] {1, 4, 5, 6, 7};
		a = x.findMedianSortedArrays(n1, n2);
		System.out.println(Arrays.toString(n1) + " + " + 
			Arrays.toString(n2) + " = " + a);
		assert a == 3.5;

		// test 14
		n1 = new int[] {2};
		n2 = new int[] {1, 3, 4, 5};
		a = x.findMedianSortedArrays(n1, n2);
		System.out.println(Arrays.toString(n1) + " + " + 
			Arrays.toString(n2) + " = " + a);
		assert a == 3.0;

	}
}