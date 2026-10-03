import java.util.Scanner;

public class ProperCase {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter string: ");
		String input = in.nextLine();

		System.out.println("S or P");
		String cc = in.nextLine();

		input = input.toLowerCase();

		StringBuilder output = new StringBuilder();
		output.append(input.substring(0, 1).toUpperCase());
		for (int i = 1; i < input.length(); i++) {
			//System.out.println(input.substring(i, i+1));
			if (input.substring(i-1, i).equals(" ") && 
				cc.equals("P")) {
				output.append(input.substring(i, i+1).
					toUpperCase());
				//System.out.println(output.toString());
			}
			else {
				output.append(input.substring(i, i+1));
				//System.out.println(output.toString());
			}
		}

		System.out.println(output.toString());
	}
}