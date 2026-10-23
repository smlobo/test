
public class BinaryTreeLCA {
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

    TreeNode LCA;

    public BinaryTreeLCA() {}

    TreeNode dfs2Targets(TreeNode t, int p, int q) {
        // base case - lca already set
        if (this.LCA != null) {
            return null;
        }

        // base case - leaf
        if (t == null) {
            return null;
        }

        TreeNode center = null;
        if (t.val == p || t.val == q) {
            center = new TreeNode(t.val);
        }

        // search left tree
        TreeNode left = dfs2Targets(t.left, p, q);

        // found in left & myself
        if (left != null && center != null) {
            this.LCA = new TreeNode(t.val);
            return null;
        }

        // search right tree
        TreeNode right = dfs2Targets(t.right, p, q);

        // found in right & myself
        if (right != null && center != null) {
            this.LCA = new TreeNode(t.val);
            return null;
        }

        // found in left and right - i am LCA
        if (left != null && right != null) {
            this.LCA = new TreeNode(t.val);
            return null;            
        }

        // only left found return it
        if (left != null) {
            return left;
        }
        // only right found return it
        if (right != null) {
            return right;
        }

        // either found in myself or null
        return center;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // initial call - reset LCA
        this.LCA = null;

        TreeNode x = dfs2Targets(root, p.val, q.val);

        return this.LCA;
    }

    public static void main(String[] args) {
        BinaryTreeLCA s = new BinaryTreeLCA();

        TreeNode t = new TreeNode(3, 
            new TreeNode(5, 
                new TreeNode(6), 
                new TreeNode(2, 
                    new TreeNode(7),
                    new TreeNode(4))),
            new TreeNode(1, 
                new TreeNode(0),
                new TreeNode(8)));
        TreeNode a = s.lowestCommonAncestor(t, new TreeNode(6), new TreeNode(4));
        System.out.println("a = " + a.val);

        a = s.lowestCommonAncestor(t, new TreeNode(6), new TreeNode(8));
        System.out.println("a = " + a.val);

        a = s.lowestCommonAncestor(t, new TreeNode(4), new TreeNode(5));
        System.out.println("a = " + a.val);

        a = s.lowestCommonAncestor(t, new TreeNode(8), new TreeNode(0));
        System.out.println("a = " + a.val);
    }
}