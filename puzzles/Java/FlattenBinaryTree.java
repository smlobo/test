/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void flatten(TreeNode root) {
        // Empty node
        if (root == null)
            return;
        
        // Left is empty
        if (root.left == null) {
            flatten(root.right);
            return;
        }
        
        // Cache the right tree
        TreeNode rcache = root.right;

        // Set left to right
        root.right = root.left;
        root.left = null;
        
        // Recurse thru left tree
        flatten(root.right);
        
        // Set the last node to the right node
        TreeNode rbottom = root.right;
        while (rbottom.right != null)
            rbottom = rbottom.right;
        rbottom.right = rcache;
        
        // Recurse thru right tree
        flatten(rcache);
    }
}
