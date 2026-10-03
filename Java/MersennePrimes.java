// A prime number of the form 2^x - 1

import java.util.ArrayList;

public class MersennePrimes {

    public static void main(String[] args) {
        // Input has number of mersenne prime numbers to find
        if (args.length < 1 || args.length > 2) {
            System.out.println("Usage: java MersennePrimes <first-n-primes> [slow]");
            System.exit(1);
        }

        int primesDesired = Integer.parseInt(args[0]);
        System.out.println("Finding " + primesDesired + " Mersenne Primes");

        if (args.length != 2) {
            long power = 2;
            while (primesDesired != 0) {
                if (checkMersennePrime(power-1)) {
                    System.out.println("Found Mersenne Prime: " + (power-1));
                    primesDesired--;
                }
                power *= 2;
            }
        }

        else {
            System.out.println("Found Mersenne Prime: 1");
            primesDesired--;
            long count = 2;
            while (primesDesired != 0) {
                if (PrimeNumbers.checkPrimeSequential(count)) {
                    // Is 2^x - 1?
                    long n = count;
                    boolean mersenne = true;
                    while (n != 0) {
                        if ((n & 0x1) == 0) {
                            mersenne = false;
                            break;
                        }
                        n >>>= 1;
                    }

                    if (mersenne) {
                        System.out.println("Found Mersenne Prime: " + count);
                        primesDesired--;
                    }
                }
                count++;
            }
        }
    }

    public static boolean checkMersennePrime(long n) {
        // Brute force
        // More efficient - only divisors 2 thru' n/2 {10s for 9}
        //for (int i = 2; i <= n/2; i++) {
        // Most efficient - only divisors upto the sq root {0.05 sec for 9}
        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n%i == 0)
                return false;
        }
        return true;
    }
}