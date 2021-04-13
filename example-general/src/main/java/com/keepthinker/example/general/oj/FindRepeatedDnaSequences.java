package com.keepthinker.example.general.oj;

import java.util.*;

public class FindRepeatedDnaSequences {
    public static void main(String[] args) {
        System.out.println(new Solution().findRepeatedDnaSequences("AAAAAAAAAAAAA"));
    }


    private static class Solution {
        public List<String> findRepeatedDnaSequences(String s) {
            Set<String> results = new HashSet<>();
            Set<String> set = new HashSet<>();
            for (int i = 0, len = s.length() - 9; i < len; i++) {
                String str = s.substring(i, i + 10);
                if (!set.add(str)) {
                    results.add(str);
                }
            }
            return new ArrayList<>(results);
        }
    }

}
