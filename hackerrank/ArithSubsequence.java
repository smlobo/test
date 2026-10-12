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
     * Complete the 'findLongestArithmeticProgression' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY arr
     *  2. INTEGER k
     */

    public static int findLongestArithmeticProgression(List<Integer> arr, int k) {
        // Write your code here
        // empty array
        if (arr.isEmpty()) {
            return 0;
        }
        
        // sort the list
        arr.sort(Comparator.naturalOrder());
        System.out.println("Sorted arr: " + arr);
        System.out.println("k: " + k);
        // de-duplicate
        ArrayList<Integer> dedup = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (!dedup.isEmpty() && (dedup.get(dedup.size()-1) == arr.get(i))) {
                continue;
            } else {
                dedup.add(arr.get(i));
            }
        }
        System.out.println("Dedup: " + dedup);
        
        // chain of found interval
        ArrayList<Integer> indexChain = new ArrayList<>(
            Collections.nCopies(dedup.size(), 0));
        for (int i = 0; i < dedup.size()-1; i++) {
            int target = dedup.get(i) + k;
            // binary search for target in the remaining arr
            int foundIndex = Collections.binarySearch(dedup, target);
            if (foundIndex >= 0) {
                indexChain.set(i, foundIndex);
            }
        }
        System.out.println("indexChain: " + indexChain);
        
        // find the longest chain
        int longestChain = 1;
        for (int i = 0; i < indexChain.size()-1; i++) {
            // iterate over chain
            int currLength = 1;
            int link = indexChain.get(i);
            while (link != 0) {
                currLength++;
                link = indexChain.get(link);
            }
            if (currLength > longestChain) {
                longestChain = currLength;
            }
        }
        
        return longestChain;
    }

}

public class ArithSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(System.in));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, arrCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.findLongestArithmeticProgression(arr, k);

        System.out.println(result);

        bufferedReader.close();
    }
}
