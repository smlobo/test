/* Get all Amicable Pairs using Runnable Threads */

public class AmicablePairsRunnable implements Runnable {
    private static final int RANGE = 10000;
    private long start;

    AmicablePairsRunnable(long start) {
        this.start = start;
    }

    @Override
    public void run() {
        //System.out.println("Started thread: " + 
        //    Thread.currentThread().getName());

        for (long i = start; i < (start + RANGE); i++) {

            long pair = AmicablePairs.getAmicablePair(i);
            if (pair != 0) {
                System.out.println("[" + Thread.currentThread().getName() + 
                    "] Found Amicable Pair: {" + i + "," + pair + "}");
            }
        }

        //System.out.println("Completed thread: " + 
        //    Thread.currentThread().getName());
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java AmicablePairsRunnable <limit-10000>");
            System.exit(1);
        }

        int upperLimit = Integer.parseInt(args[0]);
        System.out.println("Finding Amicable Pairs upto: " + upperLimit*RANGE);

        Thread[] tarray = new Thread[upperLimit];
        for (int i = 0; i < upperLimit; i++) {
            tarray[i] = new Thread(new AmicablePairsRunnable(i*RANGE));
            tarray[i].start();
        }

        for (int i = 0; i < upperLimit; i++) {
            tarray[i].join();
        }
    }
}