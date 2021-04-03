package com.keepthinker.example.general.oj;

public class Jump {

    public static void main(String[] args) {

    }

    class Solution {
        public int jump(int[] nums) {
            int length = nums.length;
            int end = 0;
            int maxPos = 0;
            int steps = 0;
            for (int i = 0; i < length; i++) {
                maxPos = Math.max(maxPos, i + nums[i]);
                if (i == end) {
                    end = maxPos;
                    steps++;
                }
            }
            return steps;
        }
    }
}
