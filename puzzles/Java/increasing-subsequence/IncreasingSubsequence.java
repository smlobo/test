import java.lang.System;
import java.util.Arrays;
import java.lang.Integer;

public class IncreasingSubsequence {
    private class Pair implements Comparable<Pair> {
        private int value;
        private int index;

        public Pair(int v, int i) {
            value = v;
            index = i;
        }

        public int value() {
            return value;
        }

        public int index() {
            return index;
        }

        public int compareTo(Pair that) {
            if (this.value < that.value())
                return -1;
            else if (this.value > that.value())
                return 1;
            if (this.index < that.index())
                return -1;
            return 1;
        }

        public String toString() {
            return "[" + value + "," + index + "]";
        }
    }

    public int lengthOfLIS(int[] nums) {
        // Solution
        Pair[] pairs = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++)
            pairs[i] = new Pair(nums[i], i);

        Arrays.sort(pairs);
        
        for (int i = 0; i < pairs.length; i++)
            System.out.print(pairs[i].toString() + ": ");
        System.out.println();

        
        int count = 0;
        int high = -1;
        int pvalue = Integer.MIN_VALUE;

        for (int i = 0; i < pairs.length; i++) {
            if (pairs[i].index() > high &&
                pairs[i].value() > pvalue) {
                high = pairs[i].index();
                count++;
                pvalue = pairs[i].value();
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        //int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18};
        //int[] nums = {2, 2};
        int[] nums = {1, 3, 6, 7, 9, 4, 10, 5, 6};

        IncreasingSubsequence is = new IncreasingSubsequence();
        System.out.println("Solution: " + is.lengthOfLIS(nums));
    }
}
