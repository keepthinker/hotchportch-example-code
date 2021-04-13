package com.keepthinker.example.general.oj;

import java.util.HashMap;
import java.util.Map;

public class IsIsomorphic {
    class Solution {
        public boolean isIsomorphic(String s, String t) {
            Map<Character, Character> map1 = new HashMap<>();
            Map<Character, Character> map2 = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                Character existedChar = map1.get(s.charAt(i));
                if (map1.get(s.charAt(i)) == null && map2.get(s.charAt(i)) == null) {
                    map1.put(s.charAt(i), t.charAt(i));
                    map2.put(t.charAt(i), s.charAt(i));
                } else {
                    if (existedChar != t.charAt(i) || map2.get(s.charAt(i)) != s.charAt(i)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
