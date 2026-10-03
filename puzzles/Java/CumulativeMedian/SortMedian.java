import java.util.Arrays;

public class SortMedian {
    public static double[] sortMedian(int[] array) {
        int[] workingArray = new int[array.length];
        double[] medianArray = new double[array.length];

        // Add elements to the working array
        for (int i = 0; i < array.length; i++) {
            workingArray[i] = array[i];

            // Sort this portion of the array
            int size = i + 1;
            Arrays.sort(workingArray, 0, size);

            // Find the median
            // Even number
            if (size % 2 == 0) {
                medianArray[i] = (workingArray[size/2-1] + workingArray[size/2]) / 2.0;
            }
            // Odd number
            else {
                medianArray[i] = workingArray[size/2];
            }
        }

        return medianArray;
    }
}