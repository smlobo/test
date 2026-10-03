import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MTMergeSort implements Runnable {
    private static final int INSERTION_SORT_CUTOFF = 8;
    private static final int MERGE_SORT_CUTOFF = 1000;

    //private static ExecutorService pool;
    private static Comparable[] array;
    private static Comparable[] aux;

    private CountDownLatch latch;
    private int begin;
    private int end;

    public MTMergeSort(CountDownLatch latch, int begin, int end) {
        this.latch = latch;
        this.begin = begin;
        this.end = end;
    }

    public static void sort(Comparable[] array, int begin, int end) {
        MTMergeSort.array = array;

        // Create an aux array for merging
        aux = new Comparable[array.length];

        //pool = Executors.newFixedThreadPool(10);

        CountDownLatch latch = new CountDownLatch(1);
        Runnable runnable = new MTMergeSort(latch, begin, end);
        //pool.execute(runnable);
        Thread t = new Thread(runnable);
        t.start();

        //pool.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // Use InsertionSort
        if ((end-begin) <= INSERTION_SORT_CUTOFF) {
            InsertionSort.sort(array, begin, end);
            this.latch.countDown();
            return;
        }

        // Use MergeSort
        if ((end-begin) <= MERGE_SORT_CUTOFF) {
            MergeSort.sort(array, begin, end);
            this.latch.countDown();
            return;
        }

        // Recursively sort each subset in a separate thread
        int mid =  (end - begin) / 2 + begin;

        CountDownLatch latch = new CountDownLatch(2);
        Runnable firstHalf = new MTMergeSort(latch, begin, mid);
        //pool.execute(firstHalf);
        Thread t1 = new Thread(firstHalf);
        t1.start();
        Runnable secondHalf = new MTMergeSort(latch, mid, end);
        //pool.execute(secondHalf);
        Thread t2 = new Thread(secondHalf);
        t2.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Merge sorted arrays (into aux)
        int s1Ptr = begin;
        int s2Ptr = mid;
        int auxPtr = begin;
        while (s1Ptr < mid || s2Ptr < end) {
            if (s2Ptr == end) {
                aux[auxPtr++] = array[s1Ptr++];
            }
            else if (s1Ptr == mid) {
                aux[auxPtr++] = array[s2Ptr++];
            }
            else if (array[s1Ptr].compareTo(array[s2Ptr]) < 0) {
                aux[auxPtr++] = array[s1Ptr++];
            }
            else {
                aux[auxPtr++] = array[s2Ptr++];
            }
        }

        // Copy sorted aux back to the original array
        for (int i = begin; i < end; i++)
            array[i] = aux[i];

        this.latch.countDown();
    }
}
