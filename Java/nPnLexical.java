/* Prints all permutations (nPn) of 0..n, in lexical order */

import java.util.Arrays;

public class nPnLexical {

	private static void swap(int[] x, int p, int q) {
		int temp = x[p];
		x[p] = x[q];
		x[q] = temp;
	}
	
	private static void printArray(int[] x) {
		for (int i =0; i < x.length; i++)
			System.out.print((char) (x[i] + 97) + ((i == x.length-1) ? "\n" : 
				" "));
	}

	private static void generate(int[] x, int n) {
		// Last array element - the next permutation is ready - print it
		if (n == 1) {
			//System.out.println(Arrays.toString(x));
			printArray(x);
			return;
		}

		// Every element in this array (subset) will be first
		for (int i = 0; i < n; i++) {

			// Recurse thro' its array subset
			generate(x, n-1);

			// Swap the 1st element with the ith element
			// For example:
			//       0 1 2 3
			//   i=0 1 0 2 3
			//   i=1 2 0 1 3
			//   i=2 3 0 1 2
			if (i != (n-1)) {
				swap(x, x.length-n, x.length-n+i+1);
			}

			// Last iteration, loop to revert back to the original order
			else {
				for (int j = 0; j < n-1; j++)
					swap(x, x.length-n+j, x.length-n+j+1);
			}
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java nPnLexical <n>");
			System.exit(1);
		}

		int n = Integer.parseInt(args[0]);
		//System.out.println("Generating " + n + "P" + n);

		int[] x = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = i;
		}

		generate(x, n);
	}
}