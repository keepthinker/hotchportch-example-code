package com.keepthinker.example.general.oj;

public class InvertTree {


    public static void main(String[] args) {

    }

    public TreeNode invertTree(TreeNode root) {

        TreeNode target = new TreeNode(root.val);
        doInvertTree(root, target);
        return  target;

    }

    public void doInvertTree(TreeNode node, TreeNode target) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            TreeNode tempLNode = new TreeNode(node.left.val);
            target.right = tempLNode;
        }
        if(node.right != null) {
            TreeNode tempRNode = new TreeNode(node.right.val);
            target.left = tempRNode;
        }
        doInvertTree(node.left, target.right);
        doInvertTree(node.right, target.left);

    }


}
