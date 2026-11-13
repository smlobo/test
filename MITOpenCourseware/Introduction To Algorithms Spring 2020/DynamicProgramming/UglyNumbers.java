public class UglyNumbers {

    private static boolean divisibleBy235Only(int n) {
        if (n == 1)
            return true;
        else if (n%2 == 0)
            return divisibleBy235Only(n/2);
        else if (n%3 == 0)
            return divisibleBy235Only(n/3);
        else if (n%5 == 0)
            return divisibleBy235Only(n/5);
        return false;
    }

    private static int simple(int x) {
        // Base case 1 is ugly
        int currentUgly = 1;
        x--;

        int count = 2;
        while (x > 0) {
            if (divisibleBy235Only(count)) {
                currentUgly = count;
                x--;
            }
            count++;
        }

        return currentUgly;
    }

    private static int memorization(int x) {
        // Array of all ugly numbers
        int[] uglys = new int[x];

        // Initialize 1st ugly number
        uglys[0] = 1;

        // Index into the ugly list for each Prime
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;

        // Next multiples
        int nextUglyMultiple2 = uglys[index2] * 2;
        int nextUglyMultiple3 = uglys[index3] * 3;
        int nextUglyMultiple5 = uglys[index5] * 5;

        for (int i = 1; i < x; i++) {
            uglys[i] = Math.min(nextUglyMultiple2, nextUglyMultiple3);
            uglys[i] = Math.min(uglys[i], nextUglyMultiple5);
            //System.out.println(" -> " + uglys[i]);

            // Update the next *ugly* multiple
            if (uglys[i] == nextUglyMultiple2) {
                index2++;
                nextUglyMultiple2 = uglys[index2] * 2;
            }
            if (uglys[i] == nextUglyMultiple3) {
                index3++;
                nextUglyMultiple3 = uglys[index3] * 3;
            }
            if (uglys[i] == nextUglyMultiple5) {
                index5++;
                nextUglyMultiple5 = uglys[index5] * 5;
            }
        }

        return uglys[x-1];
    }

    public static void main(String[] args) {
        int s, n, m;
        long sTimer, mTimer;

        n = 7;
        s = simple(n);
        m = memorization(n);
        System.out.println(n + " ugly: " + s + " : " + m);
        assert(s == 8);
        assert(m == 8);

        n = 10;
        s = simple(n);
        m = memorization(n);
        System.out.println(n + " ugly: " + s + " : " + m);
        assert(s == 12);
        assert(m == 12);

        n = 15;
        s = simple(n);
        m = memorization(n);
        System.out.println(n + " ugly: " + s + " : " + m);
        assert(s == 24);
        assert(m == 24);

        n = 150;
        sTimer = System.currentTimeMillis();
        s = simple(n);
        sTimer = System.currentTimeMillis() - sTimer;
        mTimer = System.currentTimeMillis();
        m = memorization(n);
        mTimer = System.currentTimeMillis() - mTimer;
        System.out.println(n + " ugly: " + s + "{" + sTimer + "} : " + m + "{" + 
            mTimer + "}");
        assert(s == 5832);
        assert(m == 5832);

        n = 1000;
        sTimer = System.currentTimeMillis();
        s = simple(n);
        sTimer = System.currentTimeMillis() - sTimer;
        mTimer = System.currentTimeMillis();
        m = memorization(n);
        mTimer = System.currentTimeMillis() - mTimer;
        System.out.println(n + " ugly: " + s + "{" + sTimer + "} : " + m + "{" + 
            mTimer + "}");
        //assert(s == 5832);
        //assert(m == 5832);
    }
}