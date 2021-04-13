package com.keepthinker.example.general.oj;

import java.util.HashMap;
import java.util.Map;

public class BuildTreeByInorderAndPreorder {


    private static class Solution {
        private Map<Integer, Integer> map = new HashMap<>();
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        }

        private TreeNode buildTree(int[] preorder, int[] inorder, int preLeft, int preRight, int inleft, int inRight) {
            if (preLeft > preRight) {
                return null;
            }

            TreeNode head = new TreeNode(preorder[preLeft]);
            int headInorderIndex = map.get(preorder[preLeft]);
            int leftSize = headInorderIndex - inleft;

            head.left = buildTree(preorder, inorder, preLeft + 1, preLeft + leftSize, inleft, headInorderIndex - 1);
            head.right = buildTree(preorder, inorder, preLeft + leftSize + 1, preRight, headInorderIndex + 1, inRight);
            return head;
        }
    }
}
