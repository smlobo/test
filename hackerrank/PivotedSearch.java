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
     * Complete the 'searchRotatedTimestamps' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY nums
     *  2. INTEGER target
     */

    public static int searchRotatedTimestamps(List<Integer> nums, int target) {
        // Write your code here
        
        // base case
        if (nums.isEmpty()) {
            return -1;
        }
        
        // Linear scan to find pivot
        // int pivotIndex = 0;
        // for (int i = 1; i < nums.size(); i++) {
        //     // Pivot found
        //     if (nums.get(i-1) > nums.get(i)) {
        //         pivotIndex = i;
        //         break;
        //     }
        // }
        // binary search to find the pivot
        int left = 0;
        int right = nums.size() - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;

            if (nums.get(mid) > nums.get(right)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int pivotIndex = left;
        
        // binary search the respective interval
        int foundIndex = -1;
        boolean inUpper = false;
        if (target >= nums.get(pivotIndex) && target <= nums.get(nums.size()-1)) {
            foundIndex = Collections.binarySearch(nums.subList(pivotIndex, nums.size()), target);
            inUpper = true;
        } else {
            foundIndex = Collections.binarySearch(nums.subList(0, pivotIndex), target);            
        }

        if (foundIndex >= 0) {
            if (inUpper) {
                return foundIndex + pivotIndex;
            }
            return foundIndex;
        }
        return -1;
    }

}

public class PivotedSearch {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int numsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> nums = IntStream.range(0, numsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int target = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.searchRotatedTimestamps(nums, target);

        System.out.println(result);

        bufferedReader.close();
    }
}
