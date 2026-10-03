import java.util.Comparator;

public class InsertionSort {
    public static void sort(Comparable[] array, int begin, int end) {
        sort(array, begin, end, null);
    }

    public static void sort(Comparable[] array, int begin, int end, Comparator comparator) {
        for (int i = begin; i < end-1; i++) {
            for (int j = i+1; j < end; j++) {
                //if (array[i].compareTo(array[j]) > 0) {
                if (Util.compare(array[i], array[j], comparator) > 0) {
                    Util.swap(array, i, j);
                }
            }
        }
    }
}
