package com.keepthinker.example.general.oj;

/**
 * 数组中的第 K 个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。 请注意， 你需要找的是数组排序后的第 k 个最
 * 大的元素， 而不是第 k 个不同的元素。
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * 你可以假设 k 总是有效的， 且 1 ≤ k ≤ 数组的长度。
 */
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
