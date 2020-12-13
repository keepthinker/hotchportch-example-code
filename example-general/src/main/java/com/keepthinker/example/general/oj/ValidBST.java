package com.keepthinker.example.general.oj;

public class ValidBST {
    private long preValue = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        return inorder(root);
    }

    public boolean inorder(TreeNode node){
        if (node == null) {
            return true;
        }
        boolean left = inorder(node.left);
        if (preValue < node.val) {
            preValue = node.val;
        } else {
            return false;
        }
        boolean right = inorder(node.right);
        return left && right;

    }
}
