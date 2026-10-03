public class HeapsAlgorithm {

	private static int count = 0;

	private static void heapsAlgorithm(int[] a, int x) {
		//System.out.println("[" + count + ", " + x + "]  ");

		if (x == 1) {
			//System.out.print("[" + count++ + ", " + x + "]  ");
			for (int i=0; i<a.length; i++) {
				System.out.print(a[i]);
			}
			System.out.println();
			return;
		}
		for (int i = 0; i < (x-1); i++) {
			heapsAlgorithm(a, x-1);
			if (x % 2 == 0) {
				int temp = a[i];
				a[i] = a[x-1];
				a[x-1] = temp;
			}
			else {
				int temp = a[0];
				a[0] = a[x-1];
				a[x-1] = temp;
			}
		}
		heapsAlgorithm(a, x-1);
	}
	public static void main(String[] args) {
		int x = Integer.parseInt(args[0]);

		System.out.println("Number is: " + x);

		int[] a = new int[x];
		for (int i = 0; i < x; i++) {
			a[i] = i;
			//System.out.print(a[i]);
		}
		//System.out.println();

		heapsAlgorithm(a, x);
	}
}