/*
Factorial formula: nPr = n!/(n-r)!

Recursive formula: nPr = (n-1)Pr + r.(n-1)P(r-1)
nP0 = 1, nPn = n!

Interesting case:
% java PermutationCoefficient 30 15
Calculating: 30P15
Recursive: 291471360, took: 467
Memorization: 291471360, took: 0
Manual: 291471360, took: 0

*/

public class PermutationCoefficient {
    private static int factorial(int x) {
        int answer = 1;
        for (int i = 2; i <= x; i++)
            answer *= i;
        return answer;
    }

    private static int recursive(int n, int r) {
        // Base case - nP0
        if (r == 0)
            return 1;

        // Base case - nPn
        if (n == r)
            return factorial(n);

        return recursive(n-1, r) + r * recursive(n-1, r-1);
    }

    private static int[][] cache;
    private static int memorization(int n, int r) {
        if (cache == null)
            cache = new int[n+1][r+1];

        // Base case - nP0
        if (r == 0)
            return 1;

        // Cached
        if (cache[n][r] != 0)
            return cache[n][r];

        // Base case - nPn
        int answer = 0;
        if (n == r) {
            answer = factorial(n);
        }

        // Normal case
        else {
            answer = memorization(n-1, r) + r * memorization(n-1, r-1);
        }

        cache[n][r] = answer;
        return answer;
    }

    private static int manual(int n, int r) {
        // Base case - nP0
        if (r == 0)
            return 1;

        int answer = 1;
        for (int i = n; i > (n-r); i--) {
            answer *= i;
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int r = Integer.parseInt(args[1]);

        long timer;

        System.out.println("Calculating: " + n + "P" + r);

        // timer = System.currentTimeMillis();
        // int rAnswer = recursive(n, r);
        // timer = System.currentTimeMillis() - timer;
        // System.out.println("Recursive: " + rAnswer + ", took: " + timer);

        timer = System.currentTimeMillis();
        int mAnswer = memorization(n, r);
        timer = System.currentTimeMillis() - timer;
        System.out.println("Memorization: " + mAnswer + ", took: " + timer);

        timer = System.currentTimeMillis();
        int mAAnswer = manual(n, r);
        timer = System.currentTimeMillis() - timer;
        System.out.println("Manual: " + mAAnswer + ", took: " + timer);

        // assert(rAnswer == mAnswer);
        assert(mAnswer == mAAnswer);
    }
}