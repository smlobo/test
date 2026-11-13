/*
Person can take 1, 2, 3 steps. How many combinations for getting to n steps.
*/

public class StepSequences {
    private static int recursion(int n) {
        assert(n > 0);

        // Base case: 1 step
        if (n == 1)
            return 1;
        else if (n == 2)
            return 2;
        else if (n == 3)
            return 4;

        return recursion(n-1) + recursion(n-2) + recursion(n-3);
    }

    private static int[] cache;
    private static int memorization(int n) {
        assert(n > 0);
        if (cache == null)
            cache = new int[n+1];

        // Base case: 1 step
        if (n == 1)
            return 1;
        else if (n == 2)
            return 2;
        else if (n == 3)
            return 4;

        // Check cache
        if (cache[n] > 0)
            return cache[n];

        cache[n] = memorization(n-1) + memorization(n-2) + memorization(n-3);
        return cache[n];
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        long rT = System.currentTimeMillis();
        int rA = recursion(n);
        rT = System.currentTimeMillis() - rT;
        long mT = System.currentTimeMillis();
        int mA = memorization(n);
        mT = System.currentTimeMillis() - mT;
        System.out.println(n + " steps: [recursion] " + rA + " {" + rT + 
            "} [memorization] " + mA + " {" + mT + "}");

        assert(rA == mA);
    }
}