/*
Two images A and B are given, represented as binary, square matrices of the 
same size.  (A binary matrix has only 0s and 1s as values.)

We translate one image however we choose (sliding it left, right, up, or 
down any number of units), and place it on top of the other image.  After, 
the overlap of this translation is the number of positions that have a 1 in 
both images.

(Note also that a translation does not include any kind of rotation.)

What is the largest possible overlap?
*/

import java.util.Arrays;

public class ImageOverlap {

	static class DisplacementScore implements Comparable<DisplacementScore> {
		public int displacement;
		public int score;

		public DisplacementScore(int d, int s) {
			displacement = d;
			score = s;
		}

		public String toString() {
			return "{" + displacement + ", " + score + "}";
		}

		// Sort high to low scores
		public int compareTo(DisplacementScore that) {
			return that.score - this.score;
		}
	}

	public static int[] horizontalScore(int[][] x) {
		int[] hX = new int[x.length];
		for (int i = 0; i < x.length; i++) {
			int score = 0;
			for (int j = 0; j < x[i].length; j++)
				score += x[i][j];
			hX[i] = score;
			//System.out.println("[" + i + "] " + score);
		}
		return hX;
	}

	public static int calculateScore(int[][] x, int[][] y, int vD, int hD) {
		int score = 0;

		for (int i = 0; i < x.length; i++) {
			int di = i + vD;
			if (di < 0 || di >= x.length)
				continue;
			for (int j = 0; j < x.length; j++) {
				int dj = j + hD;
				if (dj < 0 || dj >= x.length)
					continue;
				score += (x[di][dj] & y[i][j]);
			}
		}

		return score;
	}

	public static int largestOverlap(int[][] A, int[][] B) {
		// Get horizontal score
		int[] hA = horizontalScore(A);
		int[] hB = horizontalScore(B);

		// Find the highest possible score for each vertical displacement
		int n = 2 * A.length - 1;
		DisplacementScore[] verticalD = new DisplacementScore[n];
		int c = 0;
		for (int d = -(A.length-1); d < A.length; d++) {
			int score = 0;
			for (int i = 0; i < A.length; i++) {
				int di = i + d;
				if (di < 0 || di >= A.length)
					continue;
				//System.out.println("[" + di + ", " + i + "] " + hA[di] + 
				//	", " + hB[i]);
				if (hA[di] < hB[i])
					score += hA[di];
				else
					score += hB[i];
			}
			verticalD[c] = new DisplacementScore(d, score);
			//System.out.println(verticalD[c]);
			c++;
		}

		Arrays.sort(verticalD);
		System.out.println("Sorted vertical displacement scores:");

		int highScore = 0;
		for (int i = 0; i < verticalD.length; i++) {
			System.out.println(verticalD[i]);

			// Current theoritical high < highScore - stop
			if (highScore >= verticalD[i].score)
				return highScore;

			int vD = verticalD[i].displacement;

			// All horizontal displacements
			for (int hD = -(A.length-1); hD < A.length; hD++) {
				int newScore = calculateScore(A, B, vD, hD);
				System.out.println("newScore (" + vD + ", " + hD + "): " + 
					newScore);

				// Score is theoritical max
				if (newScore == verticalD[i].score)
					return newScore;

				// Reset high score
				if (newScore > highScore)
					highScore = newScore;
			}
		}

		return highScore;
	}

	public static void main(String[] args) {
		// Test 1
		int[][] a1 = {{1, 1, 0}, 
		              {0, 1, 0}, 
		              {0, 1, 0}};
		int[][] b1 = {{0, 0, 0}, 
		              {0, 1, 1}, 
		              {0, 0, 1}};
		System.out.println("Result: " + largestOverlap(a1, b1));

		// Test 2
		int[][] a2 = {{1, 1, 1}, 
		              {1, 1, 1}, 
		              {1, 1, 1}};
		int[][] b2 = {{0, 0, 0}, 
		              {0, 1, 1}, 
		              {0, 0, 1}};
		System.out.println("Result: " + largestOverlap(a2, b2));

		// Test 3
		int[][] a3 = {{1, 1, 1}, 
		              {1, 1, 1}, 
		              {1, 1, 1}};
		int[][] b3 = {{0, 1, 1}, 
		              {0, 1, 1}, 
		              {0, 0, 1}};
		System.out.println("Result: " + largestOverlap(a3, b3));

		// Test 4
		int[][] a4 = {{1, 1, 0}, 
		              {1, 0, 1}, 
		              {0, 1, 1}};
		int[][] b4 = {{0, 1, 1}, 
		              {0, 1, 1}, 
		              {0, 0, 1}};
		System.out.println("Result: " + largestOverlap(a4, b4));
	}
}
