package com.keepthinker.example.general.oj;

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
