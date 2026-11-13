// Swap alternate bits of an integer

import java.util.Scanner;

public class SwapIntBits {

    private static int swapAltBits(int x) {
        int rValue = 0;

        // Assume +ve number
        int bitCount = 0;
        while (x > 0) {
            int evenBit = x & 0x1;
            int oddBit = (x & 0x2) >> 1;
            x >>= 2;

            int newNumber = (evenBit << 1) | oddBit;
            rValue |= (newNumber << bitCount);

            bitCount += 2;
        }

        return rValue;
    }

    private static String asBinary(int x) {
        StringBuilder sb = new StringBuilder();

        // Assume +ve numbers
        while (x > 0) {
            int firstBit = x & 0x1;
            sb.insert(0, firstBit);
            x >>= 1;
        }

        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner stdinScanner = new Scanner(System.in);

        System.out.print("Enter an integer: ");
        while (stdinScanner.hasNext()) {
            String line = stdinScanner.nextLine();

            try {
                int number = Integer.parseInt(line);
                int swap = swapAltBits(number);
                System.out.println(number + " {" + asBinary(number) + "} -> " + 
                    swap + " {" + asBinary(swap) + "}");
            }
            catch (Exception e) {
                System.out.println("Bad input: " + line + "; " + e);
            }

            System.out.print("Enter an integer: ");
        }
    }
}