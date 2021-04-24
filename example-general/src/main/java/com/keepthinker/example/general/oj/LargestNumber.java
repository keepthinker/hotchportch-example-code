package com.keepthinker.example.general.oj;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class LargestNumber {

    public static void main(String[] args) {
        System.out.println(new LargestNumber().largestNumber(new int[]{111311, 1113}));
        System.out.println(new LargestNumber().largestNumber(new int[]{1113, 111311}));
        System.out.println(new LargestNumber().largestNumber(new int[]{3,30,34,5,9}));
    }


    public String largestNumber(int[] nums) {
        String[] numStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStrs[i] = Integer.toString(nums[i]);
        }
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        };
        Arrays.sort(numStrs, comparator);
        StringBuilder builder = new StringBuilder();
        for (String str : numStrs) {
            builder.append(str);
        }

        return builder.toString();
    }

}
