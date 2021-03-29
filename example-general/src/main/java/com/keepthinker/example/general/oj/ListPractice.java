package com.keepthinker.example.general.oj;

public class ListPractice {


    public static void main(String[] args) {
        new ListPractice().run();

    }
    private void run() {
        Node head = generateNodeList(10);
        printList(head);

        Node newHead = reverseNode(head);
        printList(newHead);
        printList(createReverseNode(newHead));
    }

    /** 创建一个全新数据的反转链表，和原链表没关系*/
    private Node createReverseNode(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new Node(head.val);
        }

        Node pre = null;
        Node current = head;

        Node newPre = new Node(head.val);
        Node newHead;

        do {
            Node next = current.next;
            current.next = pre;
            pre = current;
            current = next;

            newHead = new Node(current.val);
            newHead.next = newPre;
            newPre = newHead;

        } while (current.next != null);

        current.next = pre;

        return newHead;
    }

    /** 反转链表*/
    private Node reverseNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 初始值
        Node pre = null;
        Node current = head;

        do { //循环
            Node next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        } while (current.next != null);
        current.next = pre;

        return current;

    }

    private Node generateNodeList(int size) {

        Node head = new Node(0);
        Node current = head;
        for (int i = 1; i < size; i++) {
            Node newNode = new Node(i);
            current.next = newNode;
            current = newNode;
        }
        return head;
    }


    public void printList(Node head) {
        Node current = head;
        do {
            System.out.print(current.val + " ");
        } while ((current = current.next) != null);
        System.out.println();
    }

    private class Node {
        private int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}
