package com.keepthinker.example.general.oj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by keepthinker on 2018/10/3.
 */
public class TreeSum {
    public static void main(String[] args){

        System.out.println(new Solution().threeSum(new int[] {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6}));
    }

    private static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> list = new ArrayList<>();
            for(int i = 0; i < nums.length - 2; i++){
                for(int j = i + 1; j < nums.length - 1; j++){
                    for( int k = j + 1; k < nums.length; k++){
                        if (nums[i] + nums[j] + nums[k] == 0) {
                            List<Integer> a = new ArrayList<>();
                            a.add(nums[i]); a.add(nums[j]); a.add(nums[k]);
                            if(list.size() > 0) {
                                if(list.get(list.size() - 1).equals(a)) {
                                    continue;
                                }
                            }
                            list.add(a);
                        }
                    }
                }
            }
            return list;
        }
    }
}
