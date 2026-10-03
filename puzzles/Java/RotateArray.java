public class RotateArray {

    public void rotate(int[] nums, int k) {
        int[] cache = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            cache[i] = nums[i];

        if (k >= nums.length)
            k %= nums.length;
        
        for (int i = 0; i < nums.length; i++) {
            int offset = i + k;
            if (offset >= nums.length)
                offset -= nums.length;
            nums[offset] = cache[i];
        }
    }
    
    public static void main(String[] args) {
        //int[] nums = {1,2,3,4,5,6,7};
        //int k = 3;

        //int[] nums = {-1};
        //int k = 2;
        
        int[] nums = {1};
        int k = 0;

        RotateArray ra = new RotateArray();
        ra.rotate(nums, k);

        for (int i = 0; i < nums.length; i++)
            System.out.print(nums[i] + ", ");
        System.out.println();
    }
}
