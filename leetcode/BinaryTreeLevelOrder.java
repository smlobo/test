import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;

public class BinaryTreeLevelOrder {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class NodeLevel {
        TreeNode node;
        int level;
        NodeLevel(TreeNode n, int l) {
            this.node = n;
            this.level = l;
        }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelList = new ArrayList<>();

        // corner case - empty tree
        if (root == null) {
            return levelList;
        }

        // BFS Q
        ArrayDeque<NodeLevel> bfsQ = new ArrayDeque<>();
        bfsQ.addLast(new NodeLevel(root, 0));
        int prevLevel = 0;
        List<Integer> currLevelList = new ArrayList<>();
        levelList.add(currLevelList);
        while (!bfsQ.isEmpty()) {
            NodeLevel nl = bfsQ.removeFirst();

            // visit node
            if (nl.node.left != null) {
                bfsQ.addLast(new NodeLevel(nl.node.left, nl.level + 1));
            }
            if (nl.node.right != null) {
                bfsQ.addLast(new NodeLevel(nl.node.right, nl.level + 1));
            }

            // create level list if new            
            if (prevLevel != nl.level) {
                currLevelList = new ArrayList<>();
                levelList.add(currLevelList);
                prevLevel = nl.level;
            }
            // add value to current level list
            currLevelList.add(nl.node.val);
        }

        return levelList;
    }

    public static void main(String[] args) {
        TreeNode r = new TreeNode(3, new TreeNode(9), 
            new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        List<List<Integer>> l = levelOrder(r);
        System.out.print("[");
        for (List<Integer> s : l) {
            System.out.print(s + ", ");
        }
        System.out.println("]");

        r = new TreeNode(1);
        l = levelOrder(r);
        System.out.print("[");
        for (List<Integer> s : l) {
            System.out.print(s + ", ");
        }
        System.out.println("]");

        r = null;
        l = levelOrder(r);
        System.out.print("[");
        for (List<Integer> s : l) {
            System.out.print(s + ", ");
        }
        System.out.println("]");
    }
}
