package com.keepthinker.example.general.oj;

public class CompareVersion {
    public static void main(String[] args) {
        System.out.println(new Solution().compareVersion("7.5.2.4", "7.5.3"));
        System.out.println(new Solution().compareVersion("1.0.0", "1.0"));
        System.out.println(new Solution().compareVersion("0.0.0", "0.0"));
        System.out.println(new Solution().compareVersion("1.0", "1.0.0"));
    }

    private static class Solution {
        public int compareVersion(String version1, String version2) {
            String[] strs1 = version1.split("\\.");
            String[] strs2 = version2.split("\\.");
            int i = 0;
            int minLen = Math.min(strs1.length, strs2.length);
            for (; i < minLen; i++) {
                if (Integer.parseInt(strs1[i]) > Integer.parseInt(strs2[i])){
                    return 1;
                } else if (Integer.parseInt(strs1[i]) < Integer.parseInt(strs2[i])) {
                    return -1;
                }
            }
            if (strs1.length > strs2.length) {
                for (; i < strs1.length; i++) {
                    if (Integer.parseInt(strs1[i]) != 0) {
                        return 1;
                    }
                }
            } else {
                for (; i < strs2.length; i++) {
                    if (Integer.parseInt(strs2[i]) != 0) {
                        return -1;
                    }
                }
            }
            return 0;
        }
    }
}
