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
        
        TreeSet<Integer> nums = new TreeSet<>(arr);
        int max = 0;

        while (!nums.isEmpty()) {
            int ct = 0;
            for (int next = nums.first(); ; next = next + k) {
                if (nums.contains(next)) {
                    nums.remove(next);
                    ct++;
                }
                else {
                    break;
                }
            }
            if (max < ct) {
                max = ct;
            }
        }
        return max;
    }
}

public class ArithSubsequenceCopied {
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
