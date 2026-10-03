// Demo of higher throughput with a ReadWriteLock vs a normal Lock

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import java.util.Random;

public class LockVsRWLock {
    private static final int NUM_READS = 10;
    private static Random random;

    static class NormalLock implements Runnable {
        private Lock normalLock;
        private int normalData;
        private int outputValue;

        public NormalLock() {
            this.normalData = 1;
            normalLock = new ReentrantLock();
        }

        @Override
        public void run() {
            // Do several reads
            int lVar = 0;
            for (int i = 0; i < NUM_READS; i++) {
                normalLock.lock();
                try {
                    Thread.sleep(random.nextInt(10) + 1);
                } catch (InterruptedException e) {}
                lVar += normalData;
                normalLock.unlock();
            }

            synchronized(this) {
                outputValue += lVar;
            }

            // Do one write
            normalLock.lock();
            try {
                Thread.sleep(random.nextInt(10) + 1);
            } catch (InterruptedException e) {}
            normalData = lVar/NUM_READS;
            normalLock.unlock();
        }
    }

    static class RWLock implements Runnable {
        private ReadWriteLock rwLock;
        private int rwData;
        private int outputValue;

        public RWLock() {
            this.rwData = 1;
            rwLock = new ReentrantReadWriteLock();
        }

        @Override
        public void run() {
            // Do several reads
            int lVar = 0;
            for (int i = 0; i < NUM_READS; i++) {
                rwLock.readLock().lock();
                try {
                    Thread.sleep(random.nextInt(10) + 1);
                } catch (InterruptedException e) {}
                lVar += rwData;
                rwLock.readLock().unlock();
            }

            synchronized(this) {
                outputValue += lVar;
            }

            // Do one write
            rwLock.writeLock().lock();
            try {
                Thread.sleep(random.nextInt(10) + 1);
            } catch (InterruptedException e) {}
            rwData = lVar/NUM_READS;
            rwLock.writeLock().unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        random = new Random();
        int nThreads = Integer.parseInt(args[0]);

        Thread[] threads = new Thread[nThreads];

        NormalLock nLock = new NormalLock();
        long nTimer = System.currentTimeMillis();
        for (int i = 0; i < nThreads; i++) {
            threads[i] = new Thread(nLock);
            threads[i].start();
        }
        for (int i = 0; i < nThreads; i++) {
            threads[i].join();
        }
        nTimer = System.currentTimeMillis() - nTimer;
        System.out.println("Normal Lock: " + nLock.outputValue + " in " + 
            nTimer + "ms");


        RWLock rwLock = new RWLock();
        long rwTimer = System.currentTimeMillis();
        for (int i = 0; i < nThreads; i++) {
            threads[i] = new Thread(rwLock);
            threads[i].start();
        }
        for (int i = 0; i < nThreads; i++) {
            threads[i].join();
        }
        rwTimer = System.currentTimeMillis() - rwTimer;
        System.out.println("RW Lock: " + rwLock.outputValue + " in " + 
            rwTimer + "ms");
    }
}