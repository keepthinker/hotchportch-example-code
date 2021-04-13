package com.keepthinker.example.general.oj;

public class WordSearchMatrix {

    public static void main(String[] args) {

        char[][] board = {
                {'A','B','C','E'},
                {'S','F','E','S'},
                {'A','D','E','E'}
        };
        String word = "ABCESEEEFS";
        System.out.println(new Solution().exist(board, word));

    }
    private static class Solution {
        public boolean exist(char[][] board, String word) {

            int rowSize = board.length;
            int columnSize = board[0].length;

//            Set<String> visited = new HashSet(); // 可以创建一个matrix来记录已访问的元素
            boolean[][] visited = new boolean[rowSize][columnSize];

            for (int i = 0; i < rowSize; i++) {
                for (int j = 0; j < columnSize; j++) {
                    if (dfs(board, word, i, j, 0, visited)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(char[][]board, String word, int i, int j, int k, boolean[][] visited) {
            int rowSize = board.length;
            int columnSize = board[0].length;
            if (i < 0 || i >= rowSize || j < 0 || j >= columnSize || visited[i][j]) {
                return false;
            }
            if (board[i][j] == word.charAt(k)) {
                if (k == word.length() - 1) {
                    return true;
                }
//                visited.add(i + ":" + j);
                visited[i][j] = true;
                k++;
                boolean result = dfs(board, word, i - 1, j, k, visited)
                        || dfs(board, word, i + 1, j, k, visited)
                        || dfs(board, word, i, j - 1, k, visited)
                        || dfs(board, word, i, j + 1, k, visited);
                if (!result) {
//                    visited.remove(i + ":" + j); //查找失败，则从visited记录中删除
                    visited[i][j] = false;
                }

                return result;
            } else {
                return false;
            }
        }
    }
}
