package com.keepthinker.example.general.oj;

public class RotateRight {

    class Solution {
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || head.next == null || k == 0) {
                return head;
            }
            int n = 0;
            ListNode tempHead = head;
            do {
                n++;
            } while((tempHead = tempHead.next) != null);
            k = k % n;
            if (k == 0) {
                return head;
            }
            ListNode dumpHead = new ListNode(0, head);
            ListNode node1 = dumpHead;
            ListNode node2 = dumpHead;
            for (int i = 0; i < k; i++) {
                node2 = node2.next;
            }
            while (node2.next != null) {
                node1 = node1.next;
                node2 = node2.next;
            }

            ListNode insertNext = dumpHead.next;
            dumpHead.next = node1.next;
            node1.next = null;
            node2.next = insertNext;
            return dumpHead.next;

        }
    }
}
