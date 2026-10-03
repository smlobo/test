public class ArrayNesting {
    public int arrayNesting(int[] nums) {

        int rv = 0;
        
        // Iterate thru all possibilities
        for (int i = 0; i < nums.length; i++) {

            System.out.print("Checking S[" + i + "] = { ");

            // Init tracking array
            boolean[] dup = new boolean[nums.length];

            // Inner loop - max length long
            int index = i;
            int size = 0;
            for ( ; ; size++) {
                if (dup[index])
                    break;

                System.out.print("A[" + index + "] : " + nums[index] + ", ");
                dup[index] = true;
                index = nums[index];
            }

            System.out.println("}");

            if (size > rv)
                rv = size;

            // Highest possible answer
            if (rv == nums.length)
                break;
        }

        return rv;
    }

    public static void main(String[] args) {
        //int[] nums = {5, 4, 0, 3, 1, 6, 2};
        int[] nums = {1, 2, 0};

        ArrayNesting ar = new ArrayNesting();
        System.out.println("Solution: " + ar.arrayNesting(nums));
    }
}
