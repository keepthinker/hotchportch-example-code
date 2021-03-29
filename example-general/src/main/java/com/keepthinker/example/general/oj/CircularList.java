package com.keepthinker.example.general.oj;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CircularList {
    public static void main(String[] args) {

    }


    public class Solution {
        public boolean hasCycle(ListNode head) {
            if(head == null || head.next == null) {
                return false;
            }
            Set<ListNode> nodeSet= new HashSet<>();
            nodeSet.add(head);

            ListNode current = head;
            while ((current = current.next) != null) {
                if (!nodeSet.add(current)) {
                    return true;
                }
            }
            return false;
        }
    }

    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x, int id) {
            this.val = x;
            this.next = null;
        }
    }
}
