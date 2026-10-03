import java.util.*;

public class MinPathSum {

    private class Node implements Comparable<Node> {
        public int x;
        public int y;
        public int weight;

        public Node(int j, int i, int w) {
            x = i;
            y = j;
            w = weight;
        }

        public int compareTo(Node that) {
            if (this.x == that.x &&
                this.y == that.y)
                return 0;
            else if (this.weight < that.weight)
                return -1;
            else if (this.weight > that.weight)
                return 1;
            else if ((this.x < that.x &&
                      this.y <= that.y) ||
                     (this.x <= that.x &&
                      this.y < that.y))
                return -1;
            return 1;
        }
    }

    public int minPathSum(int[][] grid) {
        int p = grid.length;
        int q = 0;
        int[][] fg = new int[grid.length][];
        for (int i = 0; i < p; i++) {
            q = grid[i].length;
            fg[i] = new int[q];
            for (int j = 0; j < q; j++)
                fg[i][j] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(p*q);

        fg[0][0] = grid[0][0];
        pq.add(new Node(0, 0, fg[0][0]));

        while (pq.size() > 0) {
            Node n = pq.poll();

            System.out.println("Node: [" + n.y + "," + n.x + "] " +
                               n.weight + " : " + grid[n.y][n.x] +
                               " <-> " + fg[n.y][n.x]);

            // Adjacent nodes
            // Right
            if (n.x < (q-1)) {
                //relax(n.x+1, n.y, grid, fg);
                if (fg[n.y][n.x+1] > (fg[n.y][n.x] + grid[n.y][n.x+1])) {
                    fg[n.y][n.x+1] = fg[n.y][n.x] + grid[n.y][n.x+1];
                    //pq.remove(new Node(n.y, n.x+1, 0));
                    pq.add(new Node(n.y, n.x+1, fg[n.y][n.x+1]));
                }
            }
            
            // Down
            if (n.y < (p-1)) {
                //relax(n.x, n.y+1, grid, fg);
                if (fg[n.y+1][n.x] > (fg[n.y][n.x] + grid[n.y+1][n.x])) {
                    fg[n.y+1][n.x] = fg[n.y][n.x] + grid[n.y+1][n.x];
                    //pq.remove(new Node(n.y+1, n.x, 0));
                    pq.add(new Node(n.y+1, n.x, fg[n.y+1][n.x]));
                }
            }
            
            // Left
            //if (n.x > 0) {
                //relax(n.x-1, n.y, grid, fg);
            //  if (fg[n.y][n.x-1] > (fg[n.y][n.x] + grid[n.y][n.x-1])) {
            //      fg[n.y][n.x-1] = fg[n.y][n.x] + grid[n.y][n.x-1];
            //      pq.remove(new Node(n.y, n.x-1, 0));
            //      pq.add(new Node(n.y, n.x-1, fg[n.y][n.x-1]));
            //  }
            //}
            
            // Up
            //if (n.y > 0) {
                //relax(n.x, x.y-1, grid, fg);
            //  if (fg[n.y-1][n.x] > (fg[n.y][n.x] + grid[n.y-1][n.x])) {
            //      fg[n.y-1][n.x] = fg[n.y][n.x] + grid[n.y-1][n.x];
            //      pq.remove(new Node(n.y-1, n.x, 0));
            //      pq.add(new Node(n.y-1, n.x, fg[n.y-1][n.x]));
            //  }
            //}
        }

        return fg[p-1][q-1];
    }

    public static void main(String[] args) {
        MinPathSum mps = new MinPathSum();

        int[][] g1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Min path: " + mps.minPathSum(g1));

        int[][] g2 = {{1, 2}, {4, 5}, {7, 8}};
        System.out.println("Min path: " + mps.minPathSum(g2));

        int[][] g3 = 
            {{5,4,2,9,6,0,3,5,1,4,9,8,4,9,7,5,1},
             {3,4,9,2,9,9,0,9,7,9,4,7,8,4,4,5,8},
             {6,1,8,9,8,0,3,7,0,9,8,7,4,9,2,0,1},
             {4,0,0,5,1,7,4,7,6,4,1,0,1,0,6,2,8},
             {7,2,0,2,9,3,4,7,0,8,9,5,9,0,1,1,0},
             {8,2,9,4,9,7,9,3,7,0,3,6,5,3,5,9,6},
             {8,9,9,2,6,1,2,5,8,3,7,0,4,9,8,8,8},
             {5,8,5,4,1,5,6,6,3,3,1,8,3,9,6,4,8},
             {0,2,2,3,0,2,6,7,2,3,7,3,1,5,8,1,3},
             {4,4,0,2,0,3,8,4,1,3,3,0,7,4,2,9,8},
             {5,9,0,4,7,5,7,6,0,8,3,0,0,6,6,6,8},
             {0,7,1,8,3,5,1,8,7,0,2,9,2,2,7,1,5},
             {1,0,0,0,6,2,0,0,2,2,8,0,9,7,0,8,0},
             {1,1,7,2,9,6,5,4,8,7,8,5,0,3,8,1,5},
             {8,9,7,8,1,1,3,0,1,2,9,4,0,1,5,3,1},
             {9,2,7,4,8,7,3,9,2,4,2,2,7,8,2,6,7},
             {3,8,1,6,0,4,8,9,8,0,2,5,3,5,5,7,5},
             {1,8,2,5,7,7,1,9,9,8,9,2,4,9,5,4,0},
             {3,4,4,1,5,3,3,8,8,6,3,5,3,8,7,1,3}};
        System.out.println("Min path: " + mps.minPathSum(g3));
    }
}
