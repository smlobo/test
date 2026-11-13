// Print a double as a binary (0.5 == 0.1; 0.25 == 0.01; 0.75 == 0.11)

import java.util.Scanner;

public class DoubleAsBinary {

    public static String doubleToBinary(double x) {
        StringBuilder sb = new StringBuilder();
        sb.append("0.");

        while (x > 0.0) {
            x *= 2;
            int nextDigit = (int) x;
            //String binary = String.format("%b", nextDigit);
            sb.append(nextDigit);
            x -= nextDigit;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner stdinScanner = new Scanner(System.in);

        System.out.print("Enter a double between 0.0 & 1.0: ");
        while (stdinScanner.hasNext()) {
            String nextLine = stdinScanner.nextLine();

            try {
                Double number = Double.parseDouble(nextLine);
                if (number < 0.0 || number > 1.0) {
                    System.out.println("Bad input: " + nextLine);
                }
                else {
                    String solution = doubleToBinary(number);
                    System.out.println("input: " + number + " -> " + 
                        solution);
                }
            }
            catch (Exception e) {
                System.out.println("Bad input: " + nextLine + "; " + e);
            }
            System.out.print("Enter a double between 0.0 & 1.0: ");
        }
    }
}