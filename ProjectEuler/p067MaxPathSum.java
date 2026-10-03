import java.io.*;
import java.util.*;

public class p067MaxPathSum {
    public static void main(String[] args) throws Exception {
        // Read all data into an ArrayList of Integer Array
        BufferedReader reader = new BufferedReader(
            new FileReader(
                new File(args[0])));
        ArrayList<Integer[]> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] sLine = line.split(" ");
            Integer[] iLine = new Integer[sLine.length];
            for (int i = 0; i < sLine.length; i++) {
                iLine[i] = Integer.parseInt(sLine[i]);
            }
            lines.add(iLine);
        }
        reader.close();

        // Recursively calculate cumulative sum for each row - bottom up
        maxPathSum(lines, 0);
        System.out.println(lines.get(0)[0]);
    }

    private static void maxPathSum(ArrayList<Integer[]> lines, int lineNum) {
        // Base case
        if (lineNum == lines.size()-1) {
            System.out.println("last row: " + lineNum);
            return;
        }

        // Calculate the next row
        maxPathSum(lines, lineNum+1);

        // Iterate getting the max from the below row for each element
        Integer[] line = lines.get(lineNum);
        Integer[] next = lines.get(lineNum+1);
        for (int i = 0; i < line.length; i++) {
            line[i] += Math.max(next[i], next[i+1]);
        }
    }
}