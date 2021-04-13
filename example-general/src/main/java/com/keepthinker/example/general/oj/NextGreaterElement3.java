package com.keepthinker.example.general.oj;

import java.beans.BeanInfo;
import java.util.Collections;

public class NextGreaterElement3 {

    public static void main(String[] args) {
//        System.out.println(new Solution().nextGreaterElement(1));
//        System.out.println(new Solution().nextGreaterElement(1234689321));
        System.out.println(new Solution().nextGreaterElement(2147483476));
    }
    private static class Solution {

        public int nextGreaterElement(int n) {
            char[] nums = String.valueOf(n).toCharArray();
            int i = nums.length - 1;
            while (i > 0 && nums[i - 1] >= nums[i]) {
                i--;
            }
            int left = i - 1;
            if (left < 0) {
                return -1;
            }

            while (i < nums.length - 1) {
                if (nums[left] < nums[i] && nums[left] >= nums[i + 1]) {
                    break;
                }
                i++;
            }
            int right = i;
            swap(nums, left, right);
            reverse(nums, left + 1, nums.length - 1);
            long result = Long.parseLong(String.valueOf(nums));
            if (result <= Integer.MAX_VALUE) {
                return (int)result;
            } else {
                return -1;
            }
        }

        private void swap(char[] arr, int i, int j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }


        private void reverse(char[] arr, int start, int end) {
            while (start < end) {
                swap(arr, start++, end--);
            }
        }
    }
}
