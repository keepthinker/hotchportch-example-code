package com.keepthinker.example.general.oj;

/**
 * 最大子序和
 * 给定一个整数数组 nums ， 找到一个具有最大和的连续子数组（子数组最少包含一个元素） ，
 * 返回其最大和。
 * 示例 1：
 * 输入： nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出： 6
 * 解释： 连续子数组 [4,-1,2,1] 的和最大， 为 6 。
 * 示例 2：
 * 输入： nums = [1]
 * 输出： 1
 * 示例 3：
 * 输入： nums = [0]
 * 输出： 0
 * 示例 4：
 * 输入： nums = [-1]
 * 输出： -1
 * 示例 5：
 * 输入： nums = [-100000]
 * 输出： -100000
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * -105 <= nums[i] <= 105
 * 进阶： 如果你已经实现复杂度为 O(n) 的解法， 尝试使用更为精妙的 分治法 求解。
 */
public class MaxConsistentArray {

    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new MaxConsistentArray.Solution().maxSubArray(nums));
    }


    private static class Solution {
        public int maxSubArray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
           int[] dp = new int[nums.length];
           dp[0] = nums[0];
           int max = dp[0];
           for (int i = 1; i < nums.length; i++) {
               dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
               if (dp[i] > max) {
                   max = dp[i];
               }
           }
           return max;
        }
    }
}
