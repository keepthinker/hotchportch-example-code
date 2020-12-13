package com.keepthinker.example.general.oj;

import java.util.ArrayList;
import java.util.List;

public class InorderTraversal {

    public static void main(String[] args) {

    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        doInorderTraversal(root, list);
        return list;

    }

    private  void doInorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null ){
            return;
        }
        doInorderTraversal(node.left, list);
        list.add(node.val);
        doInorderTraversal(node.right, list);

    }


}
