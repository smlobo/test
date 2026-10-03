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
     * Complete the 'minimumBribes' function below.
     *
     * The function accepts INTEGER_ARRAY q as parameter.
     */

    public static void minimumBribes(List<Integer> q) {
    // Write your code here
        q.add(0, 0);
        Integer[] p = new Integer[q.size()];
        p = q.toArray(p);
        
        int[] bribes = new int[p.length];
        int flips = 0;
        int sortedIndex = 1;
        boolean event = true;
        while (event) {
            // Assume no event occurs in this iteration
            event = false;
            System.out.println(Arrays.toString(p));
            // for (int i = sortedIndex; i < p.length; i++) {
            for (int i = 1; i < p.length; i++) {
                // if (i != p[i]) {
                    // sortedIndex = i;
                    if ((i != p[i] || p[i] > p[i+1]) && (p[i+1] < (i+1))) {
                        // undo the flip
                        flips++;
                        System.out.println("Flipping: " + i + ", <" + 
                            p[i] + "," + p[i+1] + ">");
                        bribes[p[i]] += 1;
                        if (bribes[p[i]] > 2) {
                            System.out.println("Too chaotic");
                            return;
                        }
                        int temp = p[i];
                        p[i] = p[i+1];
                        p[i+1] = temp;
                        event = true;
                        // break;
                    }
                // }
            }
        }
        System.out.println(flips);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> q = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                Result.minimumBribes(q);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
