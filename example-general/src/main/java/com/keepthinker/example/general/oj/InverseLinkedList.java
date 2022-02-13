package com.keepthinker.example.general.oj;

public class InverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode current = head;
        ListNode next;

        do {
            next =  current.next;
            current.next = pre;
            pre = current;
            current = next;
        }  while (current != null);

        return pre;
    }


}
