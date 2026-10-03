/*
There is a special kind of apple tree that grows apples every day for n days. 
On the ith day, the tree grows apples[i] apples that will rot after days[i] 
days, that is on day i + days[i] the apples will be rotten and cannot be eaten. 
On some days, the apple tree does not grow any apples, which are denoted by 
apples[i] == 0 and days[i] == 0.

You decided to eat at most one apple a day (to keep the doctors away). Note that 
you can keep eating after the first n days.

Given two integer arrays days and apples of length n, return the maximum number 
of apples you can eat. 

Example 1:

Input: apples = [1,2,3,5,2], days = [3,2,1,4,2]
Output: 7
Explanation: You can eat 7 apples:
- On the first day, you eat an apple that grew on the first day.
- On the second day, you eat an apple that grew on the second day.
- On the third day, you eat an apple that grew on the second day. After this day, 
  the apples that grew on the third day rot.
- On the fourth to the seventh days, you eat apples that grew on the fourth day.

Example 2:

Input: apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
Output: 5
Explanation: You can eat 5 apples:
- On the first to the third day you eat apples that grew on the first day.
- Do nothing on the fouth and fifth days.
- On the sixth and seventh days you eat apples that grew on the sixth day.

*/

import java.util.*;

public class EatenApplesNew {
    public static void main(String[] args) {
        int[] apples;
        int[] days;
        int answer;

        // Test1
        apples = new int[] {1, 2, 3, 5, 2};
        days = new int[] {3, 2, 1, 4, 2};
        answer = eatenApples(apples, days);
        System.out.println(Arrays.toString(apples) + ":" + Arrays.toString(days) + 
            " = " + answer);
        assert answer == 7;

        // Test2
        apples = new int[] {3, 0, 0, 0, 0, 2};
        days = new int[] {3, 0, 0, 0, 0, 2};
        answer = eatenApples(apples, days);
        System.out.println(Arrays.toString(apples) + ":" + Arrays.toString(days) + 
            " = " + answer);
        assert answer == 5;

        // Test3
        apples = new int[] {1};
        days = new int[] {2};
        answer = eatenApples(apples, days);
        System.out.println(Arrays.toString(apples) + ":" + Arrays.toString(days) + 
            " = " + answer);
        assert answer == 1;

        // Test4
        apples = new int[] {2, 1, 10};
        days = new int[] {2, 10, 1};
        answer = eatenApples(apples, days);
        System.out.println(Arrays.toString(apples) + ":" + Arrays.toString(days) + 
            " = " + answer);
        assert answer == 4;

        // Test5
        apples = new int[] {5, 2, 3};
        days = new int[] {6, 9, 10};
        answer = eatenApples(apples, days);
        System.out.println(Arrays.toString(apples) + ":" + Arrays.toString(days) + 
            " = " + answer);
        assert answer == 10;

        // Test6
        apples = new int[] {8,8,3,6,8,1,1,1,1,5};
        days = new int[] {1,2,1,1,5,10,9,8,7,2};
        answer = eatenApples(apples, days);
        System.out.println(Arrays.toString(apples) + ":" + Arrays.toString(days) + 
            " = " + answer);
        assert answer == 15;
    }

    private static int eatenApples(int[] apples, int[] days) {
        TreeMap<Integer, Integer> cache = new TreeMap<>();
        int eatCount = 0;

        for (int i = 0; i < apples.length; i++) {
            // Add current apples to our sorted map
            int lastDay = i+days[i]-1;
            Integer existingApples = cache.get(lastDay);
            if (existingApples != null) {
                cache.put(lastDay, apples[i] + existingApples);
            }
            else {
                cache.put(lastDay, apples[i]);                
            }

            // Pick the soonest rotting
            Set<Map.Entry<Integer,Integer>> eligibleApples = cache.tailMap(i).entrySet();
            for (Map.Entry<Integer,Integer> entry : eligibleApples) {
                if (entry.getValue() > 0) {
                    // Eat it
                    eatCount++;
                    entry.setValue(entry.getValue()-1);
                    break;
                }
            }
        }

        // This ran out of time
        // Tail map of days past apple tree producing - keep eating
        // int dayCount = apples.length;
        // while (true) {
        //     Set<Map.Entry<Integer,Integer>> eligibleApples = cache.
        //         tailMap(dayCount).entrySet();
        //     if (eligibleApples.size() == 0)
        //         break;
        //     for (Map.Entry<Integer,Integer> entry : eligibleApples) {
        //         if (entry.getValue() > 0) {
        //             // Eat it
        //             eatCount++;
        //             // Update
        //             entry.setValue(entry.getValue()-1);
        //             break;
        //         }
        //     }

        //     dayCount++;
        // }

        // Iterate thru' available apple buckets only
        int dayCount = apples.length;
        for (Map.Entry<Integer,Integer> entry : cache.tailMap(apples.length).entrySet()) {
            int eligibleDays = entry.getKey() - dayCount + 1;
            int eligibleApples = entry.getValue();

            // Use the minimum
            if (eligibleDays < eligibleApples) {
                eatCount += eligibleDays;
                dayCount += eligibleDays;
            }
            else {
                eatCount += eligibleApples;
                dayCount += eligibleApples;
            }
        }

        return eatCount;
    }
}
