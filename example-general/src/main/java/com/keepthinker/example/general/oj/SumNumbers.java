package com.keepthinker.example.general.oj;

import java.util.concurrent.atomic.AtomicInteger;

public class SumNumbers {

    class Solution {
        public int sumNumbers(TreeNode root) {
            AtomicInteger counter =new AtomicInteger();
            dfs(root, 0, counter);
            return counter.get();
        }

        private void dfs(TreeNode node, int sum, AtomicInteger counter) {
            sum = sum * 10 + node.val;
            if (node.left == null && node.right == null) {
                counter.addAndGet(sum);
            }
            if (node.left != null) {
                dfs(node.left, sum, counter);
            }
            if (node.right != null) {
                dfs(node.right, sum, counter);
            }
        }
    }
}
