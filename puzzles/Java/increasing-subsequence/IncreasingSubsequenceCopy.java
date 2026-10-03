public class IncreasingSubsequenceCopy {

    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = 0, j = size;
            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }
            tails[i] = x;
            if (i == size) ++size;
        }
        return size;
    }
    
    public static void main(String[] args) {
        //int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18};
        //int[] nums = {2, 2};
        int[] nums = {1, 3, 6, 7, 9, 4, 10, 5, 6};

        IncreasingSubsequenceCopy is = new IncreasingSubsequenceCopy();
        System.out.println("Solution: " + is.lengthOfLIS(nums));
    }
}
