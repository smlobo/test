import java.util.PriorityQueue;
import java.util.Comparator;

public class PQMedian {
    public static double[] pqMedian(int[] array) {
        double[] medianArray = new double[array.length];

        // MinPQ for the top half
        PriorityQueue<Integer> minPQ = new PriorityQueue<>(array.length/2+1);

        // MaxPQ for the bottom half
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(array.length/2+1, 
            Comparator.reverseOrder());

        // Iterate over the input array adding items to the respective PQ
        for (int i = 0; i < array.length; i++) {
            // Bottom half - add it to the maxPQ
            if (i == 0 || array[i] < maxPQ.peek())
                maxPQ.add(array[i]);
            // Top half - add it to the minPQ
            else
                minPQ.add(array[i]);

            // Balance both PQ
            // MaxPQ has more
            if (maxPQ.size() - minPQ.size() == 2)
                minPQ.add(maxPQ.poll());
            // MinPQ has more
            else if (minPQ.size() - maxPQ.size() == 2)
                maxPQ.add(minPQ.poll());

            // Find the median
            if (maxPQ.size() > minPQ.size())
                medianArray[i] = maxPQ.peek();
            else if (minPQ.size() > maxPQ.size())
                medianArray[i] = minPQ.peek();
            else
                medianArray[i] = (maxPQ.peek() + minPQ.peek()) / 2.0;             
        }

        return medianArray;
    }
}