package com.keepthinker.example.general.oj;

import java.util.ArrayList;
import java.util.List;

public class DifferentBinarySearchTree {
    public static void main(String[] args) {

    }


    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return null;
        }
        return generateTrees(1, n);

    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if(start > end) {
            list.add(null);
            return list;
        }

        for(int i =  start; i <= end; i++) {

            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            for(TreeNode leftNode : leftTrees) {
                for(TreeNode rightNode : rightTrees) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = leftNode;
                    treeNode.right = rightNode;
                    list.add(treeNode);
                }
            }

        }

        return list;
    }

}
