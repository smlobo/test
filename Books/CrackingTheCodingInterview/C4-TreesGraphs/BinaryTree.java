public class BinaryTree<V> {
    private int depth;
    private V[] binaryHeap;

    public BinaryTree(int depth) {
        this.depth = depth;

        int size = (int) Math.pow(2, depth);
        System.out.println("2^" + depth + " = " + size);
        binaryHeap = (V[]) new Object[size];
    }

    public void put(int index, V value) {
        binaryHeap[index] = value;
    }

    private boolean[] path(int index) {
        int rank = 0;
        int parent = index;
        while (parent != 1) {
            rank++;
            parent /= 2;
        }
        //System.out.println("Rank of " + index + " = " + rank);

        boolean[] path = new boolean[rank];
        parent = index;
        for (int i = rank-1; i >= 0; i--) {
            if (parent % 2 == 0)
                path[i] = false;
            else
                path[i] = true;
            parent /= 2;
        }

        return path;
    }

    public void printPath(int index) {
        System.out.print("[" + index + "] " + binaryHeap[index] + " -> ");
        boolean[] path = path(index);
        for (int i = 0; i < path.length; i++) {
            if (path[i])
                System.out.print("R,");
            else
                System.out.print("L,");
        }
        System.out.println();
    }

    public void print() {
        int index = 1;

        // Spaces before each line
        int spaces = (int) Math.pow(2, depth);

        for (int i = 0; i < depth; i++) {
            // Spaces before
            spaces /= 2;

            // Number to print on each line
            int number = (int) Math.pow(2, i);

            for (int j = 0; j < number; j++) {
                // Actual spaces (0 is before, otherwise inbetween)
                int aSpaces = 0;
                if (j == 0)
                    aSpaces = spaces;
                else
                    aSpaces = spaces*2 - 1;

                // Print spaces
                for (int k = 0; k < aSpaces; k++)
                    System.out.print(" ");

                // Print next element
                V element = binaryHeap[index++];
                if (element != null)
                    System.out.print(element);
                else
                    System.out.print(" ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        BinaryTree<Character> bt = new BinaryTree(3);
        bt.put(1, 'A');
        bt.put(2, 'B');
        bt.put(3, 'C');
        bt.put(4, 'D');
        bt.put(5, 'E');
        bt.put(6, 'F');
        bt.put(7, 'G');
        bt.print();

        for (int i = 1; i <= 7; i++)
            bt.printPath(i);

        BinaryTree<Character> bt2 = new BinaryTree(5);
        bt2.put(1, 'A');
        bt2.put(3, 'C');
        bt2.put(6, 'D');
        bt2.put(7, 'E');
        bt2.put(12, 'F');
        bt2.put(14, 'G');
        bt2.put(25, 'H');
        bt2.put(28, 'I');
        bt2.put(2, 'B');
        bt2.put(5, 'J');
        bt2.put(10, 'K');
        bt2.put(11, 'L');
        bt2.put(20, 'M');
        bt2.print();
        bt2.printPath(25);
        bt2.printPath(28);
        bt2.printPath(20);
        bt2.printPath(10);
    }
}