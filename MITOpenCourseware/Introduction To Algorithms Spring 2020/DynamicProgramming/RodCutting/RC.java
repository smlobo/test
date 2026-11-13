import java.util.Arrays;

public class RC {
    static int rodCutting(int rodLength, int[] values) {
        // Base case - no further division possible
        if (rodLength == 1) {
            // System.out.println(indent + rodLength);
            return values[1];
        }

        int returnCost = 0;

        // Iterate thru all possible divisions
        for (int i = 1; i <= rodLength; i++) {
            int cost = values[i] + rodCutting(rodLength-i, values);
            returnCost = Math.max(returnCost, cost);
        }

        // System.out.println(indent + rodLength + ":" + returnCost);
        return returnCost;
    }

    static int rodCuttingMemoization(int rodLength, int[] values, int[] maxVals) {
        // Check memoize
        if (maxVals[rodLength] > 0)
            return maxVals[rodLength];

        // Base case - no further division possible
        if (rodLength == 1) {
            // System.out.println(indent + rodLength);
            return values[1];
        }

        int returnCost = 0;

        // Iterate thru all possible divisions
        for (int i = 1; i <= rodLength; i++) {
            int cost = values[i] + rodCuttingMemoization(rodLength-i, values, maxVals);
            returnCost = Math.max(returnCost, cost);
        }

        // System.out.println(indent + rodLength + ":" + returnCost);
        // Memoize
        maxVals[rodLength] = returnCost;
        return returnCost;
    }

    public static void main(String[] args) {
        int[] vals = new int[] {0, 1, 10, 13, 18, 20, 31, 32};
        System.out.println("Costs: " + Arrays.toString(vals));

        // All lengths
        for (int i = 1; i < vals.length; i++) {
            int max = rodCutting(i, vals);
            int[] maxVals = new int[vals.length];
            Arrays.fill(maxVals, 0); // unnecessary
            int maxM = rodCuttingMemoization(i, vals, maxVals);
            System.out.println(i + " = " + max + ":" + maxM);
        }
    }
}