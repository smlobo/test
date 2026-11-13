import java.util.Arrays;

public class SubsetSum {

    static boolean subsetSum(int target, int[] list, int index) {
        int newTarget = target - list[index];

        // Found!
        if (newTarget == 0)
            return true;

        // Iterate over all remaining list items with the new target
        for (int i = index+1; i < list.length; i++) {
            boolean found = subsetSum(newTarget, list, i);
            if (found)
                return true;
        }
        return false;
    }

    static boolean subsetSum(int target, int[] list) {
        // Iterate over all list items with the target
        for (int i = 0; i < list.length; i++) {
            boolean found = subsetSum(target, list, i);
            if (found)
                return true;
        }
        return false;        
    }

    public static void main(String[] args) {
        int[] list = new int[] {2, 5, 7, 8, 9};
        System.out.println("List: " + Arrays.toString(list));

        for (int i = 0; i < 33; i++) {
            boolean found = subsetSum(i, list);
            System.out.println(i + " = " + found);
        }
    }
}