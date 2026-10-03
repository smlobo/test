// The sum of the divisors (excluding the number itself) are equal to the number

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class PerfectNumbersParallelDivisors {
    private static final int NUM_THREADS = 100;
    private static final int RANGE = 100000;
    private static ExecutorService executorService;
    private static Future[] futures;

    public static void main(String[] args) {
        // Input has number of perfect numbers to find
        if (args.length != 1) {
            System.out.println("Usage: java PerfectNumbers <first-n-perfects>");
            System.exit(1);
        }

        int perfectsDesired = Integer.parseInt(args[0]);
        System.out.println("Finding " + perfectsDesired + " perfect numbers");

        executorService = Executors.newFixedThreadPool(NUM_THREADS);
        futures = new Future[NUM_THREADS];

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

        executorService.shutdown();
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
        //for (long i = 2; i <= Math.sqrt(n); i += RANGE) {
        //}

        long last = (long) Math.sqrt(n);

        // Generate all tasks
        List<DivisorsInRange> tasks = new LinkedList<>();
        for (long i = 2; i <= last; i += RANGE) {
            long endRange = Math.min(i+RANGE-1, last);
            DivisorsInRange dir = new DivisorsInRange(n, i, endRange);
            tasks.add(dir);
        }

        // Fill up all the threads with initial tasks
        ListIterator<DivisorsInRange> taskIterator = tasks.listIterator();
        for (int i = 0; i < NUM_THREADS; i++) {
            if (taskIterator.hasNext()) {
                futures[i] = executorService.submit(taskIterator.next());
            }
            else {
                break;
            }
        }

        // Check for completion, and add new tasks
        while (taskIterator.hasNext()) {
            for (int i = 0; i < NUM_THREADS; i++) {
                try {
                    List<Long> result = (List<Long>) 
                        futures[i].get(10, TimeUnit.MILLISECONDS);

                    futures[i] = executorService.submit(taskIterator.next());

                    divisors.addAll(result);
                }
                catch (Exception e) {}

                if (!taskIterator.hasNext())
                    break;
            }
        }

        // Get running task results
        for (int i = 0; i < NUM_THREADS; i++) {
            if (futures[i] == null)
                continue;

            try {
                List<Long> result = (List<Long>) futures[i].get();
                divisors.addAll(result);
            }
            catch (Exception e) {}
        }

        return divisors;
    }
}

class DivisorsInRange implements Callable<List<Long>> {
    private long number;
    private long begin;
    private long end;

    DivisorsInRange(long n, long b, long e) {
        number = n;
        begin = b;
        end = e;
    }

    @Override
    public List<Long> call() throws Exception {
        List<Long> divisors = new LinkedList<>();
        for (long i = begin; i <= end; i++) {
            if (number%i == 0) {
                divisors.add(i);
                if (i != number/i)
                    divisors.add(number/i);
            }
        }
        return divisors;
    }
}