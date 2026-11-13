/*
Factorial formula: nCr = n!/(r!.(n-r)!)

Recursive formula: nCr = (n-1)C(r-1) + (n-1)Cr
nCn = 1; nC0 = 1

Interesting case:
% java BinomialCoefficient 30 12
Calculating: 30C12
Recursive: 86493225, took: 189
Memorization: 86493225, took: 0

*/

public class BinomialCoefficient {
    private static int recursive(int n, int r) {
        // Base case - nCn OR nC0
        if (n == r || r == 0)
            return 1;

        // Recurse
        return recursive(n-1, r-1) + recursive(n-1, r);
    }

    private static int[][] cache;
    private static int memorization(int n, int r) {
        if (cache == null)
            cache = new int[n+1][r+1];

        // Base case - nCn OR nC0
        if (n == r || r == 0)
            return 1;

        // Cached
        if (cache[n][r] != 0)
            return cache[n][r];

        int answer = memorization(n-1, r-1) + memorization(n-1, r);

        cache[n][r] = answer;
        return answer;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int r = Integer.parseInt(args[1]);

        long timer;

        System.out.println("Calculating: " + n + "C" + r);

        timer = System.currentTimeMillis();
        int rAnswer = recursive(n, r);
        timer = System.currentTimeMillis() - timer;
        System.out.println("Recursive: " + rAnswer + ", took: " + timer);

        timer = System.currentTimeMillis();
        int mAnswer = memorization(n, r);
        timer = System.currentTimeMillis() - timer;
        System.out.println("Memorization: " + mAnswer + ", took: " + timer);

        assert(rAnswer == mAnswer);
    }
}