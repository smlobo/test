public class CoinSequences {
    private static int[] denominations = new int[] {25, 10, 5, 1};

    private static int recursion(int n, int dIndex) {
        // Base case: no leftover ammount - a valid combination
        if (n == 0)
            return 1;

        // Cannot use this denomination
        if (denominations[dIndex] > n)
            return recursion(n, dIndex+1);

        // Base case - last denomination - no other combination possible
        if (dIndex == denominations.length-1)
            return 1;

        return recursion(n-denominations[dIndex], dIndex) + 
            recursion(n, dIndex+1);
    }

    private static int[][] cache;
    private static int memorization(int n, int dIndex) {
        // Initialize the cache
        if (cache == null)
            cache = new int[n+1][denominations.length];

        // Base case: no leftover ammount - a valid combination
        if (n == 0)
            return 1;

        // Cannot use this denomination
        if (denominations[dIndex] > n)
            return recursion(n, dIndex+1);

        // Base case - last denomination - no other combination possible
        if (dIndex == denominations.length-1)
            return 1;

        // Check the cache
        if (cache[n][dIndex] > 0)
            return cache[n][dIndex];

        cache[n][dIndex] = memorization(n-denominations[dIndex], dIndex) + 
            memorization(n, dIndex+1);
        return cache[n][dIndex];
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        long rT = System.currentTimeMillis();
        int rA = recursion(n, 0);
        rT = System.currentTimeMillis() - rT;
        long mT = System.currentTimeMillis();
        int mA = memorization(n, 0);
        mT = System.currentTimeMillis() - mT;
        System.out.println(n + " combinations: [recursion] " + rA + " {" + rT + 
            "} [memorization] " + mA + " {" + mT + "}");

        assert(rA == mA);
    }
}