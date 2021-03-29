package com.keepthinker.example.general.oj;

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
