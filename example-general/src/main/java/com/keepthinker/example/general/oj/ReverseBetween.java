package com.keepthinker.example.general.oj;

public class ReverseBetween {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            if (head == null || head.next == null || left == right) {
                return head;
            }
            ListNode dummyHead = new ListNode(0, head);
            ListNode current = dummyHead;
            for (int i = 0; i < left - 1; i++) {
                current = current.next;
            }
            ListNode preLeftNode = current;
            ListNode leftNode = current.next;
            current = dummyHead;
            for (int i = 0; i < right; i++) {
                current = current.next;
            }
            ListNode nextRightNode = current.next;
            current = leftNode.next;
            ListNode preNode = leftNode;
            preNode.next = nextRightNode;
            do {
                ListNode nextCurrent = current.next;
                current.next = preNode;
                preNode = current;
                current = nextCurrent;
            } while (current != nextRightNode);

            preLeftNode.next = preNode;
            return dummyHead.next;
        }
    }
}
