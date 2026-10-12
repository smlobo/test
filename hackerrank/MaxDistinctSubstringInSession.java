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
     * Complete the 'maxDistinctSubstringLengthInSessions' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING sessionString as parameter.
     */

    public static int maxDistinctSubstringLengthInSessions(String sessionString) {
        // Write your code here

        // array of each letter's last found index
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        
        int max = 0;
        int windowStart = 0;
        
        for (int i = 0; i < sessionString.length(); i++) {
            char curr = sessionString.charAt(i);

            if (curr == '*') {
                windowStart = i + 1;
                continue;
            }
            
            int offset = curr - 'a';
            if (lastSeen[offset] >= windowStart) {
                windowStart = lastSeen[offset] + 1;
            }
            lastSeen[offset] = i;
            max = Math.max(max, i - windowStart + 1);
        }
        
        return max;
    }

}

public class MaxDistinctSubstringInSession {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String sessionString = bufferedReader.readLine();

        int result = Result.maxDistinctSubstringLengthInSessions(sessionString);

        System.out.println(result);

        bufferedReader.close();
    }
}
