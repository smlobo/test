import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.Random;

public class AtomicCounter implements Runnable {
    int iterations = 300;
    long noAtomicCounter = 0;
    AtomicLong counter = new AtomicLong(0);
    LongAdder adderCounter = new LongAdder();
    long syncCounter = 0;
    Random random = new Random();

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            // Introduce a random delay (between 1 & 10ms)
            try {
                Thread.sleep(random.nextInt(10) + 1);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // preserve the signal
                return;                             // stop this worker
            }

            noAtomicCounter += 2;
            counter.addAndGet(2);
            adderCounter.add(2);
            synchronized(this) {
                syncCounter += 2;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int nThreads = 10;

        AtomicCounter atomicCounter = new AtomicCounter();

        Thread[] threads = new Thread[nThreads];
        for (int i = 0; i < nThreads; i++) {
            threads[i] = new Thread(atomicCounter);
            threads[i].start();
        }

        for (int i = 0; i < nThreads; i++) {
            threads[i].join();
        }

        System.out.println("Count with " + nThreads + " threads, and " + 
            atomicCounter.iterations + " iterations = ");
        System.out.println("\tatomic: " + atomicCounter.counter);
        System.out.println("\tlong adder: " + atomicCounter.adderCounter.sum());
        System.out.println("\tnon-atomic: " + atomicCounter.noAtomicCounter);
        System.out.println("\tsync: " + atomicCounter.syncCounter);
    }
}
