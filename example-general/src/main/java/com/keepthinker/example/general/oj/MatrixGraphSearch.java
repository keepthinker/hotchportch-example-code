package com.keepthinker.example.general.oj;

/**
 * 岛屿数量
 * 给你一个由 '1'（陆地） 和 '0'（水） 组成的的二维网格， 请你计算网格中岛屿的数量。岛屿总是被水包围， 并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外， 你可以假设该网格的四条边均被水包围。
 * 示例 1：
 * 输入： grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出： 1
 * 示例 2：
 * 输入： grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ] 输
 * 出： 3
 */
public class MatrixGraphSearch {

    public static void main(String[] args) {
//        char[][] grid = {
//                {'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'}
//        };
//
//        System.out.println(new MatrixGraphSearch().numIslands(grid));
//
//
//        char[][] grid2 = {
//                {'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '1', '0', '1'}
//        };
//
//        System.out.println(new MatrixGraphSearch().numIslands(grid2));


        char[][] grid3 = {
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'1', '1', '1'}
        };

        System.out.println(new MatrixGraphSearch().numIslands(grid3));



    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    num++;
                }
            }
        }

        return num;
    }

    public void dfs(char[][] graph, int i, int j) {

        if (graph[i][j]=='1') {
            System.out.println("(" + i + ',' + j + ')');
        }

        graph[i][j] = '0';
        int rowLength = graph.length;
        int columnLength = graph[0].length;

        if (j + 1 < columnLength && graph[i][j + 1] == '1') dfs(graph, i, j + 1);
        if (i + 1 < rowLength && graph[i + 1][j] == '1') dfs(graph, i + 1, j);
        if (i - 1 >= 0 && graph[i - 1][j] == '1') dfs(graph, i - 1, j);
        if (j - 1 >= 0&& graph[i][j - 1] == '1') dfs(graph, i, j - 1);
    }
}
