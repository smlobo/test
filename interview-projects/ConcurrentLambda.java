import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentLambda {
    public static void main(String[] args) throws InterruptedException {
        var task = new Runnable() {
            AtomicInteger atomicCounter = new AtomicInteger(0);
            int normalCounter = 0;

            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    atomicCounter.incrementAndGet();
                    normalCounter++;
                }
            }
        };
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        System.out.println("Final value: atomic: " + task.atomicCounter.get() + 
            ", normal: " + task.normalCounter);
    }
}