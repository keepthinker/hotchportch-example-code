package com.keepthinker.example.general;

/**
 * Created by keepthinker on 2017/8/1.
 */
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] arr = new int[nums1.length + nums2.length];
        int i1 = 0, i2 = 0;
        int j = 0;
        while(j < arr.length){
            if(i1 >= nums1.length){
                for(;i2 < nums2.length;j++, i2++){
                    arr[j] = nums2[i2];
                }
                break;
            }
            if(i2 >= nums2.length){
                for(;i1 < nums1.length;j++, i1++){
                    arr[j] = nums1[i1];
                }
                break;
            }

            int k1 = nums1[i1];
            int k2 = nums2[i2];
            if(k1 > k2){
                arr[j] = k2;
                i2++;
            }else{
                arr[j] = k1;
                i1++;
            }
            j++;

        }

        boolean isEven = arr.length % 2 == 0;

        if(!isEven) {
            return arr[arr.length / 2];
        }else{
            return (arr[arr.length / 2] + arr[arr.length / 2 - 1]) /2.0;
        }

    }

    public static void main(String[] args){
        System.out.println(new Solution().findMedianSortedArrays(
                new int[]{1, 3, 4}, new int[]{2,3, 3}
        ));
    }
}