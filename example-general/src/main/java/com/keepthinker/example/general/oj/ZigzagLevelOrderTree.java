package com.keepthinker.example.general.oj;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class ZigzagLevelOrderTree {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        if(root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> nextQueue = new LinkedBlockingQueue<>();
        nextQueue.add(root);
        levelOrder(nextQueue, result, true);
        return result;

    }

    public void levelOrder(Queue<TreeNode> queue, List<List<Integer>> result, boolean goRight) {
        if(queue.size() == 0) {
            return;
        }
        List<Integer> list = new LinkedList<>();
        Deque<TreeNode> nextQueue = new LinkedBlockingDeque<>();
        while (true) {
            TreeNode node;
            node = queue.poll();
            if (node != null) {
                if(goRight) {
                    list.add(node.val);
                } else {
                    list.add(0, node.val);
                }
                if (node.left != null) {
                    nextQueue.add(node.left);
                }
                if (node.right != null) {
                    nextQueue.add(node.right);
                }

            } else {
                break;
            }
        }
        result.add(list);
        levelOrder(nextQueue, result, !goRight);
    }
}
