package com.keepthinker.example.general.oj;

import java.util.ArrayList;
import java.util.List;

public class AllPermutation {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        new Solution().permute(nums);
    }

    static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result =  new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(nums[i]);
                permute(nums, i, result, temp);

            }
            return result;
        }

        public void permute(int[] nums, int chooseIndex, List<List<Integer>> result, List<Integer> temp) {
            if (temp.size() == nums.length) {
                result.add(new ArrayList<Integer>(temp));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (i == chooseIndex || temp.contains(nums[i])) {
                    continue;
                }
                temp.add((Integer)nums[i]);
                permute(nums, i, result, temp);
                temp.remove((Integer)nums[i]);
            }
        }
    }
}
