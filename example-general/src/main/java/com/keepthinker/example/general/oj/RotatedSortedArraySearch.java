package com.keepthinker.example.general.oj;

public class RotatedSortedArraySearch {
    public static void main(String[] args) {
        int[] arr = new int[] {4,5,6,7,0,1,2};
        int target = 0;
        System.out.println("1： " + new RotatedSortedArraySearch().search(arr, target));

        arr = new int[] {4,5,6,7,0,1,2};
        target = 4;
        System.out.println("2： " + new RotatedSortedArraySearch().search(arr, target));

        arr = new int[] {4,5,6,7,0,1,2};
        target = 2;
        System.out.println("3： " + new RotatedSortedArraySearch().search(arr, target));

        arr = new int[] {4,5};
        target = 2;
        System.out.println("4： " + new RotatedSortedArraySearch().search(arr, target));
    }

    public int search(int[] nums, int target) {


        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0;
        int r = nums.length - 1;
        while (l <= r ) {
            int mid = (r + l) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[nums.length - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;


    }

}
