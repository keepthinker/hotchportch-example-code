package com.keepthinker.example.general.oj;

import java.util.Stack;

public class AddTwoNumbers {

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            Stack<ListNode> stack1 = new Stack<>();
            Stack<ListNode> stack2 = new Stack<>();
            while (l1 != null) {
                stack1.add(l1);
                l1 = l1.next;
            }
            while (l2 != null) {
                stack2.add(l2);
                l2 = l2.next;
            }
            ListNode dumpHead = new ListNode(0);
            ListNode preNode = dumpHead;
            int carry = 0;
            while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
                int num1 = stack1.isEmpty() ? 0 : stack1.pop().val;
                int num2 = stack2.isEmpty() ? 0 : stack2.pop().val;
                int sum = num1 + num2 + carry;
                carry = sum / 10;
                ListNode tempNode = dumpHead.next;
                dumpHead.next = new ListNode(sum % 10);
                dumpHead.next.next = tempNode;
            }
            return dumpHead.next;
        }
    }

}
