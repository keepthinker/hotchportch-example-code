package com.keepthinker.example.general.oj;

public class FindKthLargest {

    public static void main(String[] args) {

        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(new Solution().findKthLargest(nums, 4));
        nums = new int[]{3,2,1,5,6,4};
        System.out.println(new Solution().findKthLargest(nums, 2));


    }


   private static class Solution {
        public int findKthLargest(int[] nums, int k) {

            return find(nums, 0, nums.length, nums.length - k);

        }

        private int find(int[] nums, int start, int end, int k) {

            int pivotIndex = partion(nums, start, end);
            if (pivotIndex == k) {
                return nums[k];
            }

            if (pivotIndex < k) {
                return find(nums, pivotIndex + 1, end, k);
            } else {
                return find(nums, start, pivotIndex, k);
            }
        }


        private int partion(int[] nums, int start, int end) {

            int pivotValue = nums[end - 1];
            int i = start - 1;
            for (int j = start; j < nums.length; j++) {
                if (nums[j] < pivotValue) {
                    swap(nums, ++i, j);
                }
            }
            swap(nums, i + 1, end - 1);
            return i + 1;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

    }
}
