/*
Print a binary tree in an m*n 2D string array following these rules:

* The row number m should be equal to the height of the given binary 
  tree. 
* The column number n should always be an odd number.
* The root node's value (in string format) should be put in exactly 
  the middle of the first row it can be put. The column and the row 
  where the root node belongs will separate the rest space into two 
  parts (left-bottom part and right-bottom part). You should print 
  the left subtree in the left-bottom part and print the right 
  subtree in the right-bottom part. The left-bottom part and the 
  right-bottom part should have the same size. Even if one subtree 
  is none while the other is not, you don't need to print anything 
  for the none subtree but still need to leave the space as large as 
  that for the other subtree. However, if two subtrees are none, then 
  you don't need to leave space for both of them.
* Each unused space should contain an empty string "".
* Print the subtrees following the same rules.

Example 1:
Input:
     1
    /
   2
Output:
[["", "1", ""],
 ["2", "", ""]]
Example 2:
Input:
     1
    / \
   2   3
    \
     4
Output:
[["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]
Example 3:
Input:
      1
     / \
    2   5
   / 
  3 
 / 
4 
Output:

[["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
*/

import java.util.List;
import java.util.ArrayList;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) {
		val = x;
	}
	TreeNode(int x, TreeNode l, TreeNode r) {
		val = x;
		left = l;
		right = r;
	}
}

class PrintBinaryTree {

	private int findDepth(TreeNode n, int d) {
		if (n == null) {
			return d;
		}

		int dLeft = findDepth(n.left, d+1);
		int dRight = findDepth(n.right, d+1);

		return Math.max(dLeft, dRight);
	}

	private List<String> columnArray(int d) {
		int size = (int) Math.pow(2, d) - 1;
		//System.out.println("Size: " + size);
		List<String> s = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			s.add("");
		}
		return s;
	}

	private List<List<String>> create2DArray(int d) {
		List<List<String>> s = new ArrayList<>(d);
		for (int i = 0; i < d; i++) {
			s.add(columnArray(d));
		}
		return s;
	}

	private void insertValue(List<List<String>> s, TreeNode n, 
		int r, int lo, int hi) {
		if (n == null) {
			return;
		}

		List<String> row = (List<String>) s.get(r);
		int column = (hi - lo) / 2 + lo;
		row.set(column, "" + n.val);

		insertValue(s, n.left, r+1, lo, column);
		insertValue(s, n.right, r+1, column+1, hi);
	}

	public List<List<String>> printTree(TreeNode root) {
		int d = findDepth(root, 0);
		//System.out.println("Depth: " + d);

		List<List<String>> s = create2DArray(d);

		int size = (int) Math.pow(2, d) - 1;
		insertValue(s, root, 0, 0, size);

		return s;
	}

	private static void print2DArray(List<List<String>> x) {
		System.out.print("[");
		for (int i = 0; i < x.size(); i++) {
			if (i != 0) {
				System.out.print(" ");
			}
			System.out.print("[");
			for (String s : x.get(i)) {
				if (s.equals("")) {
					System.out.print(" , ");
				}
				else {
					System.out.print(s + ", ");
				}
			}
			System.out.print("]");
			if (i != x.size()-1) {
				System.out.println();
			}
		}
		System.out.println("]");
	}

	public static void main(String[] args) {
		PrintBinaryTree pbt = new PrintBinaryTree();

		TreeNode tn;
		List<List<String>> s;

		// test 1
		tn = new TreeNode(1, new TreeNode(2), null);
		s = pbt.printTree(tn);
		print2DArray(s);

		// test 2
		tn = new TreeNode(1);
		s = pbt.printTree(tn);
		print2DArray(s);

		// test 3
		tn = new TreeNode(1, new TreeNode(2, new TreeNode(3), null), 
			null);
		s = pbt.printTree(tn);
		print2DArray(s);

		// test 4
		tn = new TreeNode(1, new TreeNode(2, null, new TreeNode(4)), 
			new TreeNode(3));
		s = pbt.printTree(tn);
		print2DArray(s);

		// test 5
		tn = null;
		s = pbt.printTree(tn);
		print2DArray(s);

		// test 6
		tn = new TreeNode(1, new TreeNode(2, new TreeNode(3, 
			new TreeNode(4), null), null), new TreeNode(5));
		s = pbt.printTree(tn);
		print2DArray(s);

		// test 6
		tn = new TreeNode(1, new TreeNode(2, new TreeNode(3, 
			new TreeNode(4), new TreeNode(6)), new TreeNode(7)), 
			new TreeNode(5, new TreeNode(8, new TreeNode(9), 
				new TreeNode(0)), null));
		s = pbt.printTree(tn);
		print2DArray(s);
	}
}