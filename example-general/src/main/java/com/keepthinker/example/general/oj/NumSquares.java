package com.keepthinker.example.general.oj;

import java.util.Arrays;

public class NumSquares {
    class Solution {
        public int numSquares(int n) {

            int maxQuare = (int)Math.sqrt(n);
            int[] squares = new int[n + 1];
            for (int i = 1; i < squares.length; i++) {
                squares[i] = i * i;
            }
            int[] dp = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= maxQuare; j++) {
                    if (i < squares[j]) {
                        break;
                    }
                    dp[i] = Math.min(dp[i], dp[i - squares[j]] + 1);
                }
            }
            return dp[n];
        }
    }
}
