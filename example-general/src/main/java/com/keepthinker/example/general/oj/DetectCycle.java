package com.keepthinker.example.general.oj;

import java.util.HashSet;
import java.util.Set;

public class DetectCycle {
    public class Solution {

        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next == head) {
                return head;
            }
            if (head.next == null) {
                return null;
            }

            ListNode slowNode = head;
            ListNode fastNode = head.next;
            while (fastNode != null && fastNode.next != null) {
                if (slowNode == fastNode.next) {
                    return slowNode;
                }
                slowNode = slowNode.next;
                fastNode = fastNode.next.next;
            }
            return null;

        }


        public ListNode detectCycle1(ListNode head) {
            if (head == null || head.next == head) {
                return head;
            }
            Set<ListNode> set = new HashSet<>();
            do {
                if (set.contains(head)) {
                    return head;
                } else {
                    set.add(head);
                }
                head = head.next;
            } while (head != null);
            return null;

        }
    }
}
