/* Get all Amicable Pairs using Runnable Threads & a fixed size Thread pool 
    getting tasks from synchronizing on a simple queue (list) */

import java.util.LinkedList;
import java.util.Queue;

public class AmicablePairsSynchronizedQueue implements Runnable {
    private static final int RANGE = 10000;
    private static final int MAX_THREADS = 10;
    private static Queue<Integer> taskQueue;

    @Override
    public void run() {
        //System.out.println("Started thread: " + 
        //    Thread.currentThread().getName());

        while (true) {
            int start = -1;
            synchronized(this) {
                start = taskQueue.isEmpty() ? -1 : taskQueue.poll();
            }

            if (start == -1)
                break;

            for (long i = start; i < (start + RANGE); i++) {
                long pair = AmicablePairs.getAmicablePair(i);
                if (pair != 0) {
                    System.out.println("[" + Thread.currentThread().getName() + 
                        "] Found Amicable Pair: {" + i + "," + pair + "}");
                }
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

        taskQueue = new LinkedList<>();
        for (int i = 0; i < upperLimit; i++)
            taskQueue.offer(i*RANGE);

        int nThreads = Math.min(upperLimit, MAX_THREADS);
        Thread[] tarray = new Thread[nThreads];
        for (int i = 0; i < nThreads; i++) {
            tarray[i] = new Thread(new AmicablePairsSynchronizedQueue());
            tarray[i].start();
        }

        for (int i = 0; i < nThreads; i++) {
            tarray[i].join();
        }
    }
}