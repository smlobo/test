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
     * Complete the 'calculateMinimumSpanningTreeWeightWithFreeEdge' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     *  3. 2D_INTEGER_ARRAY edges
     */

    private static class DisjointSet {
        private final int[] parent;
        private final int[] size;

        DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int node) {
            if (parent[node] != node) {
                parent[node] = find(parent[node]);
            }
            return parent[node];
        }

        boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return false;
            }
            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
            return true;
        }
    }
    
    public static long calculateMinimumSpanningTreeWeightWithFreeEdge(int n, int m, List<List<Integer>> edges) {
        if (n <= 1) {
            return 0;
        }

        edges.sort(Comparator.comparingInt(edge -> edge.get(2)));
        DisjointSet components = new DisjointSet(n);
        long mstWeight = 0;
        int largestTreeEdge = Integer.MIN_VALUE;
        int chosenEdges = 0;

        for (List<Integer> edge : edges) {
            int weight = edge.get(2);
            if (components.union(edge.get(0), edge.get(1))) {
                mstWeight += weight;
                largestTreeEdge = weight;
                if (++chosenEdges == n - 1) {
                    break;
                }
            }
        }

        if (chosenEdges != n - 1) {
            return -1;
        }
        return mstWeight - Math.max(0, largestTreeEdge);
    }

}

public class MSTFreeEdge {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        int edgesRows = Integer.parseInt(bufferedReader.readLine().trim());
        int edgesColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> edges = new ArrayList<>();

        IntStream.range(0, edgesRows).forEach(i -> {
            try {
                edges.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result = Result.calculateMinimumSpanningTreeWeightWithFreeEdge(n, m, edges);

        System.out.println(result);

        bufferedReader.close();
    }
}
