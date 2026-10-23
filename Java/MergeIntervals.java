import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MergeIntervals {
    private static List<Integer[]> mergeIntervals(List<Integer[]> i) {
        List<Integer[]> merged = new ArrayList<>();

        // sort by first element in the array
        List<Integer[]> sorted = new ArrayList<>(i);
        sorted.sort((a, b) -> Integer.compare(a[0], b[0]));

        // Iterate tracking overlapping intervals
        for (Integer[] interval : sorted) {
            if (merged.isEmpty()) {
                merged.add(Arrays.copyOf(interval, interval.length));
            } else {
                Integer[] prev = merged.get(merged.size()-1);
                if (prev[1] >= interval[0]) {
                    // avoid shrinking
                    if (prev[1] < interval[1]) {
                        prev[1] = interval[1];
                    }
                } else {
                    merged.add(Arrays.copyOf(interval, interval.length));
                }
            }
        }
        return merged;
    }

    private static String listString(List<Integer[]> l) {
        StringBuilder x = new StringBuilder("{");
        for (Integer[] il : l) {
            x.append(Arrays.toString(il) + ", ");
        }
        x.append("}");
        return x.toString();
    }

    public static void main(String[] args) {
        List<Integer[]> input = new ArrayList<>();
        input.add(new Integer[]{1, 3});
        input.add(new Integer[]{2, 6});
        input.add(new Integer[]{8, 10});
        List<Integer[]> output = mergeIntervals(input);
        System.out.println(listString(input) + " -> " + listString(output));

        input = new ArrayList<>();
        input.add(new Integer[]{1, 10});
        input.add(new Integer[]{2, 3});
        output = mergeIntervals(input);
        System.out.println(listString(input) + " -> " + listString(output));

        input = new ArrayList<>();
        input.add(new Integer[]{1, 3});
        input.add(new Integer[]{2, 6});
        input.add(new Integer[]{8, 10});
        input.add(new Integer[]{1, 10});
        input.add(new Integer[]{2, 3});
        output = mergeIntervals(input);
        System.out.println(listString(input) + " -> " + listString(output));
    }
}
