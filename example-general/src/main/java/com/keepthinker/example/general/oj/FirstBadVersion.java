package com.keepthinker.example.general.oj;

public class FirstBadVersion {


    public class Solution  {
        public int firstBadVersion(int n, int k) {
            int left = 1;
            int right = n;

            while (left < right) {
                int mid = (right + left) / 2;
                if (mid >= k) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }
    }
}
