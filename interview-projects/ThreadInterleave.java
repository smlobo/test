
public class ThreadInterleave {
    public static void main(String[] args) throws InterruptedException {
        Runnable work = () -> {
            long tId = Thread.currentThread().threadId();
            for (int i = 1; i <= 5; i++) {
                System.out.println("[" + tId + "] " + i);
            }
        };
        Thread t1 = new Thread(work);
        Thread t2 = new Thread(work);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}