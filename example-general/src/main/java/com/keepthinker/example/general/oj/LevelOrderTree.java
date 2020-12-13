package com.keepthinker.example.general.oj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class LevelOrderTree {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> nextQueue = new LinkedBlockingQueue<>();
        nextQueue.add(root);
        levelOrder(nextQueue, result);
        return result;

    }

    public void levelOrder(Queue<TreeNode> queue, List<List<Integer>> result) {
        if(queue.size() == 0) {
            return;
        }
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> nextQueue = new LinkedBlockingQueue<>();
        while (true) {
            TreeNode node = queue.poll();
            if (node != null) {
                list.add(node.val);
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
        levelOrder(nextQueue, result);
    }
}
