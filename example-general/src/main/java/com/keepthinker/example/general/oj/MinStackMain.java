package com.keepthinker.example.general.oj;

import java.util.LinkedList;
import java.util.List;

public class MinStackMain {

    class MinStack {
        private List<Integer> stack;
        private List<Integer> minStack;
        /** initialize your data structure here. */
        public MinStack() {
            stack = new LinkedList<>();
            minStack = new LinkedList<>();
            minStack.add(Integer.MAX_VALUE);
        }

        public void push(int val) {
            stack.add(0, val);
            minStack.add(0, Math.min(minStack.get(0), val));
        }

        public void pop() {
            stack.remove(0);
            minStack.remove(0);
        }

        public int top() {
            return stack.get(0);
        }

        public int getMin() {
            return minStack.get(0);
        }
    }

}
