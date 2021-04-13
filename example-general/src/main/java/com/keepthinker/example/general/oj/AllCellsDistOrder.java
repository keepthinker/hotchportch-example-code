package com.keepthinker.example.general.oj;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AllCellsDistOrder {

    public static void main(String[] args) {
        int[][] result = new Solution().allCellsDistOrder(5, 6, 2, 2);

        for (int i = 0; i < result.length; i++){
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(j == 0 ? result[i][j] + ", " : result[i][j]);
            }
            System.out.println();
        }
    }
    private static class Solution {
        public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
            Map<Integer, List<int[]>> map = new TreeMap<>();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    int delta = Math.abs(i - r0) + Math.abs(j - c0);
                    if (map.containsKey(delta)) {
                        map.get(delta).add(new int[]{i, j});
                    } else {
                        List<int[]> list = new ArrayList<>();
                        list.add(new int[]{i, j});
                        map.put(delta, list);
                    }
                }
            }
            List<int[]> list = new ArrayList<>();
            for (List<int[]> elem : map.values()) {
                list.addAll(elem);
            }

            return list.toArray(new int[C][R]);

        }
    }
}
