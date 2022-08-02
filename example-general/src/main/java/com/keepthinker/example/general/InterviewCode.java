package com.keepthinker.example.general;

public class InterviewCode {

    public static void main(String[] args) {
    }

    public void nextPermutation(int[] nums) {
        int leftLowerTop = nums.length - 2;
        //先找到最尾巴的顶峰A左1的位置I
        while (leftLowerTop >= 0 && nums[leftLowerTop + 1] <= nums[leftLowerTop]) {
            leftLowerTop--;
        }
        if (leftLowerTop >= 0) {
            int rightGreaterThanL = nums.length - 1;
            //从顶峰A的右下坡找到一个比位置I的值更大的一个位置
            while (rightGreaterThanL >= 0 && nums[rightGreaterThanL] <= nums[leftLowerTop]) {
                rightGreaterThanL--;
            }
            swap(nums, leftLowerTop, rightGreaterThanL);
        }
        reverse(nums, leftLowerTop + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}