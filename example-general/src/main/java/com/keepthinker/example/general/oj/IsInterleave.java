package com.keepthinker.example.general.oj;

public class IsInterleave {

    public static void main(String[] args) {
        String str1 = "accbaabaaabbcbaacbababacaababbcbabaababcaabbbbbcacbaa";
        String str2 = "cabaabcbabcbaaaacababccbbccaaabaacbbaaabccacabaaccbbcbcb";
        String str3 = "accbcaaabbaabaaabbcbcbabacbacbababaacaaaaacbabaabbcbccbbabbccaaaaabaabcabbcaabaaabbcbcbbbcacabaaacccbbcbbaacb";

        /*System.out.println(str1.length());
        System.out.println(str2.length());
        System.out.println(str3.length());
        System.out.println(new Solution().isInterleave(str1, str2, str3));*/

        str1 = "ab";
        str2 = "bc";
        str3 = "bacb";

        System.out.println(str1.length());
        System.out.println(str2.length());
        System.out.println(str3.length());
        System.out.println(new Solution().isInterleave(str1, str2, str3));

    }



    static class Solution {
        public boolean isInterleave(String s1, String s2, String s3) {
            boolean[][] f = new boolean[s1.length() + 1][s2.length() + 1];
            f[0][0] = true;

            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }

            for (int i = 0; i <= s1.length(); i++) {
                for (int j = 0; j <= s2.length(); j++) {
                    if (i > 0) {
                        f[i][j] = f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                    }
                    if (j > 0) {
                        f[i][j] = f[i][j] || f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                    }
                }
            }
            return f[s1.length() - 1][s2.length() - 1];
        }


    }
}
