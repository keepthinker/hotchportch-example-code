package com.keepthinker.example.general.oj;

/**
 * 寻找旋转排序数组中的最小值
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。 例如， 数组 [0,1,2,4,5,6,7] 可
 * 能变为 [4,5,6,7,0,1,2] 。
 * 请找出其中最小的元素。
 * 示例 1：
 * 输入： nums = [3,4,5,1,2]
 * 输出： 1
 * 示例 2：
 * 输入： nums = [4,5,6,7,0,1,2]
 * 输出： 0
 */
public class FindRotatedOrderedList {

    public static void main(String[] args) {
        int[] nums = {5,1,2,3,4};
        System.out.println(new Solution().findMin(nums));
    }

    private static class Solution {
        public int findMin(int[] nums) {

            if (nums.length == 1) {
                return nums[0];
            }

            if (nums.length == 2) {
                return Math.min(nums[0], nums[1]);
            }
            int left = 0;
            int right = nums.length - 1;

            if (nums[right] > nums[0]) {
                return nums[0];
            }

            while (right >= left) {
                int mid = (right - left) / 2 + left;

                if (nums[mid] > nums[mid + 1]) {
                    return nums[mid + 1];
                }
                if (nums[mid - 1] > nums[mid]) {
                    return nums[mid];
                }

                if (nums[mid] > nums[0]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;

        }
    }
}
