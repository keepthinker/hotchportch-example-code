package com.keepthinker.example.general.oj;

public class MirrorTree {


    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;

        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }

}
