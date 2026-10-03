// Numbers divisible only by 1 and itself

//import java.util.ArrayList;
import java.util.LinkedList;

public class PrimeNumbers {
    //private static ArrayList<Long> primes = new ArrayList<>();
    private static LinkedList<Long> primes = new LinkedList<>();

    public static void main(String[] args) {
        // Input has number of prime numbers to find
        if (args.length != 1) {
            System.out.println("Usage: java PrimeNumbers <first-n-primes>");
            System.exit(1);
        }

        int primesDesired = Integer.parseInt(args[0]);
        System.out.println("Finding " + primesDesired + " primes");

        long count = 2;
        while (primesDesired != 0) {
            if (checkPrimeSequential(count)) {
                System.out.println("Found prime: " + count);
                primesDesired--;
            }
            count++;
        }
    }

    public static boolean checkPrimeAny(long n) {
        // Brute force
        //for (int i = 2; i <= n/2; i++) {
        // Most efficient - only divisors upto the sq root {0.05 sec for 9}
        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n%i == 0)
                return false;
        }
        return true;
    }

    public static boolean checkPrimeSequential(long n) {
        // Check against previous primes only
        if (n == 2) {
            primes.add(n);
            return true;
        }
        for (Long p : primes) {
            if (n % p == 0)
                return false;
        }
        primes.add(n);
        return true;
    }
}