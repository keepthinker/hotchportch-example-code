package com.keepthinker.example.general.oj;

public class CoinChange {

    public static void main(String[] args) {
        System.out.println(new Solution().coinChange(new int[]{2}, 3));
    }

    /**
     * 动态规划方程：
     * f(x) = min(f(i - cj) + 1, 其中j = (0,
     */
    static  class Solution {


        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            dp[amount] = amount + 1;

            for (int i = 1; i <= amount; i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < coins.length; j++) {
                    int remain = i - coins[j];
                    if (remain >= 0) {
                        min = Math.min(min, dp[remain] + 1);
                        dp[i] = min;
                    }
                }
            }
            return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
        }
    }
/*&
public class Solution {
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
 */

}
