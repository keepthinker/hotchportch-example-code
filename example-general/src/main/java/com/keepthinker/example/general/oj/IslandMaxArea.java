package com.keepthinker.example.general.oj;

import java.util.concurrent.atomic.AtomicInteger;

public class IslandMaxArea {
    public static void main(String[] args) {
        int[][] grid = {
            {1,0,1},{1,1,1}
        };
        System.out.println(new Solution().maxAreaOfIsland(grid));
        int[][] grid2 = {
                {0,1},{1,1},{1,1}
        };
        System.out.println(new Solution().maxAreaOfIsland(grid2));
    }

    static class Solution {

        private int rowSize;
        private int columnSize;
        public int maxAreaOfIsland(int[][] grid) {
            int maxArea = 0;
            rowSize = grid.length;
            columnSize = grid[0].length;
            for (int i = 0; i < rowSize; i++) {
                for (int j = 0; j < columnSize; j++) {
                    if (grid[i][j] == 1) {
                        AtomicInteger counter = new AtomicInteger(1);
                        grid[i][j] = 0;
                        if (j + 1 < columnSize && grid[i][j + 1] == 1) dfs(grid, i, j + 1, counter);
                        if (i + 1 < rowSize && grid[i + 1][j] == 1) dfs(grid, i + 1, j, counter);
                        if (j - 1 >= 0 && grid[i][j - 1] == 1) dfs(grid, i, j - 1, counter);
                        if (maxArea < counter.get()) {
                            maxArea = counter.get();
                        }
                    }
                }
            }
            return maxArea;

        }

        private void dfs(int[][]grid, int i, int j, AtomicInteger counter) {
            counter.incrementAndGet();
            grid[i][j] = 0;
            if (j + 1 < columnSize && grid[i][j + 1] == 1) dfs(grid, i, j + 1, counter);
            if (i + 1 < rowSize && grid[i + 1][j] == 1) dfs(grid, i + 1, j, counter);
            if (j - 1 >= 0 && grid[i][j - 1] == 1) dfs(grid, i, j - 1, counter);
            if (i - 1 >= 0 && grid[i - 1][j] == 1) dfs(grid, i - 1, j, counter);
        }
    }
}
