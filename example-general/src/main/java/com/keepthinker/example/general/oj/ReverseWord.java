package com.keepthinker.example.general.oj;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseWord {
    public static void main(String[] args) {

    }

    private static class Solution {
        public String reverseWords(String s) {
            List<String> list = Arrays.asList(s.split(" "));
            Collections.reverse(list);
            return String.join(" ", list);
        }
    }
}
