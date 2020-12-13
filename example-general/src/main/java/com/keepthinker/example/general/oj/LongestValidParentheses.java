package com.keepthinker.example.general.oj;

public class LongestValidParentheses {

    //"(()())"
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int maxLength = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')' && s.charAt(i - 1) == '(') {
                if(i > 1) {
                    dp[i] = dp[i - 2] + 2;
                } else {
                    dp[i] = 2;
                }
            } else if(s.charAt(i) == ')' && s.charAt(i - 1)== ')') {
                if ((i - dp[i - 1] > 0) && s.charAt(i - dp[i - 1] - 1) == '(') {
                    if(i - dp[i - 1] - 2 > 0) {
                        dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
                    } else {
                        dp[i] = dp[i - 1] + 2;
                    }
                }
            }
            maxLength = Math.max(dp[i], maxLength);
        }

        return maxLength;
    }
}
