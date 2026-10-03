import java.util.Arrays;

public class Solution {
    public static int[] rearrangeArray(int[] nums) {
        // Sort the array
        Arrays.sort(nums);

        // Length
        int n = nums.length;
        int i;

        // Iterate from i = 1 to n-2
        // if neighbor avg == index, swap (i+1) with (i+2)
        for (i = 1; i < n-2; i++) {
            if ((nums[i-1]+nums[i+1])/2.0 == nums[i]) {
                swap(nums, i+1, i+2);
            }
        }

        // for i == n-2 if neighbor average, swap (i) with (i+1)
        i = n-2;
        if ((nums[i-1]+nums[i+1])/2.0 == nums[i]) {
            swap(nums, i, i+1);
        }

        // Iterate from i = n-3 down to 2
        for (i = n-3; i >= 2; i--) {
            if ((nums[i-1]+nums[i+1])/2.0 == nums[i]) {
                swap(nums, i-1, i-2);
            }            
        }

        // for i == 1 if neighbor average, swap (i) with (i-1)
        i = 1;
        if ((nums[i-1]+nums[i+1])/2.0 == nums[i]) {
            swap(nums, i, i-1);
        }

        return nums;
    }

    private static void swap(int[] nums, int p, int q) {
        int temp = nums[p];
        nums[p] = nums[q];
        nums[q] = temp;
    }

    private static void test(int[] nums) {
        for (int i = 0; i < nums.length; i++)
            System.out.print(nums[i] + ",");
        System.out.println();
        for (int i = 1; i < nums.length-1; i++) {
            if ((nums[i-1]+nums[i+1])/2.0 == nums[i]) {
                System.out.println("FAIL");
                return;
            }
        }
        System.out.println("PASS");
    }

    public static void main(String[] args) {
        // input 1
        int[] nums = new int[] {1, 2, 3, 4, 5};
        int[] answer = rearrangeArray(nums);
        test(answer);

        // input 2
        nums = new int[] {6, 2, 0, 9, 7};
        answer = rearrangeArray(nums);
        test(answer);

        // input 3
        nums = new int[] {0, 4, 1, 5, 3};
        answer = rearrangeArray(nums);
        test(answer);
    }
}