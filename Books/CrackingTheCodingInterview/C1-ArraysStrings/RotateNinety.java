import java.util.Random;

public class RotateNinety {
    private int[][] array;

    public RotateNinety(int n, String fill) {
        array = new int[n][n];

        if ("sequential".equals(fill)) {
            int count = 0;
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    array[i][j] = count++;
                }
            }
        }
        else if ("random".equals(fill)) {
            Random random = new Random();
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    array[i][j] = random.nextInt(n*n);
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + ", ");
            }
            System.out.println();
        }
    }

    private void swap(int r1, int c1, int r2, int c2) {
        int temp = array[r1][c1];
        array[r1][c1] = array[r2][c2];
        array[r2][c2] = temp;
    }

    private void swapColumn(int c1, int c2) {
        for (int i = 0; i < array.length; i++) {
            swap(i, c1, i, c2);
        }
    }

    public void transpose() {
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array[i].length; j++) {
                swap(i, j, j, i);
            }
        }
    }
    public void rotateNinety() {
        transpose();

        // Swap columns 0, n-1; 1, n-2; ...
        for (int i = 0; i < array.length/2; i++) {
            swapColumn(i, array.length-1-i);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java RotateNinety <n-dimension>");
            System.exit(1);
        }

        int dimension = Integer.parseInt(args[0]);
        System.out.println("Generating " + dimension + "x" + dimension + " matrix");

        RotateNinety rn1 = new RotateNinety(dimension, "sequential");
        System.out.println("rn1 Before: ");
        rn1.print();
        rn1.rotateNinety();
        System.out.println("rn1 After: ");
        rn1.print();

        RotateNinety rn2 = new RotateNinety(dimension, "random");
        System.out.println("rn2 Before: ");
        rn2.print();
        rn2.rotateNinety();
        System.out.println("rn2 After: ");
        rn2.print();
    }
}