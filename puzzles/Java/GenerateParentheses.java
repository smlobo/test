import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class GenerateParentheses {

	/* Generate all combinations of () based on input n.
		For 3, the output should be:
		[
  			"((()))",
  			"(()())",
  			"(())()",
  			"()(())",
  			"()()()"
		]
		My algorithm is for each n recurse over:
			(0), n-1
			(1), n-2
			(2), n-3
			...
			(n-1), 0
		No repetitions occur with this process
	*/
	public List<String> generateParenthesis(int n) {
		List<String> returnP = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			List<String> l1 = generateParenthesis(i);
			if (l1.isEmpty())
				l1.add("");

			List<String> l2 = generateParenthesis(n-i-1);
			if (l2.isEmpty())
				l2.add("");

			ListIterator<String> l1Iter = l1.listIterator();
			while (l1Iter.hasNext()) {
				String s1 = "(" + l1Iter.next() + ")";

				ListIterator<String> l2Iter = l2.listIterator();
				while (l2Iter.hasNext()) {
					String s = s1 + l2Iter.next();
					returnP.add(s);
				}
			}
		}

		return returnP;
	}

	private static void printList(List<String> l) {
		ListIterator<String> lIter = l.listIterator();
		while (lIter.hasNext())
			System.out.println(lIter.next());
	}

    public static void main(String[] args) {
    	if (args.length != 1) {
    		System.out.println("Usage: GenerateParentheses <n>");
    		return;
    	}

        GenerateParentheses gp = new GenerateParentheses();

        int n = Integer.parseInt(args[0]);
        List<String> pl = gp.generateParenthesis(n);
		System.out.println("Parentheses for : " + n + ", number = " + 
			pl.size());
		printList(pl);
    }
}