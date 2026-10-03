import java.util.Arrays;
import java.util.Random;

// Timing different sorting algorithms against an array of Integers
public class TestSort {
    protected static void printArray(Comparable[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        long timer, totalTime;

        int n = Integer.parseInt(args[0]);
        System.out.println("Generating array length: " + n);

        Random random = new Random();

        Integer[] iArray = new Integer[n];
        for (int i = 0; i < n; i++) {
            iArray[i] = random.nextInt(n);
        }

        Shuffle.shuffle(iArray);
        //printArray(iArray);
        timer = System.currentTimeMillis();
        InsertionSort.sort(iArray, 0, iArray.length);
        totalTime = System.currentTimeMillis() - timer;
        assert(Util.isSorted(iArray));
        //printArray(iArray);
        System.out.println(iArray.length + " insertion sort took: " + totalTime);

        Shuffle.shuffle(iArray);
        //printArray(iArray);
        timer = System.currentTimeMillis();
        MergeSort.sort(iArray, 0, iArray.length);
        totalTime = System.currentTimeMillis() - timer;
        assert(Util.isSorted(iArray));
        //printArray(iArray);
        System.out.println(iArray.length + " merge sort took: " + totalTime);

        Shuffle.shuffle(iArray);
        //printArray(iArray);
        timer = System.currentTimeMillis();
        MTMergeSort.sort(iArray, 0, iArray.length);
        totalTime = System.currentTimeMillis() - timer;
        assert(Util.isSorted(iArray));
        //printArray(iArray);
        System.out.println(iArray.length + " MT merge sort took: " + totalTime);

        Shuffle.shuffle(iArray);
        //printArray(iArray);
        timer = System.currentTimeMillis();
        Arrays.sort(iArray, 0, iArray.length);
        totalTime = System.currentTimeMillis() - timer;
        assert(Util.isSorted(iArray));
        //printArray(iArray);
        System.out.println(iArray.length + " java sort took: " + totalTime);
    }
}
