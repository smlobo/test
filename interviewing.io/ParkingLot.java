/*
  [[INF, -1, 0, INF],
   [INF, INF, INF, -1],
   [INF, -1, INF, -1],
   [0, -1, INF, INF]]

  [[3, -1, 0, 1],
   [2, 2, 1, -1],
   [1, -1, 2, -1],
   [0, -1, 3, 4]]
*/

import java.util.PriorityQueue;

public class ParkingLot {

    public static void printArray(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + ", ");
            }
            System.out.println();
        }
    }

    static class Node implements Comparable<Node> {
        int row;
        int col;
        int value;

        public Node(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        public int compareTo(Node that) {
            return this.value - that.value;
        }
    }

    public static void solution(int[][] array) {
        if (array == null || array.length < 1 || 
            array[0] == null || array[0].length < 1)
            return;

        PriorityQueue<Node> pQ = new PriorityQueue<>();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0) {
                    pQ.add(new Node(i, j, 0));
                }
            }
        }

        while (!pQ.isEmpty()) {
            Node node = pQ.poll();

            // Check 4 possible neighbors
            // Up
            if (node.row-1 >= 0 && 
                array[node.row-1][node.col] == Integer.MAX_VALUE) {
                pQ.add(new Node(node.row-1, node.col, node.value+1));
                array[node.row-1][node.col] = node.value+1;
            }
            // Down
            if (node.row+1 < array.length && 
                array[node.row+1][node.col] == Integer.MAX_VALUE) {
                pQ.add(new Node(node.row+1, node.col, node.value+1));
                array[node.row+1][node.col] = node.value+1;
            }
            // Left
            if (node.col-1 >= 0 && 
                array[node.row][node.col-1] == Integer.MAX_VALUE) {
                pQ.add(new Node(node.row, node.col-1, node.value+1));
                array[node.row][node.col-1] = node.value+1;
            }
            // Right
            if (node.col+1 < array[0].length && 
                array[node.row][node.col+1] == Integer.MAX_VALUE) {
                pQ.add(new Node(node.row, node.col+1, node.value+1));
                array[node.row][node.col+1] = node.value+1;
            }
        }
    }

    public static void main(String[] args) {
        int[][] array = new int[4][4];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = Integer.MAX_VALUE;
            }
        }
        array[0][1] = -1;
        array[0][2] = 0;
        array[1][3] = -1;
        array[2][1] = -1;
        array[2][3] = -1;
        array[3][0] = 0;
        array[3][1] = -1;
        printArray(array);
        solution(array);
        printArray(array);
    }
}