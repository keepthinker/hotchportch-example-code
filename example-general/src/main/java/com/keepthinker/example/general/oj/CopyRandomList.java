package com.keepthinker.example.general.oj;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomList {

    class Solution {

        private Map<Node, Node> map = new HashMap<>();

        public Node copyRandomList(Node head) {

            if (head == null) {
                return head;
            }

            Node cacheNode = map.get(head);
            if (cacheNode != null) {
                return cacheNode;
            }

            Node newNode = new Node(head.val);
            map.put(head, newNode);

            newNode.next = copyRandomList(head.next);
            newNode.random = copyRandomList(head.random);

            return newNode;

        }
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
