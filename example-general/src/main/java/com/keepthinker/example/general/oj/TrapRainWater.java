package com.keepthinker.example.general.oj;

public class TrapRainWater {


    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(new TrapRainWater.Solution().trap(nums));
    }


    private static class Solution {
        public int trap(int[] height) {
            if (height.length < 2) {
                return 0;
            }
            int volume = 0;
            for (int i = 1; i < height.length - 1; i++) {
                int maxLeft = 0;
                for (int j = 0; j <= i; j++) {
                    maxLeft = Math.max(height[j], maxLeft);
                }
                int maxRight = 0;
                for (int k = i; k < height.length; k++) {
                    maxRight = Math.max(height[k], maxRight);
                }
                volume += Math.min(maxLeft, maxRight) - height[i];
            }
            return volume;

        }
    }
}
