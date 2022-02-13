package com.keepthinker.example.general.oj;

import java.util.*;

/**
 * 给你一个数组 routes ， 表示一系列公交线路， 其中每个 routes[i] 表示一条公交线路， 第 i
 * 辆公交车将会在上面循环行驶。
 * 例如， 路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 ->
 * 7 -> 1 -> ... 这样的车站路线行驶。
 * 现在从 source 车站出发（初始时不在公交车上） ， 要前往 target 车站。 期间仅可乘坐公
 * 交车。求出 最少乘坐的公交车数量 。 如果不可能到达终点车站， 返回 -1 。
 * 示例 1：
 * 输入： routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * 输出： 2
 * 解释： 最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
 * 示例 2：
 * 输入： routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * 输出： -1
 * 提示：
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 105
 * routes[i] 中的所有值 互不相同
 * sum(routes[i].length) <= 105
 * 0 <= routes[i][j] < 106
 * 0 <= source, target < 106
 * 广度优先搜索：
 * 我的解法， 先构建一个车站号到车路线的 map。 然后找到起点所有车路线加入到 queue 中，
 * 然后取出队头， 找到非起点的车站号， 查找下一个路线， 直到找到终点站， 结束后， 回溯继
 * 续查找， 查找过程中有个数字记录最小换车次数。
 */
public class NumBusesToDestination {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) return 0;
        int N = routes.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            Arrays.sort(routes[i]);
            graph.add(new ArrayList());
        }
        Set<Integer> seen = new HashSet<>();
        Set<Integer> targets = new HashSet();
        Queue<Point> queue = new ArrayDeque();
// Build the graph. Two buses are connected if
// they share at least one bus stop.
        for (int i = 0; i < N; ++i)
            for (int j = i + 1; j < N; ++j)
                if (intersect(routes[i], routes[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
// Initialize seen, queue, targets.
// seen represents whether a node has ever been enqueued to queue.
// queue handles our breadth first search.
// targets is the set of goal states we have.
        for (int i = 0; i < N; ++i) {
            if (Arrays.binarySearch(routes[i], S) >= 0) {
                seen.add(i);
                queue.offer(new Point(i, 0));
            }
            if
            (Arrays.binarySearch(routes[i], T) >= 0)
                targets.add(i);
        }
        while (!queue.isEmpty()) {
            Point info = queue.poll();
            int node = info.x, depth = info.y;
            if (targets.contains(node)) return depth + 1;
            for (Integer nei : graph.get(node)) {
                if (!seen.contains(nei)) {
                    seen.add(nei);
                    queue.offer(new Point(nei, depth + 1));
                }
            }
        }
        return -1;
    }

    public boolean intersect(int[] A, int[] B) {
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            if (A[i] == B[j]) return true;
            if (A[i] < B[j]) i++;
            else j++;
        }
        return false;
    }

    private static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
