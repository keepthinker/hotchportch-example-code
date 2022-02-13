package com.keepthinker.example.general.oj;

/**
 * 最小路径和
 */
public class MinPathSum {

    public static void main(String[] args) {

        int[][] grid = {{1,3,1},{1,5,1},{4,2,1},{2,2,1}};
        System.out.println(new Solution().minPathSum(grid));
    }

    private static class Solution {
        public int minPathSum(int[][] grid) {
            int[][] dp = new int[grid.length][];
            for (int i = 0; i < grid.length; i++) {
                dp[i] = new int[grid[0].length];
            }

            dp[0][0] = grid[0][0];
            for (int i = 1; i < grid.length; i++) {
                dp[i][0] = grid[i][0] + dp[i - 1][0];
            }

            for (int i = 1; i < grid[0].length; i++) {
                dp[0][i] = grid[0][i] + dp[0][i - 1];
            }


            for (int i = 1; i < grid.length; i++) {
                for (int j = 1; j < grid[0].length; j++) {
                    dp[i][j] = Math.min(dp[i][j - 1] + grid[i][j], dp[i - 1][j] + grid[i][j]);
                }
            }

            return dp[grid.length - 1][grid[0].length - 1];
        }
    }
}
