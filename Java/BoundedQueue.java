import java.util.ArrayDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.Random;
import java.util.Objects;

public class BoundedQueue<T> {
    private int capacity;
    private Lock lock;
    private Condition putCondition;
    private Condition takeCondition;
    private ArrayDeque<T> queue;

    public BoundedQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("empty queue");
        }
        this.capacity = capacity;
        lock = new ReentrantLock();
        putCondition = lock.newCondition();
        takeCondition = lock.newCondition();
        queue = new ArrayDeque<>(capacity);
    }

    public void put(T item) throws InterruptedException {
        Objects.requireNonNull(item, "put item must not be null");
        lock.lockInterruptibly();
        try {
            while (queue.size() >= capacity) {
                putCondition.await();
            }
            queue.addLast(item);
            takeCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        T item;
        lock.lockInterruptibly();
        try {
            while (queue.isEmpty()) {
                takeCondition.await();
            }
            item = queue.removeFirst();
            putCondition.signal();
        } finally {
            lock.unlock();
        }
        return item;
    }

    public static void main(String[] args) throws InterruptedException {
        final int producerCount = 3;
        final int consumerCount = 2;
        final int producerIter = 2;
        final int consumerIter = 3;
        final Random random = new Random();

        final BoundedQueue<Integer> bq = new BoundedQueue<>(4);
        // AtomicLong consumedCount = 0;
        // AtomicLong consumedTotal = 0;

        Thread[] producers = new Thread[producerCount];
        Thread[] consumers = new Thread[consumerCount];

        Runnable consumerTask = () -> {
            long id = Thread.currentThread().threadId();
            for (int i = 0; i < consumerIter; i++) {
                try {
                    Thread.sleep(random.nextInt(10) + 1);
                    System.out.println("[C: " + id + "] " + bq.take());
                } catch (InterruptedException e) {
                    return;
                }
            }
        };
        for (int i = 0; i < consumerCount; i++) {
            consumers[i] = new Thread(consumerTask);
            consumers[i].start();
        }

        Runnable producerTask = () -> {
            long id = Thread.currentThread().threadId();
            for (int i = 0; i < producerIter; i++) {
                try {
                    int r = random.nextInt(10) + 1;
                    Thread.sleep(r);
                    bq.put(r);
                    System.out.println("[P: " + id + "] " + r);
                } catch (InterruptedException e) {
                    return;
                }
            }
        };
        for (int i = 0; i < producerCount; i++) {
            producers[i] = new Thread(producerTask);
            producers[i].start();
        }

        for (int i = 0; i < producerCount; i++) {
            producers[i].join();
        }
        for (int i = 0; i < consumerCount; i++) {
            consumers[i].join();
        }

    }
}
