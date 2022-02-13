package com.keepthinker.example.general.oj;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个 没有重复 数字的序列， 返回其所有可能的全排列。
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class AllPermutation {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        new Solution().permute(nums);
    }

    private static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result =  new ArrayList<>();
            List<Integer> output= new ArrayList<Integer>();
            permute(nums,result, output);
            return result;
        }
        public void permute(int[] nums, List<List<Integer>> result, List<Integer> output) {
            if (output.size() == nums.length) {
                result.add(new ArrayList<Integer>( output));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (output.contains(nums[i])) {
                    continue;
                }
                output.add((Integer)nums[i]);
                permute(nums, result, output);
                output.remove((Integer)nums[i]);
            }
        }

    }
}
