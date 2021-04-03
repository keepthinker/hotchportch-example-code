package com.keepthinker.example.general.oj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReportIpAddress {

    public static void main(String[] args) {
//        System.out.println(new Solution().restoreIpAddresses("25525511135"));
//        System.out.println(new Solution().restoreIpAddresses("0000"));
//        System.out.println(new Solution().restoreIpAddresses("1111"));
//        System.out.println(new Solution().restoreIpAddresses("010010"));

        // [1.0.10.23, 1.0.102.3, 10.1.0.23, 10.10.2.3, 101.0.2.3]
//        System.out.println(new ThreePointSolution().restoreIpAddresses("101023"));
        System.out.println(new TracebackSolution().restoreIpAddresses("101023"));
    }

    static class TracebackSolution {
        int[] segments =  new int[4];
        List<String> results = new ArrayList<>();

        public List<String> restoreIpAddresses(String s) {
            if (s.length() > 12) {
                return Collections.emptyList();
            }
            dfs(s, 0, 0);
            return results;
        }

        public void dfs(String s, int segId, int pointer) {
            if (segId == 4) {
                if (pointer == s.length()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int seg : segments) {
                        stringBuilder.append(seg).append(".");
                    }
                    results.add(stringBuilder.substring(0, stringBuilder.length() - 1));
                } else {
                    return;
                }
            }
            if (pointer >= s.length()) {
                return;
            }
            if (s.charAt(pointer) == '0') {
                segments[segId] = 0;
                dfs(s, segId + 1, pointer + 1);
                return;

            }
            int target = 0;
            for (int i = pointer; i < pointer + 3 && i < s.length(); i++) {
                target = target * 10 + (s.charAt(i) - '0');
                if (target <= 0xFF) {
                    segments[segId] = target;
                    dfs(s, segId + 1, i + 1);
                }
            }
        }
    }

    static class ThreePointSolution {
        public List<String> restoreIpAddresses(String s) {
            int slen = s.length();
            if (slen > 12) {
                return Collections.emptyList();
            }

            List<String> list = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                for (int j = i + 1; j < slen - 1; j++) {
                    for (int k = j + 1; k < slen; k++) {
                        String str1 = s.substring(0, i);
                        if (str1.length() > 1 && str1.charAt(0) == '0') {
                            continue;
                        }
                        int value1 = Integer.parseInt(str1);
                        str1 = s.substring(i, j);
                        if (str1.length() > 1 && str1.charAt(0) == '0') {
                            continue;
                        }
                        int value2 = Integer.parseInt(str1);
                        str1 = s.substring(j, k);
                        if (str1.length() > 1 && str1.charAt(0) == '0') {
                            continue;
                        }
                        int value3 = Integer.parseInt(str1);
                        str1 = s.substring(k);
                        if (str1.length() > 1 && str1.charAt(0) == '0') {
                            continue;
                        }
                        int value4 = Integer.parseInt(str1);
                        if (value1 <= 255 && value2 <= 255 && value3 <= 255 && value4 <= 255) {
                            list.add(value1 + "." + value2 + '.' + value3 + '.' + value4);
                        }
                    }
                }
            }
            return list;

        }
    }
}
