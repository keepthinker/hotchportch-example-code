package com.keepthinker.example.general;

import java.util.Scanner;

/**
 * Created by keepthinker on 2017/8/1.
 */
public class Solution {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
//        System.out.println(new Solution().isMatch("abbbbc", "a*b*."));
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode secondNode = head;
        ListNode firstNode = head.next;
        for(int i = 0; i < n; i++){
            firstNode = firstNode.next;
        }

        if(firstNode.next == null){
            ListNode resultHead = head.next;
            head.next = null;
            return resultHead;
        }
        while(firstNode != null){
            firstNode = firstNode.next;
            secondNode = secondNode.next;
        }

        ListNode removedNode = secondNode.next;
        secondNode.next = secondNode.next.next;
        removedNode.next = null;
        return head;
    }

    /**
     * Definition for singly-linked list.
     */
     public class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }


}
