/*

Given n non-negative integers a1, a2, ..., an , where each represents a 
point at coordinate (i, ai). n vertical lines are drawn such that the two 
endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
 with x-axis forms a container, such that the container contains the most 
 water.

Note: You may not slant the container and n is at least 2.

The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In 
this case, the max area of water (blue section) the container can contain 
is 49.

Example:
Input: [1,8,6,2,5,4,8,3,7]
Output: 49

*/

import java.util.Arrays;
import java.util.Comparator;

class MostWaterNew {

	class Coordinate {
		public int distance;
		public int height;

		public Coordinate(int d, int h) {
			distance = d;
			height = h;
		}

		public String toString() {
			return "[" + distance + "," + height + "]";
		}
	}

	class HeightOrder implements Comparator<Coordinate> {
		public int compare(Coordinate x, Coordinate y) {
			if (x.height != y.height) {
				return x.height - y.height;
			}
			return x.distance - y.distance;
		}
	}

	class DistanceOrder implements Comparator<Coordinate> {
		public int compare(Coordinate x, Coordinate y) {
			return x.distance - y.distance;
		}
	}

	public int maxArea(int[] height) {

		// Create an array of Coordinate
		Coordinate[] coords = new Coordinate[height.length];
		for (int i = 0; i < height.length; i++) {
			coords[i] = new Coordinate(i, height[i]);
		}

		// Sort by height - low to high
		Arrays.sort(coords, new HeightOrder());

		//System.out.println("Height sorted: " + Arrays.toString(coords));

		// Iterate over the sorted coords
		// * find the lowest & highest coordinate
		// * calculate the area
		// * keep track of the highest area
		int highestArea = 0;
		for (int i = 0; i < coords.length - 1; i++) {
			int cDistance = coords[i].distance;
			int cHeight = coords[i].height;

			// Copy the rest of the array for distance sorting
			Coordinate[] dArray = 
				Arrays.copyOfRange(coords, i+1, coords.length);

			// Sort the new array by distance
			Arrays.sort(dArray, new DistanceOrder());

			//System.out.println(coords[i] + " -> " + Arrays.toString(dArray));

			// Area to the left
			int leftMost = dArray[0].distance;
			if (cDistance > leftMost) {
				int area = cHeight * (cDistance - leftMost);
				//System.out.println("      : left = " + area);
				if (area > highestArea) {
					highestArea = area;
				}
			}

			// Area to the right
			int rightMost = dArray[dArray.length-1].distance;
			if (cDistance < rightMost) {
				int area = cHeight * (rightMost - cDistance);
				//System.out.println("      : right = " + area);
				if (area > highestArea) {
					highestArea = area;
				}
			}
		}

		return highestArea;
	}

    public static void main(String[] args) {
		MostWaterNew a = new MostWaterNew();
		int[] b;
		int c;

		// test 1
		b = new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7};
		c = a.maxArea(b);
		System.out.println(Arrays.toString(b) + ", solution: " + c);
		assert c == 49;

		// test 2
		b = new int[] {10, 1, 1, 10};
		c = a.maxArea(b);
		System.out.println(Arrays.toString(b) + ", solution: " + c);
		assert c == 30;

		// test 3
		b = new int[] {10, 100, 1, 1, 1, 100, 10};
		c = a.maxArea(b);
		System.out.println(Arrays.toString(b) + ", solution: " + c);
		assert c == 400;

		// test 4
		b = new int[] {10, 100, 1, 1, 1, 1, 1};
		c = a.maxArea(b);
		System.out.println(Arrays.toString(b) + ", solution: " + c);
		assert c == 10;

		// test 5
		b = new int[] {10, 100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		c = a.maxArea(b);
		System.out.println(Arrays.toString(b) + ", solution: " + c);
		assert c == 11;
    }
}
