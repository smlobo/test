/* Keep track of the median of a large number of values
Naive algo: keep sorting and choose the middle
Better: 2 PQ. MinPQ for the upper numbers, MaxPQ for the lower numbers. 
Add appropriately, balance. peek() (both) to get the median */

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter # of ints: ");
        int n = Integer.parseInt(scanner.nextLine());
        System.out.println("Generating " + n + " numbers");

        int[] array = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(n*10);
        }
        //System.out.println(Arrays.toString(array));

        long timer = System.currentTimeMillis();
        double[] sortMedian = SortMedian.sortMedian(array);
        timer = System.currentTimeMillis() - timer;
        System.out.println("Sort took: " + timer);
        //System.out.println(Arrays.toString(sortMedian));

        timer = System.currentTimeMillis();
        double[] pqMedian = PQMedian.pqMedian(array);
        timer = System.currentTimeMillis() - timer;
        System.out.println("PQ took: " + timer);
        //System.out.println(Arrays.toString(pqMedian));

        verify(sortMedian, pqMedian);
    }

    private static void verify(double[] x, double[] y) {
        assert(x.length == y.length);
        for (int i = 0; i < x.length; i++)
            assert(x[i] == y[i]);
    }
}