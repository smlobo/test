import java.util.*;

// Timing searching for top M (10) numbers from N (>> M) numbers.
// [1] Using sort (NlogN)
// [2] priority queue (NlogM)
public class TestPriorityQueue {
    public static void main(String[] args) {
        long timer, totalTime;

        int n = Integer.parseInt(args[0]);
        System.out.println("Generating integer dataset: " + n);

        //Integer[] dataset = {10, 20, 30};
        Random random = new Random();
        Integer[] dataset = new Integer[n];
        for (int i = 0; i < n; i++) {
            dataset[i] = random.nextInt(n);
        }

        // Find the top 10 by sort
        Shuffle.shuffle(dataset);
        timer = System.currentTimeMillis();
        MergeSort.sort(dataset, 0, dataset.length, Comparator.reverseOrder());
        //Arrays.sort(dataset, 0, dataset.length, Comparator.reverseOrder());
        totalTime = System.currentTimeMillis() - timer;
        System.out.println("Merge Sort took: " + totalTime);
        for (int i = 0; i < 10; i++) {
            System.out.print(dataset[i] + ", ");
        }
        System.out.println();

        // Find the top 10 by PriorityQueue
        Shuffle.shuffle(dataset);
        //PriorityQueue<Integer> iPQ = new PriorityQueue<>(10, Comparator.reverseOrder());
        timer = System.currentTimeMillis();
        PriorityQueue<Integer> iPQ = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            iPQ.add(dataset[i]);
            if (iPQ.size() > 10)
                iPQ.poll();
        }
        totalTime = System.currentTimeMillis() - timer;
        System.out.println("Priority Queue took: " + totalTime);
        Iterator<Integer> iterator = iPQ.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ", ");
        }
        System.out.println();
    }
}