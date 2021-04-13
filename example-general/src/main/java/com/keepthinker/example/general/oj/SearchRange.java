package com.keepthinker.example.general.oj;

public class SearchRange {

    public int[] searchRange(int[] nums, int target) {
        int left = binarySearch(nums, target, true);
        int right = binarySearch(nums, target, false);
        if (left <= right && left < nums.length && right < nums.length && nums[left] == nums[right] && nums[left] == target) {
            return new int[]{left, right};
        } else {
            return new int[]{-1, -1};
        }


    }

    private int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length; // ans要么等于target，要么离target最近的值，如果lower等于true，难么就是离target最近，且>=target的下标
        while (left <= right) {
            int mid = (right + left) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }


}
