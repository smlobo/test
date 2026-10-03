// The sum of the divisors is equal to the other, and the sum of the divisors 
// is equal to the first.

import java.util.List;

public class AmicablePairs {

    public static void main(String[] args) {
        // Input has number of amicable pair of numbers to find
        if (args.length != 1) {
            System.out.println("Usage: java AmicablePairs <first-n-amicable-pairs>");
            System.exit(1);
        }

        int amicablesDesired = Integer.parseInt(args[0]);
        System.out.println("Finding " + amicablesDesired + " amicable pairs of numbers");

        long count = 2;
        while (amicablesDesired != 0) {
            long pair = getAmicablePair(count);
            if (pair != 0) {
                System.out.println("Found Amicable Pair: {" + count + "," + pair + "}");
                amicablesDesired--;
            }
            count++;
        }
    }

    public static long getAmicablePair(long n) {
        // Get the sum of the divisors
        //System.out.print("Divisors: " + n + " {");
        List<Long> divisors = PerfectNumbers.populateDivisors(n);

        long nSum = 0;
        for (Long l : divisors) {
            //System.out.print(l + ",");
            nSum += l;
        }
        //System.out.println("} = " + nSum);

        // Perfect number, not Amicable Pair
        // Also, already found the Amicable Pair when nSum < n
        if (n >= nSum)
            return 0;

        // Get the sum of the divisors of the sum :-)
        //System.out.print("Divisors: " + nSum + " {");
        divisors = PerfectNumbers.populateDivisors(nSum);
        long pSum = 0;
        for (Long l : divisors) {
            //System.out.print(l + ",");
            pSum += l;
        }
        //System.out.println("} = " + pSum);

        if (pSum == n)
            return nSum;

        return 0;
    }
}
