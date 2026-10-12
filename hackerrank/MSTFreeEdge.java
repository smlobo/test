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
    
    public static long calculateMinimumSpanningTreeWeightWithFreeEdge(int n, int m, List<List<Integer>> edges) {
        // Write your code here

        // Build free edge adj list
        class Edge {
            int to;
            int weight;
            
            Edge(int to, int weight) {
                this.to = to;
                this.weight = weight;
            }
        }
        List<List<Edge>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.size(); i++) {
            List<Integer> rawEdge = edges.get(i);
            int from = rawEdge.get(0);
            int to = rawEdge.get(1);
            int weight = rawEdge.get(2);
            adjList.get(from).add(new Edge(to, weight));
            adjList.get(to).add(new Edge(from, weight));
        }
        
        // Visited node array
        boolean[] visited = new boolean[n];
        int visitedCount = 0;

        // PQ
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        long mstWeight = 0;
        int largestEdge = 0;

        // Initialize
        visited[0] = true;
        visitedCount = 1;
        for (Edge e : adjList.get(0)) {
            pq.add(e);
        }
        
        // add to the mst
        while (!pq.isEmpty() && visitedCount < n) {
            Edge e = pq.poll();
            // System.out.println("[" + visitedCount + "] PQ candidate: " + e.to + "; " + e.weight);
            
            if (visited[e.to]) {
                continue;
            }
            
            mstWeight += e.weight;
            largestEdge = Math.max(largestEdge, e.weight);
            visited[e.to] = true;
            for (Edge newE : adjList.get(e.to)) {
                if (!visited[newE.to]) {
                    pq.add(newE);
                }
            }
        }
        
        return mstWeight - largestEdge;
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
