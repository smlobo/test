import java.util.LinkedList;
import java.util.Iterator;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Digraph {
    private int numEdges;
    private LinkedList<Integer>[] adjList;

    public Digraph(int n) {
        adjList = new LinkedList[n];
        for (int i = 0; i < n; i++)
            adjList[i] = new LinkedList<Integer>();
    }

    public Digraph(File file) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        int vertices = Integer.parseInt(reader.readLine());
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++)
            adjList[i] = new LinkedList<Integer>();

        int edges = Integer.parseInt(reader.readLine());
        for (int i = 0; i < edges; i++) {
            String fromTo = reader.readLine();
            //System.out.println(fromTo);
            String[] tokens = fromTo.trim().split(" +");
            //System.out.println(tokens[0] + " -> " + tokens[1]);

            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            link(from, to);
        }
    }

    public void link(int from, int to) {
        adjList[from].add(to);
        numEdges++;
    }

    public int vertices() {
        return adjList.length;
    }

    public int edges() {
        return numEdges;
    }

    public Iterator<Integer> to(int from) {
        return adjList[from].iterator();
    }

    public static void main(String[] args) throws FileNotFoundException, 
            IOException {
        Digraph dg = new Digraph(new File(args[0]));

        System.out.println("Build Directed Graph with: " + dg.vertices() + 
            " vertices, and " + dg.edges() + " edges.");
    }
}