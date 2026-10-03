public class nPr {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int r = Integer.parseInt(args[1]);
		System.out.println("Generating " + n + "P" + r);

		int[] x = new int[r];
		int[] y = new int[r];
		for (int i = 0; i < r; i++) {
			x[i] = i;
			y[i] = n-(r-i);
		}

		while (true) {
			System.out.print("  ");
			for (int i = 0; i < r; i++) {
				System.out.print(x[i] + ", ");
			}
			System.out.println();

			// Increment
			x[r-1]++;
			for (int i = r-2; i >= 0; i--) {
				if (x[i+1] > y[i+1]) {
					x[i]++;
				}
			}

			// End loop condition
			if (x[0] > y[0]) {
				break;
			}

			// Adjust
			for (int i = 1; i < r; i++) {
				if (x[i] > y[i]) {
					x[i] = x[i-1] + 1;
				}
			}
		}
	}
}