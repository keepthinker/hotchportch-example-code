package com.keepthinker.example.general.oj;

public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if(left == null && right == null) {
            return true;
        } else if (left != null && right != null) {
            boolean isSym = left.val == right.val;
            if (!isSym) {
                return false;
            }
        } else {
            return false;
        }
        boolean l = isSymmetric(left.left, right.right);
        boolean r = isSymmetric(left.right, right.left);
        return l && r;
    }
}
