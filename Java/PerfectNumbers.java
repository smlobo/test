// The sum of the divisors (excluding the number itself) are equal to the number

import java.util.LinkedList;
import java.util.List;

public class PerfectNumbers {
    public static void main(String[] args) {
        // Input has number of perfect numbers to find
        if (args.length < 1 || args.length > 2) {
            System.out.println("Usage: java PerfectNumbers <first-n-perfects> [brute]");
            System.exit(1);
        }

        int perfectsDesired = Integer.parseInt(args[0]);
        System.out.println("Finding " + perfectsDesired + " perfect numbers");

        // Brute force method
        if (args.length == 2) {
            int count = 1;
            while (perfectsDesired != 0) {
                List<Long> divisors = checkPerfect(count);
                if (divisors.size() > 0) {
                    System.out.print("Found perfect number: " + count + " { ");
                    for (Long d : divisors)
                        System.out.print(d + ", ");
                    System.out.println("}");
                    perfectsDesired -= 1;
                }
                count++;
                //divisors.clear();
            }
        }

        // Euclid algorithm { perfect = 2^x * (2^(x+1)-1)
        long first = 2;
        int count = 1;
        while (perfectsDesired != 0) {
            // 2^x
            long second = first * 2;

            // More efficient Euclid - (x+1) should be prime { 38s to 36s for 8 }
            if (!PrimeNumbers.checkPrimeAny(count+1)) {
            //if (!PrimeNumbers.checkPrimeSequential(count+1)) {
                first = second;
                count++;
                continue;
            }

            // Even more efficient Euclid - (2^(x+1)-1) should be prime { 38s to 36s for 8 }
            if (!PrimeNumbers.checkPrimeAny(second-1)) {
                first = second;
                count++;
                continue;
            }

            // Potential perfect
            long perfect = first * (second - 1);

            int next = count + 1;
            //System.out.println("  Potential perfect: " + perfect + " = 2^" + count + " * (2^" + next + "-1)");
            List<Long> divisors = checkPerfect(perfect);
            if (divisors.size() > 0) {
                System.out.print("Found perfect number: " + perfect + " { ");
                for (Long d : divisors)
                    System.out.print(d + ", ");
                System.out.println("}");
                perfectsDesired -= 1;
            }
            //divisors.clear();

            first = second;
            count++;
        }
    }

    public static List<Long> checkPerfect(long n) {
        List<Long> divisors = populateDivisors(n);
        long sum = 0;
        for (Long d : divisors)
            sum += d;
        if (sum == n)
            return divisors;
        divisors.clear();
        return divisors;
    }

    public static List<Long> populateDivisors(long n) {
        List<Long> divisors = new LinkedList<>();
        divisors.add((long)1);
        // Brute force - all possible divisors 2 to (n-1) {2m 13s for 6}
        //for (long i = 2; i < n; i++) {
        // More efficient - only divisors 2 thru' n/2 {1m 9s for 6}
        //for (long i = 2; i <= n/2; i++) {
        // Most efficient - iterate to the square root and get the pair { < 0.1s for 6!!, 38s for 8 }
        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n%i == 0) {
                divisors.add(i);
                if (i != n/i)
                    divisors.add(n / i);
            }
        }
        return divisors;
    }

}