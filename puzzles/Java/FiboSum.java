/*
*/

import java.util.*;

public class FiboSum {
    public int findMinFibonacciNumbers(int k) {
        // Generate Fibo numbers <= k in a table
        ArrayList<Integer> fibos = new ArrayList<>();
        fibos.add(1);
        fibos.add(1);
        while (fibos.get(fibos.size()-1) < k) {
            int nextFibo = fibos.get(fibos.size()-1) + fibos.get(fibos.size()-2);
            fibos.add(nextFibo);
        }
        Integer[] fiboArray = new Integer[fibos.size()];
        fiboArray = fibos.toArray(fiboArray);
        System.out.println(Arrays.toString(fiboArray));
        
        // Binary search for the target
        int target = k;
        int count = 0;
        int upperBound = fiboArray.length;
        while (target > 0) {
            int foundIndex = Arrays.binarySearch(fiboArray, 0, upperBound, target);
            if (foundIndex >= 0) {
                target = 0;
            }
            else {
                int nextBest = -foundIndex - 2;
                target -= fiboArray[nextBest];
                upperBound = nextBest;
            }
            count++;
        }
        
        return count;
    }

    public static void main(String[] args) {
        FiboSum fs = new FiboSum();

        // Test 1
        System.out.println("7 answer: " + fs.findMinFibonacciNumbers(7));
        // Test 2
        System.out.println("10 answer: " + fs.findMinFibonacciNumbers(10));
        // Test 3
        System.out.println("19 answer: " + fs.findMinFibonacciNumbers(19));
        // Test 4
        System.out.println("991244035 answer: " + fs.findMinFibonacciNumbers(991244035));

    }

}