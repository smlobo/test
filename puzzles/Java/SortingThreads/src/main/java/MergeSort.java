import java.util.Comparator;

public class MergeSort {
    private static final int INSERTION_SORT_CUTOFF = 8;

    public static void sort(Comparable[] array, int begin, int end) {
        sort(array, begin, end, null);
    }

    public static void sort(Comparable[] array, int begin, int end, Comparator comparator) {
        // Create an aux for the entire array
        Comparable[] aux = new Comparable[array.length];

        sort(array, aux, begin, end, comparator);
    }

    private static void sort(Comparable[] array, Comparable[] aux, int begin, int end, Comparator comparator) {
        // 8 and under, use InsertionSort
        if ((end-begin) <= INSERTION_SORT_CUTOFF) {
            InsertionSort.sort(array, begin, end, comparator);
            return;
        }

        // Recursively sort each subset
        int mid =  (end - begin) / 2 + begin;
        sort(array, aux, begin, mid, comparator);
        sort(array, aux, mid, end, comparator);

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
            //else if (array[s1Ptr].compareTo(array[s2Ptr]) < 0) {
            else if (Util.compare(array[s1Ptr], array[s2Ptr], comparator) < 0) {
                aux[auxPtr++] = array[s1Ptr++];
            }
            else {
                aux[auxPtr++] = array[s2Ptr++];
            }
        }

        // Copy sorted aux back to the original array
        for (int i = begin; i < end; i++)
            array[i] = aux[i];
    }
}
