import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;

class AmicableResult {
    public long n1;
    public long n2;

    public AmicableResult(long n1, long n2) {
        this.n1 = n1;
        this.n2 = n2;
    }
}

class CheckAmicable implements Callable<AmicableResult> {
    private long num;

    public CheckAmicable(long num) {
        this.num = num;
    }

    @Override
    public AmicableResult call() throws Exception {
        long pair = AmicablePairs.getAmicablePair(num);
        if (pair != 0) {
            return new AmicableResult(num, pair);
        }
        return null;
    }
}

class AmicablePairsCallableExSvc {
    private static final int NUM_TASKS = 10;

    public static void main(String[] args) {
        // Input has number of amicable pair of numbers to find
        if (args.length != 1) {
            System.out.println("Usage: java AmicablePairsCallableExSvc <first-n-amicable-pairs>");
            System.exit(1);
        }

        int amicablesDesired = Integer.parseInt(args[0]);
        System.out.println("Finding " + amicablesDesired + " amicable pairs of numbers");

        ExecutorService service = Executors.newFixedThreadPool(NUM_TASKS);

        long count = 2;
        Future[] futures = new Future[NUM_TASKS];

        // Generate NUM_TASKS
        for (int i = 0; i < NUM_TASKS; i++) {
            futures[i] = service.submit(new CheckAmicable(count++));
        }

        int taskIndex = 0;
        while (amicablesDesired > 0) {
            try {
                AmicableResult result = (AmicableResult) 
                    futures[taskIndex].get(2, TimeUnit.MILLISECONDS);

                if (result != null) {
                    System.out.println("[" + taskIndex + "] Found Amicable " + 
                        "Pair: {" + result.n1 + "," + result.n2 + "}");
                    amicablesDesired--;
                }

                // Generate a new task
                futures[taskIndex] = service.submit(new CheckAmicable(count++));
            }
            // Swallow exception - check the next task
            catch (TimeoutException|InterruptedException|ExecutionException e) {
            }

            taskIndex++;
            taskIndex = (taskIndex == NUM_TASKS) ? 0 : taskIndex;
        }

        service.shutdown();
    }
}