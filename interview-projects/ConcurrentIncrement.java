import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentIncrement implements Runnable {
    java.util.concurrent.atomic.AtomicInteger atomicCounter;
    int normalCounter;

    public ConcurrentIncrement() {
        this.atomicCounter = new AtomicInteger(0);
        this.normalCounter = 0;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            atomicCounter.incrementAndGet();
            normalCounter++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentIncrement c = new ConcurrentIncrement();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(c);
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        System.out.println("Final value: atomic: " + c.atomicCounter.get() + 
            ", normal: " + c.normalCounter);
    }
}