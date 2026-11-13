// Implement -, *, / with only +

import java.util.Scanner;

public class ArithmeticWithPlus {
    
    private static int negate(int a) {
        int d = a > 0 ? -1 : 1;
        int negative = 0;

        while (a != 0) {
            a += d;
            negative += d;
        }

        return negative;
    }

    private static int subtract(int a, int b) {
        return a + negate(b);
    }

    private static int multiply(int a, int b) {
        int counter = b < 0 ? negate(b) : b;
        int result = 0;
        for (int i = 0; i < counter; i++)
            result += a;

        // Adjust sign of the result, based on 'b'
        if (b < 0)
            return negate(result);

        // Else, no need to adjust
        return result;
    }

    private static int divide(int a, int b) {
        int count = 0;

        int positiveNumerator = a < 0 ? negate(a) : a;
        int positiveDenominator = b < 0 ? negate(b) : b;
        int negativeDenominator = b < 0 ? b : negate(b);

        while (positiveNumerator >= positiveDenominator) {
            positiveNumerator += negativeDenominator;
            count++;
        }

        if (a < 0 && b > 0 || a > 0 && b < 0)
            return negate(count);

        return count;
    }

    public static void main(String[] args) {
        Scanner stdinScanner = new Scanner(System.in);

        System.out.print("Enter -, *, / int expression: ");
        while (stdinScanner.hasNext()) {
            String expression = stdinScanner.nextLine();
            String[] tokens = expression.split(" ");

            try {
                int a = Integer.parseInt(tokens[0]);
                int b = Integer.parseInt(tokens[2]);

                int result = 0;
                switch (tokens[1]) {
                    case "-":
                    result = subtract(a, b);
                    assert(result == a-b);
                    break;
                    case "*":
                    result = multiply(a, b);
                    assert(result == a*b);
                    break;
                    case "/":
                    result = divide(a, b);
                    assert(result == (int)a/b);
                    break;
                    default:
                    throw new Exception("Bad operator: " + tokens[1]);
                }
                System.out.println(expression + " = " + result);
            }
            catch (Exception e) {
                System.out.println("Bad expression: " + expression + "; " + e);
            }
            System.out.print("Enter another -, *, / int expression: ");
        }

        System.out.println("Thanks for playing!");
    }
}