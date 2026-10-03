// Simple demo of a field concurrently updated without a Lock, and with 
// a Lock

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class SimpleLock implements Runnable {
    private static final int ITERATIONS = 10;

    private int noLockCounter;
    private int lockCounter;
    private Lock lock;
    private Random random;

    public SimpleLock() {
        lock = new ReentrantLock();
        //lock.unlock();
        random = new Random();
    }

    @Override
    public void run() {
        // Repeat to show the bad RAW/WAR/WAW hazard
        for (int i = 0; i < ITERATIONS; i++) {
            // Introduce a random delay (between 1 & 10ms)
            try {
                Thread.sleep(random.nextInt(10) + 1);
            }
            catch (InterruptedException e) {
                // Swallow
            }

            // Increment the noLockCounter
            noLockCounter++;

            // Increment the lockCounter
            lock.lock();
            lockCounter++;
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int nThreads = Integer.parseInt(args[0]);

        System.out.println("Using " + nThreads + " threads. Expected value = " + 
            nThreads*ITERATIONS);

        // Instance executed in parallel
        SimpleLock simpleLock = new SimpleLock();

        // Create and start the threads
        Thread[] threads = new Thread[nThreads];
        for (int i = 0; i < nThreads; i++) {
            threads[i] = new Thread(simpleLock);
            threads[i].start();
        }

        // Wait for threads to complete
        for (int i = 0; i < nThreads; i++)
            threads[i].join();

        // Print results
        System.out.println("noLockCounter: " + simpleLock.noLockCounter);
        System.out.println("lockCounter: " + simpleLock.lockCounter);
    }
}