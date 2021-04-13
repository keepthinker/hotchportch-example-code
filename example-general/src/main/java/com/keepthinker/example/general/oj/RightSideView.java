package com.keepthinker.example.general.oj;

import java.util.ArrayList;
import java.util.List;

public class RightSideView {
    class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            inorder(root.right, list, 1);
            return list;

        }

        private void inorder(TreeNode node, List<Integer> list, int level) {
            if (node == null) {
                return;
            }
            inorder(node.left, list, level + 1);
            if (list.size() >= level) {
                list.add(node.val);
            } else {
                list.set(level, node.val);
            }
            inorder(node.right, list, level + 1);
        }
    }
}
