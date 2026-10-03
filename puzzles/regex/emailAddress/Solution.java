/*

You will be provided with a block of text, spanning not more than hundred 
lines. Your task is to find the unique e-mail addresses present in the 
text. You could use Regular Expressions to simplify your task. And remember 
that the "@" sign can be used for a variety of purposes!

Input Format

The first line contains an integer N (N<=100), which is the number of 
lines present in the text fragment which follows. 
From the second line, begins the text fragment (of N lines) in which you 
need to search for e-mail addresses.

Output Format

All the unique e-mail addresses detected by you, in one line, in 
lexicographical order, with a semi-colon as the delimiter.

*/

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.TreeSet;
import java.util.Iterator;

class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();
		System.out.println("n = " + n);
		in.nextLine();

		Pattern p = Pattern.
			//compile("\\s[^\\s@]+@[^\\s@]+\\.[^\\s@]\\s");
			//compile("\\s[^\\s@]+@");
			//compile("\\s[^\\s@]+@[^\\s@]+(\\s|$)");
			//compile("\\b\\w+@\\w+\\.\\w+\\b");
			//compile("([^\\w\\.\\-]|^)([\\w\\.\\-]+@[\\w\\.\\-]+)([^\\w\\.\\-]|$)");
			compile("([^\\w\\.\\-]|^)(\\w[\\w\\.\\-]*@[\\w\\.\\-]*\\w)([^\\w\\-]|$)");

		TreeSet<String> ts = new TreeSet<>();

		for (int i = 0; i < n; i++) {
			String line = in.nextLine();
			//System.out.println(i + " : " + line);

			Matcher m = p.matcher(line);
			while (m.find()) {
				//System.out.println("~~~~ " + 
				//	line.substring(m.start(), m.end()));
				//System.out.println("++++ " + m.group(2));
				ts.add(m.group(2));
			}
		}

		Iterator i = ts.iterator();
		boolean first = true;
		while (i.hasNext()) {
			if (!first)
				System.out.print(";");
			else
				first = false;
			System.out.print(i.next());
		}
		System.out.println();
	}
}
