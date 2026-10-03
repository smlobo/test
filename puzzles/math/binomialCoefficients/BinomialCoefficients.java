/*
In mathematics, binomial coefficients are a family of positive 
integers that occur as coefficients in the binomial theorem.

	(n)
	(k) 

denotes the number of ways of choosing k objects from n different 
objects.

However when n and k are too large, we often save them after modulo 
operation by a prime number P. Please calculate how many binomial 
coefficients of n become to 0 after modulo by P.

Input Format 
The first of input is an integer T, the number of test cases. 
Each of the following T lines contains 2 integers, n and prime P.

Constraints 
 T < 100
 n < 10^500
 P < 10^9 

Output Format

For each test case, output a line contains the number of (nk)s 
(0<=k<=n)  each of which after modulo operation by P is 0.

Sample Input
3
2 2
3 2
4 3

Sample Output
1
0
1
*/

import java.util.Scanner;

public class BinomialCoefficients {

	static int binomialCoefficient(int n, int r) {
		int result = 1;

		// Corner case
		if (n == 0) {
			return result;
		}

		// Numerator
		for (int i = 0; i < r; i++) {
			result *= (n-i);
		}

		// Denominator
		for (int i = 1; i <= r; i++) {
			result /= i;
		}

		return result;
	}

	static int solve(String n, int p) {
		System.out.println("Solving: " + n + " for " + p);

		int nn = Integer.parseInt(n);

		int result = 0;

		// Iterate over all r calculating the binomial coefficient
		for (int r = 0; r <= nn; r++) {
			//int bc = binomialCoefficient(nn, r);
			int bc = 0;
			System.out.println(n + "C" + r + "= " + bc);
			if (bc % p == 0) {
				result++;
			}
		}

		return result;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int t = scanner.nextInt();
		scanner.skip("[\r\n]+");

		for (int i = 0; i < t; i++) {
			String[] line = scanner.nextLine().split(" ");
			System.out.println(solve(line[0], Integer.parseInt(line[1])));
		}
	}
}