package com.keepthinker.example.general.oj;

import java.util.concurrent.ThreadLocalRandom;

public class LinkedListSort {

    public static void main(String[] args) {
        ListNode list =  generateRandomNodeList(5);
        printList(list);
        ListNode newList = new Solution().insertionSortList(list);
        printList(newList);
    }

    static class Solution {

        public ListNode  insertionSortList(ListNode head) {
            ListNode dummyHead = new ListNode(0, head);

            ListNode lastSorted = head;
            ListNode cur = head.next;

            while (cur != null) {
                if (lastSorted.val < cur.val) {
                    lastSorted = lastSorted.next;
                } else {
                    ListNode tempPreHead = dummyHead;
                    while (tempPreHead.next.val < cur.val) {
                        tempPreHead = tempPreHead.next;
                    }
                    lastSorted.next = lastSorted.next.next;
                    ListNode tempNext = tempPreHead.next;
                    tempPreHead.next = cur;
                    cur.next = tempNext;
                }
                cur = lastSorted.next;
            }
            return dummyHead.next;
        }


        public ListNode selectSortList(ListNode head) {

            if (head.next == null) {
                return head;
            }

            ListNode dummyHead = new ListNode(0, head);

            ListNode preHead = dummyHead;
            ListNode preNode = dummyHead;
            int tempMin = preHead.next.val;
            while (preHead.next != null) {
                ListNode chosenPre = null;
                while (preNode.next != null) {
                    if (preNode.next.val < tempMin) {
                        tempMin = preNode.next.val;
                        chosenPre = preNode;
                    }
                    preNode = preNode.next;
                }
                if (chosenPre != null) {
                    ListNode chosenNode = chosenPre.next;
                    chosenPre.next = chosenPre.next.next;

                    ListNode tempNext = preHead.next;
                    preHead.next = chosenNode;
                    chosenNode.next = tempNext;
                }

                preHead = preHead.next;
                preNode = preHead;
                if (preHead.next == null) {
                    break;
                }
                tempMin = preHead.next.val;

//                printList(dummyHead.next);
            }
            return dummyHead.next;

        }
    }

    public static void printList(ListNode head) {
        ListNode current = head;
        do {
            System.out.print(current.val + " ");
        } while ((current = current.next) != null);
        System.out.println();
    }


    public static ListNode generateRandomNodeList(int size) {

        ListNode head = new ListNode(5);
        ListNode current = head;
        for (int i = 1; i < size; i++) {
            ThreadLocalRandom.current().nextInt();
            ListNode newNode = new ListNode(ThreadLocalRandom.current().nextInt(size * 2));
            current.next = newNode;
            current = newNode;
        }
        return head;
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
