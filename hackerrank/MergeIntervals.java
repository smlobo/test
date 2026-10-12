import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'mergeHighDefinitionIntervals' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts 2D_INTEGER_ARRAY intervals as parameter.
     */

    public static List<List<Integer>> mergeHighDefinitionIntervals
        (List<List<Integer>> intervals) {

        // Write your code here
        // corner case empty list
        List<List<Integer>> retList = new ArrayList<>();
        if (intervals.isEmpty()) {
            return retList;
        }
        // sort the intervals by 1st element
        intervals.sort(Comparator.comparingInt(inner -> inner.get(0)));
        
        // Push the 1st interval to the return list
        retList.add(intervals.get(0));

        // Iterate and merge
        for (int i = 1; i < intervals.size(); i++) {
            List<Integer> prevInterval = retList.get(retList.size()-1);
            List<Integer> currInterval = intervals.get(i);

            // No overlap
            if (prevInterval.get(1) < currInterval.get(0)) {
                retList.add(currInterval);
                continue;
            }
            
            // Overlap
            // already bigger
            if (prevInterval.get(1) >= currInterval.get(1)) {
                continue;
            }
            // make bigger
            retList.get(retList.size()-1).set(1, currInterval.get(1));
        }
        
        return retList;
    }

}

public class MergeIntervals {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in));

        int intervalsRows = Integer.parseInt(bufferedReader.readLine().trim());
        // int intervalsColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> intervals = new ArrayList<>();

        IntStream.range(0, intervalsRows).forEach(i -> {
            try {
                intervals.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<List<Integer>> result = 
            Result.mergeHighDefinitionIntervals(intervals);

        result.stream()
            .map(
                r -> r.stream()
                    .map(Object::toString)
                    .collect(joining(" "))
            )
            .collect(toList())
            .forEach(System.out::println);

        bufferedReader.close();
    }
}
