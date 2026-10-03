public class ArrayNestingCopy {
    public int arrayNesting(int[] nums) {

        int rv = 0;
        
        // Iterate thru all possibilities
        for (int i = 0; i < nums.length; i++) {

            System.out.print("Checking S[" + i + "] = { ");

            // Inner loop - max length long
            int size = 0;
            for (int j = i; nums[j] >= 0; size++) {
                int k = nums[j];
                nums[j] = -1;
                j = k;
            }

            System.out.println("}");

            rv = Integer.max(rv, size);

            // Highest possible answer
            if (rv == nums.length)
                break;
        }

        return rv;
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 0, 3, 1, 6, 2};
        //int[] nums = {1, 2, 0};

        ArrayNestingCopy ar = new ArrayNestingCopy();
        System.out.println("Solution: " + ar.arrayNesting(nums));
    }
}
