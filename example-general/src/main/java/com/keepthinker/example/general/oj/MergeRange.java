package com.keepthinker.example.general.oj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并区间
 * 示例 1：
 * 输入： intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出： [[1,6],[8,10],[15,18]]
 * 解释： 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 * 输入： intervals = [[1,4],[4,5]]
 * 输出： [[1,5]]
 * 解释： 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class MergeRange {

    public static void main(String[] args) {

        int [][] intervals = {
            {1,3},{2,6},{15,18},{8,10}
        };

        new Solution().merge(intervals);


        int [][] intervals2 = {
                {1,3},{2,6},{15,18},{8,15}
        };

        new Solution().merge(intervals2);

    }


    private static class Solution {
        public int[][] merge(int[][] intervals) {
            if (intervals == null) {
                return null;
            }

            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });

            List<int[]> results  = new ArrayList<>();
            int[] temp = intervals[0];

            for (int i = 1; i < intervals.length; i++) {
                if (temp[1] < intervals[i][0]) {
                    results.add(temp);
                    temp = intervals[i];
                } else if (temp[1] >= intervals[i][0] && temp[1] < intervals[i][1]) {
                    temp[1] = intervals[i][1];
                }
            }
            results.add(temp);

            return results.toArray(new int[results.size()][]);

        }
    }
}
