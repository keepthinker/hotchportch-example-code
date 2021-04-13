package com.keepthinker.example.general.oj;

public class RemoveKdigits {
    public static void main(String[] args) {
        System.out.println(new Solution().removeKdigits("1432219", 3));
        System.out.println(new Solution().removeKdigits("10200", 1));
        System.out.println(new Solution().removeKdigits("10", 2));
        System.out.println(new Solution().removeKdigits("100001", 2));
    }

    static class Solution {
        public String removeKdigits(String num, int k) {
            if (num.length() <= k ) {
                return "0";
            }
            char[] resChars = new char[num.length() - k];
            char maxValue = '9' + 1;
            int i = 0;
            for (int j = 0; j < resChars.length; j++) {
                int maxIndex = 0;
                for (; i <= num.length() - (resChars.length - j); i++) {
                    if (num.charAt(i) < maxValue) {
                        maxValue = num.charAt(i);
                        maxIndex = i;
                    }
                }
                i = maxIndex + 1;
                resChars[j] = maxValue;
                maxValue = '9' + 1;
            }
            StringBuilder builder = new StringBuilder();
            boolean shouldRemove0 = true;
            for (int j = 0; j < resChars.length; j++) {
                if (shouldRemove0 && resChars[j] == '0') {
                    continue;
                }
                shouldRemove0 = false;
                builder.append(resChars[j]);
            }
            return builder.length() == 0 ? "0" : builder.toString();
        }
    }
}
