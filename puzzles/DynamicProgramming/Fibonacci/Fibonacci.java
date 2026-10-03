
public class Fibonacci {
    private static int n2 = 1;

    private static int fiboNaive(int n) {
        // Base case
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;

        return fiboNaive(n-1) + fiboNaive(n-2);
    }

    private static int fibo(int n) {
        // Base case
        if (n == 0)
            return 0;

        int n1 = fibo(n-1);
        int retValue = n1 + n2;
        n2 = n1;

        return retValue;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: ...");
            System.exit(1);
        }

        int n = Integer.parseInt(args[0]);

        long elapsed = System.currentTimeMillis();
        int a = fibo(n);
        elapsed = System.currentTimeMillis() - elapsed;
        System.out.println("The " + n + " Fibonacci: " + a + " ; took: " + elapsed);

        elapsed = System.currentTimeMillis();
        a = fiboNaive(n);
        elapsed = System.currentTimeMillis() - elapsed;
        System.out.println("The " + n + " Fibonacci: " + a + " ; took: " + elapsed);
    }
}