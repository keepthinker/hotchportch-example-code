package com.keepthinker.example.general.oj;

/**
 * Created by keepthinker on 2019/3/3.
 */
public class PalindromicString {

    public static void main(String[] args){
//        System.out.println(new PalindromicString().longestPalindrome("abc"));
//        System.out.println(new PalindromicString().longestPalindrome("aaa"));
//        System.out.println(new PalindromicString().longestPalindrome("aaabccba"));
//        System.out.println(new PalindromicString().longestPalindrome("aaaaaaa"));
        System.out.println(new PalindromicString().longestPalindrome("aaabbbccc"));
//        System.out.println(new PalindromicString().longestPalindrome("abababab"));
    }
    public String longestPalindrome(String s) {
        if(s.length() < 1){
            return s;
        }
        int len;
        int resultLen = 1;
        int begin = 0;
        for(len = 2; len <= s.length(); len++) {
            for (int i = 0; i <= s.length() - len; i++) {
                if (isPalindromic(s.substring(i, i + len))) {
                    begin = i;
                    resultLen = len;
                }
            }
        }
        return s.substring(begin, begin + resultLen);
    }

    public boolean isPalindromic(String s){
        for(int i = 0; i < s.length() / 2; i++){
            if(s.charAt(i) != s.charAt(s.length() - i - 1)){
                return false;
            }
        }
        return true;
    }
}
