
public class BinaryTreeKth {
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

    int count;
    int k;

    public BinaryTreeKth() {}

    public int kthSmallestDFS(TreeNode root) {
        // base
        if (root == null) {
            return -1;
        }

        // in order traversal
        int leftAnswer = kthSmallestDFS(root.left);
        if (leftAnswer >= 0) {
            return leftAnswer;
        }
        if (k == count) {
            return root.val;
        }
        count++;
        return kthSmallestDFS(root.right);
    }

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        this.count = 1;
        return kthSmallestDFS(root);
    }

    public static void main(String[] args) {
        BinaryTreeKth s = new BinaryTreeKth();

        TreeNode t = new TreeNode(5,
            new TreeNode(3, 
                new TreeNode(2, 
                    new TreeNode(1),
                    null), 
                new TreeNode(4)),
            new TreeNode(6));
        System.out.println("a(3) = " + s.kthSmallest(t, 3));
        System.out.println("a(6) = " + s.kthSmallest(t, 6));
        System.out.println("a(1) = " + s.kthSmallest(t, 1));

    }
}