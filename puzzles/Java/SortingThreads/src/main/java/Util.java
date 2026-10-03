import java.util.Comparator;

public class Util {
    protected static void swap(Comparable[] array, int i, int j) {
        assert(i >= 0 && i < array.length);
        assert(j >= 0 && j < array.length);
        Comparable temp = array[i];
        array[i] = array[j];;
        array[j] = temp;
    }

    protected static boolean isSorted(Comparable[] array) {
        if (array.length < 2)
            return true;

        for (int i = 0; i < array.length-1; i++) {
            if (array[i].compareTo(array[i+1]) > 0)
                return false;
        }
        return true;
    }

    protected static int compare(Comparable x, Comparable y, Comparator comparator) {
        if (comparator != null) {
            return comparator.compare(x, y);
        }
        return x.compareTo(y);
    }
}
