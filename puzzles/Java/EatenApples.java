import java.util.ArrayList;
import java.util.Arrays;

public class EatenApples {
    public static void main(String[] args) {
        int[] apples;
        int[] days;
        int answer;

        // Test1
        apples = new int[] {1, 2, 3, 5, 2};
        days = new int[] {3, 2, 1, 4, 2};
        // answer = eatenApples(apples, days);
        // System.out.println(Arrays.toString(apples) + ":" + Arrays.toString(days) + 
            // " = " + answer);

        // Test2
        apples = new int[] {3, 0, 0, 0, 0, 2};
        days = new int[] {3, 0, 0, 0, 0, 2};
        // answer = eatenApples(apples, days);
        // System.out.println(Arrays.toString(apples) + ":" + Arrays.toString(days) + 
            // " = " + answer);

        // Test3
        apples = new int[] {1};
        days = new int[] {2};
        // answer = eatenApples(apples, days);
        // System.out.println(Arrays.toString(apples) + ":" + Arrays.toString(days) + 
            // " = " + answer);

        // Test4
        apples = new int[] {2, 1, 10};
        days = new int[] {2, 10, 1};
        answer = eatenApples(apples, days);
        System.out.println(Arrays.toString(apples) + ":" + Arrays.toString(days) + 
            " = " + answer);
    }

    private static int eatenApples(int[] apples, int[] days) {
        ArrayList<Integer> cache = new ArrayList<>(apples.length);
        int eatCount = 0;

        for (int i = 0; i < apples.length; i++) {
            // Check new apples first
            if (apples[i] > 0) {
                // Eat one
                eatCount++;

                // Initialize the cache until the last day the remaining apples 
                // are edible
                // Don't bother for 0 apples
                if (apples[i] == 1)
                    continue;
                for (int j = cache.size(); j < i + days[i]; j++) {
                    cache.add(0);
                }

                // Write remainder of apples to the cache
                for (int j = 1; j < days[i]; j++) {
                    int k = i + j;
                    cache.set(k, cache.get(k) + apples[i] - 1);
                    System.out.println("    cache [" + k + "] " + cache.get(k));
                }
            }

            // Eat an old apple (if available)
            else if (cache.size() > i && cache.get(i) > 0) {
                eatCount++;

                // Update the cache to reflect this eaten apple
                for (int j = i+1; j < cache.size(); j++) {
                    cache.set(j, cache.get(j)-1);
                }
            }
            System.out.println("  [" + i + "] " + eatCount);
        }

        // Remaining days after the apple tree stops producing
        System.out.println("reamining days: " + cache.size());
        if (cache.size() > apples.length)
            eatCount += cache.size() - apples.length;

        return eatCount;
    }
}
