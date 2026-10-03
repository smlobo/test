import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class PerfectResult {
    public long perfect;
    public List<Long> divisors;

    PerfectResult(long p, List<Long> d) {
        perfect = p;
        divisors = d;
    }
}

class CheckPerfect implements Callable<PerfectResult> {
    private long count;
    private long first;
    private long second;

    CheckPerfect(long c, long f, long s) {
        count = c;
        first = f;
        second = s;
    }

    @Override
    public PerfectResult call() throws Exception {
        // More efficient Euclid - (x+1) should be prime { 38s to 36s for 8 }
        if (!PrimeNumbers.checkPrimeAny(count+1)) {
            return null;
        }

        // Even more efficient Euclid - (2^(x+1)-1) should be prime { 38s to 36s for 8 }
        if (!PrimeNumbers.checkPrimeAny(second-1)) {
            return null;
        }

        // Potential perfect
        long perfect = first * (second - 1);

        List<Long> divisors = PerfectNumbers.checkPerfect(perfect);
        if (divisors.size() > 0) {
            return new PerfectResult(perfect, divisors);
        }

        return null;
    }
}

public class PerfectNumbersCallableExSvc {
    private static final int NUM_THREADS = 10;
    private static final int NUM_TASKS = 10;

    public static void main(String[] args) {
        // Input has number of perfect numbers to find
        if (args.length != 1) {
            System.out.println("Usage: java PerfectNumbersCallableExSvc <first-n-perfects>");
            System.exit(1);
        }

        int perfectsDesired = Integer.parseInt(args[0]);
        System.out.println("Finding " + perfectsDesired + " perfect numbers");

        AtomicInteger atomicDesired = new AtomicInteger(perfectsDesired);

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

        // Euclid algorithm { perfect = 2^x * (2^(x+1)-1) }
        long first = 2;
        int count = 1;
        long second = 0;

        // Start by generating NUM_TASKS tasks
        Future[] futures = new Future[NUM_TASKS];
        for (int i = 0; i < NUM_TASKS; i++) {
            // 2^x
            second = first * 2;
            futures[i] = executorService.submit(new CheckPerfect(count, first, second));
            //System.out.println("[" + i + "] " + count + ", " + first + ", " + 
            //    second + " = " + first*(second-1));

            first = second;
            count++;
        }

        // Check for task completion, & generate new tasks
        boolean breakOut = false;
        boolean overFlow = false;
        while (!breakOut && !overFlow) {
            for (int i = 0; i < NUM_TASKS; i++) {
                try {
                    PerfectResult result = (PerfectResult) 
                        futures[i].get(10, TimeUnit.MILLISECONDS);

                    if (result != null) {
                        System.out.print("Found perfect number: " + result.perfect + 
                            " { ");
                        for (Long d : result.divisors)
                            System.out.print(d + ", ");
                        System.out.println("}");

                        perfectsDesired = atomicDesired.decrementAndGet();
                        if (perfectsDesired == 0) {
                            breakOut = true;
                            break;
                        }
                    }

                    // 2^x
                    second = first * 2;
                    futures[i] = executorService.
                        submit(new CheckPerfect(count, first, second));
                    //System.out.println("[" + i + "] " + count + ", " + first + ", " + 
                    //    second + " = " + first*(second-1));
                    first = second;
                    count++;

                    // Check for next iteration overflow
                    if (count == 32) {
                        overFlow = true;
                        break;
                    }
                }
                catch (Exception e) {
                }
            }
        }

        /*for (int i = 0; i < NUM_TASKS; i++) {
            futures[i].cancel(true);
        }*/
        // All results not in - wait for tasks to complete
        if (!breakOut) {
            for (int i = 0; i < NUM_TASKS; i++) {
                try {
                    PerfectResult result = (PerfectResult) futures[i].get();

                    if (result != null) {
                        System.out.print("Found perfect number: " + 
                            result.perfect + " { ");
                        for (Long d : result.divisors)
                            System.out.print(d + ", ");
                        System.out.println("}");

                        perfectsDesired--;
                        if (perfectsDesired == 0) {
                            break;
                        }
                    }
                }
                catch (Exception e) {
                }
            }
        }

        executorService.shutdown();
    }
}