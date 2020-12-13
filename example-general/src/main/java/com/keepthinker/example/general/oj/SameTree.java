package com.keepthinker.example.general.oj;

public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        return preorder(p, q);
    }

    private boolean preorder(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null) {
            boolean isSame = p.val == q.val;
            if (!isSame) {
                return false;
            }
            boolean l = preorder(p.left, q.left);
            boolean r = preorder(p.right, q.right);
            return l && r;
        } else {
            return false;
        }

    }
}
