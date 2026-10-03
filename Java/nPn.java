/* Prints all permutations (nPn) of 0..n*/

import java.util.Arrays;

public class nPn {

	private static void swap(int[] x, int p, int q) {
		//System.out.println("Swap: " + Arrays.toString(x) + " - " + p + 
		//	", " + q);
		int temp = x[p];
		x[p] = x[q];
		x[q] = temp;
	}

	private static void putback(int[] x, int n, int i) {
		//System.out.println("Putback: " + Arrays.toString(x) + " - " + n + 
		//	", " + i);
		int temp = x[x.length-n];
		x[x.length-n] = x[x.length-n+i];
		x[x.length-n+i] = temp;
		//System.out.println("Putback done: " + Arrays.toString(x));
	}
	
	private static void generate(int[] x, int n) {
		for (int i = 0; i < n; i++) {
			if (n == 1) {
				System.out.println(Arrays.toString(x));
			}
			else {
				generate(x, n-1);
				if (i > 0) {
					putback(x, n, i);
				}
				if (i < (n-1)) {
					//System.out.print(n + " -> ");
					swap(x, x.length-n, x.length-n+i+1);
				}
			}
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java nPn <n>");
			System.exit(1);
		}

		int n = Integer.parseInt(args[0]);
		System.out.println("Generating " + n + "P" + n);

		int[] x = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = i;
		}

		generate(x, n);
	}
}