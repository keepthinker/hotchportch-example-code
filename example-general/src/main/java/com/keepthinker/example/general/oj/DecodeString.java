package com.keepthinker.example.general.oj;

public class DecodeString {

    public static void main(String[] args) {
//        System.out.println(new Solution().decodeString("3[a]2[bc]"));
        System.out.println(new Solution().decodeString("3[a2[c]]"));
        System.out.println(new Solution().decodeString("3[a2[c2[add2[d]]]]"));
    }

    private static class Solution {

        private int i = 0;

        public String decodeString (String s) {
            StringBuilder stringBuilder = new StringBuilder();
            return getString(s);
        }


        public String getString(String s) {
            if (i == s.length() || s.charAt(i) == ']') {
                return "";
            }

            char cur = s.charAt(i);

            String ret = "";
            if (Character.isDigit(cur)) {
                int multi = 0;
                do {
                    multi = multi * 10 + (cur - '0');
                    i++;
                    cur = s.charAt(i);
                } while(Character.isDigit(cur));
                i++;
                String str = getString(s);
                i++;
                while (multi-- > 0) {
                    ret = ret + (str);
                }
            } else {
                ret = String.valueOf(s.charAt(i++));
            }
            return ret + getString(s);

        }

    }
}
