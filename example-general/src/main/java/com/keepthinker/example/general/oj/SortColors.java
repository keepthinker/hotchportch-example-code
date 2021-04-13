package com.keepthinker.example.general.oj;

public class SortColors {

    class Solution {
        public void sortColors(int[] nums) {
            int[] counter = new int[3];
            for (int i = 0; i < nums.length; i++) {
                counter[nums[i]] ++;
            }
            int i = 0;
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < counter[k]; j++) {
                    nums[i++] = k;
                }
            }

        }
    }
}
