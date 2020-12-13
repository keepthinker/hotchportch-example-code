package com.keepthinker.example.general.oj;

import java.util.ArrayList;
import java.util.List;

public class NumOfDifferentBinarySearchTree {
    public static void main(String[] args) {

    }


    public int generateTrees(int n) {
        int[] result = new int[n + 1];
        result[0] = 1;
        result[1] = 1;

        for(int i = 2; i <= n; i++) {
            for(int j = 1; j <= i; i++) {
                result[i] += result[j - 1] * result[i - j];
            }
        }

        return result[n];

    }
}
