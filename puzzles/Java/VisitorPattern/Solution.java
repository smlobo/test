/*

An important concept in Object-Oriented Programming is the open/closed 
principle, which means writing code that is open to extension but closed to 
modification. In other words, new functionality should be added by writing an 
extension for the existing code rather than modifying it and potentially 
breaking other code that uses it. This challenge simulates a real-life problem 
where the open/closed principle can and should be applied.

A Tree class implementing a rooted tree is provided in the editor. It has the following publicly available methods:

getValue(): Returns the value stored in the node.
getColor(): Returns the color of the node.
getDepth(): Returns the depth of the node. Recall that the depth of a node is the number of edges between the node and the tree's root, so the tree's root has depth  and each descendant node's depth is equal to the depth of its parent node .
In this challenge, we treat the internal implementation of the tree as being closed to modification, so we cannot directly modify it; however, as with real-world situations, the implementation is written in such a way that it allows external classes to extend and build upon its functionality. More specifically, it allows objects of the TreeVis class (a Visitor Design Pattern) to visit the tree and traverse the tree structure via the accept method.

There are two parts to this challenge.

Part I: Implement Three Different Visitors
Each class has three methods you must write implementations for:

getResult(): Return an integer denoting the , which is different for each class:

The SumInLeavesVisitor implementation must return the sum of the values in the tree's leaves only.
The ProductRedNodesVisitor implementation must return the product of values stored in all red nodes, including leaves, computed modulo . Note that the product of zero values is equal to .
The FancyVisitor implementation must return the absolute difference between the sum of values stored in the tree's non-leaf nodes at even depth and the sum of values stored in the tree's green leaf nodes. Recall that zero is an even number.
visitNode(TreeNode node): Implement the logic responsible for visiting the tree's non-leaf nodes such that the getResult method returns the correct  for the implementing class' visitor.

visitLeaf(TreeLeaf leaf): Implement the logic responsible for visiting the tree's leaf nodes such that the getResult method returns the correct  for the implementing class' visitor.
Part II: Read and Build the Tree
Read the -node tree, where each node is numbered from  to . The tree is given as a list of node values (), a list of node colors (), and a list of edges. Construct this tree as an instance of the Tree class. The tree is always rooted at node number .

Your implementations of the three visitor classes will be tested on the tree you built from the given input.

Input Format

The first line contains a single integer, , denoting the number of nodes in the tree. The second line contains space-separated integers describing the respective values of . 
The third line contains  space-separated binary integers describing the respective values of . Each  denotes the color of the  node, where  denotes red and  denotes green. 
Each of the  subsequent lines contains two space-separated integers,  and , describing an edge between nodes  and .

Constraints

It is guaranteed that the tree is rooted at node .
Output Format

Do not print anything to stdout, as this is handled by locked stub code in the editor. The three getResult() methods provided for you must return an integer denoting the  for that class' visitor (defined above). Note that the value returned by ProductRedNodesVisitor's getResult method must be computed modulo .

Sample Input

5
4 7 2 5 12
0 1 0 0 1
1 2
1 3
3 4
3 5
Sample Output

24
40
15
Explanation

image

Locked stub code in the editor tests your three class implementations as follows:

Creates a SumInLeavesVisitor object whose getResult method returns the sum of the leaves in the tree, which is . The locked stub code prints the returned value on a new line.
Creates a ProductOfRedNodesVisitor object whose getResult method returns the product of the red nodes, which is . The locked stub code prints the returned value on a new line.
Creates a FancyVisitor object whose getResult method returns the absolute difference between the sum of the values of non-leaf nodes at even depth and the sum of the values of green leaf nodes, which is . The locked stub code prints the returned value on a new line.*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ListIterator;

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis
{
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    private int result = 0;

    public int getResult() {
      	//implement this
        return result;
    }

    public void visitNode(TreeNode node) {
      	//implement this
    }

    public void visitLeaf(TreeLeaf leaf) {
      	//implement this
        result += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private long result = 1;
    private final int M = 1000000007;

    public int getResult() {
      	//implement this
        return (int) result;
    }

    private void computeProduct(int x) {
        result = (result*x) % M;
    }

    public void visitNode(TreeNode node) {
      	//implement this
        if (node.getColor() == Color.RED)
            computeProduct(node.getValue());
    }

    public void visitLeaf(TreeLeaf leaf) {
      	//implement this
        if (leaf.getColor() == Color.RED)
            computeProduct(leaf.getValue());
    }
}

class FancyVisitor extends TreeVis {
    private int result;

    public int getResult() {
      	//implement this
        return Math.abs(result);
    }

    public void visitNode(TreeNode node) {
    	//implement this
        if (node.getDepth() % 2 == 0)
            result += node.getValue();
    }

    public void visitLeaf(TreeLeaf leaf) {
    	//implement this
        if (leaf.getColor() == Color.GREEN)
            result -= leaf.getValue();
    }
}

public class Solution {

    public static int nChild = 0;

    public static Tree solve() {
        // read the tree from STDIN and return its root as a return value of 
        // this function
        Scanner s = new Scanner(System.in);

        // # of nodes
        int n = s.nextInt();
        //System.out.println("n = " + n);

        // node values
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = s.nextInt();
            //System.out.println(i + " : " + values[i]);
        }

        // node colors
        Color[] colors = new Color[n];
        for (int i = 0; i < n; i++) {
            colors[i] = s.nextInt() == 0 ? Color.RED : Color.GREEN;
            //System.out.println(i + " : " + colors[i]);
        }

        // node connections
        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] connections = new LinkedList[n];
        for (int i = 0; i < n - 1; i++) {
            int u = s.nextInt() - 1;
            int v = s.nextInt() - 1;

            if (connections[u] == null)
                connections[u] = new LinkedList<Integer>();
            connections[u].add(v);

            if (connections[v] == null)
                connections[v] = new LinkedList<Integer>();
            connections[v].add(u);

            //System.out.println("[" + i + "] " + u + " -> " + v);
        }

        // root node
        Tree root = makeTree(0, 0, connections, values, colors);
        //System.out.println("# children: " + nChild);

        return root;
    }

    private static Tree makeTree(int index, int depth, 
        LinkedList<Integer>[] connections, int[] values, Color[] colors) {

        // TreeLeaf
        if (index != 0 && connections[index].size() == 1) {
            //System.out.println("leaf: " + index);
            return new TreeLeaf(values[index], colors[index], depth);
        }

        // TreeNode
        //System.out.println("node: " + index);
        TreeNode node = new TreeNode(values[index], colors[index], depth);

        // Mark as done
        values[index] = 0;

        // Add in children
        ListIterator<Integer> it = connections[index].listIterator();
        while (it.hasNext()) {
            int edgeTo = it.next();

            // Parent - skip
            if (values[edgeTo] == 0)
                continue;

            node.addChild(makeTree(edgeTo, depth+1, connections, values, 
                colors));
            nChild++;
        }

        return node;
    }

    public static void main(String[] args) {
        Tree root = solve();
		SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
    	ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
    	FancyVisitor vis3 = new FancyVisitor();

     	root.accept(vis1);
     	root.accept(vis2);
     	root.accept(vis3);

    	int res1 = vis1.getResult();
     	int res2 = vis2.getResult();
     	int res3 = vis3.getResult();

     	System.out.println(res1);
     	System.out.println(res2);
    	System.out.println(res3);
    }
}