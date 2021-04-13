package com.keepthinker.example.general.oj;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequent {

    public static void main(String[] args) {
        int[] arr = {1,1,1,2,2,3};
        new Solution().topKFrequent(arr, 2);

    }
    private static class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> counterMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                counterMap.put(nums[i], counterMap.getOrDefault(counterMap.get(nums[i]) + 1, 1));
            }

            PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<Map.Entry<Integer, Integer>>(
                    new Comparator<Map.Entry<Integer, Integer>>() {
                        @Override
                        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                            return o2.getValue() - o1.getValue();
                        }
                    }
            );
            priorityQueue.addAll(counterMap.entrySet());

            int[] results = new int[k];
            for (int i = 0; i < k; i++) {
                results[i] = priorityQueue.poll().getKey();
            }
            return results;

        }
    }
}
