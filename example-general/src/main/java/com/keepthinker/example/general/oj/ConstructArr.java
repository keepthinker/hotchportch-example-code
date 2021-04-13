package com.keepthinker.example.general.oj;

public class ConstructArr {

    class Solution {
        public int[] constructArr(int[] a) {
            int[] dpLeft = new int[a.length];
            dpLeft[0] = a[0];
            for (int i = 1; i < a.length; i++) {
                dpLeft[i] = a[i] * dpLeft[i-1];
            }
            int[] dpRight = new int[a.length];
            dpRight[a.length - 1] = a[a.length - 1];
            for (int i = a.length - 2; i >= 0; i++) {
                dpRight[i] = a[i] * dpRight[i+1];
            }
            int[] results = new int[a.length];
            results[0] = dpRight[1];
            results[results.length - 1] = dpLeft[1];
            for (int i = 1; i < results.length - 1; i++) {
                results[i] = dpLeft[i - 1] * dpRight[i + 1];
            }
            return results;
        }
    }


}
