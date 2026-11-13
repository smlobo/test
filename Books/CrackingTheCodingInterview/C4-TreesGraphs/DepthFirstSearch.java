import java.util.ArrayDeque;
import java.util.Iterator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DepthFirstSearch {
    private int source;
    private Digraph digraph;
    private boolean[] reached;
    private int[] edgeTo;

    public DepthFirstSearch(int s, Digraph d) {
        source = s;
        digraph = d;

        reached = new boolean[d.vertices()];
        edgeTo = new int[d.vertices()];
        for (int i = 0; i < edgeTo.length; i++)
            edgeTo[i] = -1;

        // Run depth first search
        depthFirstSearch(source, source);
    }

    private void depthFirstSearch(int from, int to) {
        // Previously visited
        if (reached[to])
            return;

        // Mark & trace path
        reached[to] = true;
        edgeTo[to] = from;

        // Recurse
        Iterator<Integer> iterator = digraph.to(to);
        while (iterator.hasNext())
            depthFirstSearch(to, iterator.next());
    }

    public boolean reaches(int destination) {
        return reached[destination];
    }

    public Iterator<Integer> path(int destination) {
        if (!reached[destination])
            return null;

        ArrayDeque<Integer> path = new ArrayDeque<>();

        int vertex = destination;
        while (vertex != source) {
            path.push(vertex);
            vertex = edgeTo[vertex];
        }
        path.push(vertex);

        return path.iterator();
    }

    public static void main(String[] args) throws FileNotFoundException, 
            IOException {
        Digraph dg = new Digraph(new File(args[0]));

        // DFS for every vertex
        for (int i = 0; i < dg.vertices(); i++) {
            DepthFirstSearch dfs = new DepthFirstSearch(i, dg);
            System.out.println("Vertex " + i + ":");

            // Reach every other vertex
            for (int j = 0; j < dg.vertices(); j++) {
                if (i == j)
                    continue;

                System.out.print("  ");

                if (dfs.reaches(j)) {
                    Iterator<Integer> iterator = dfs.path(j);
                    while (iterator.hasNext())
                        System.out.print(iterator.next() + ", ");
                    System.out.println();
                }
                else {
                    System.out.println("Does not reach " + j);
                }
            }
        }
    }
}