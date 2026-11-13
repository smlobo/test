/*
Given a m x n grid filled with non-negative numbers, find a path from top left 
to bottom right, which minimizes the sum of all numbers along its path.
Example:
  1 3 1
  1 5 1
  4 2 1
  Answer = 7

Leet code - memoization was required

Time: Assigning to every element: m*n
      Checking for memoization - come in 2 ways to every square: 2*m*n
      Worst case recursion: m*n 
Memory: m*n more memory
*/

import java.util.Arrays;

public class Solution {
    static int minPathSum(int[][] a, int[][] f, int i, int j) {
        // Memoized value
        if (f[i][j] < Integer.MAX_VALUE)
            return f[i][j];

        // Base case
        if (i == (a.length-1) && j == (a[0].length-1))
            f[i][j] = a[i][j];

        // Cannot go right
        else if (j == (a[0].length-1))
            f[i][j] = a[i][j] + minPathSum(a, f, i+1, j);

        // Cannot go down
        else if (i == (a.length-1))
            f[i][j] = a[i][j] + minPathSum(a, f, i, j+1);

        else
            f[i][j] = a[i][j] + Math.min(minPathSum(a, f, i, j+1), minPathSum(a, f, i+1, j));

        return f[i][j];
    }

    static int minPathSum(int[][] grid) {
        int[][] fGrid = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            fGrid[i] = new int[grid[i].length];
            for (int j = 0; j < grid[i].length; j++) {
                fGrid[i][j] = Integer.MAX_VALUE;
            }
        }
        return minPathSum(grid, fGrid, 0, 0);
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {
            {1, 3, 1}, 
            {1, 5, 1},
            {4, 2, 1}
        };
        int s = minPathSum(a);
        System.out.println(Arrays.deepToString(a) + " = " + s);

        a = new int[][] {
            {1, 2, 3}, 
            {4, 5, 6}
        };
        s = minPathSum(a);
        System.out.println(Arrays.deepToString(a) + " = " + s);
    }
}