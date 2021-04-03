package com.keepthinker.example.general.oj;

public class RevertByteMinCount {
    class Solution {
        public int minFlips(int a, int b, int c) {
            byte[] az = convert(a);
            byte[] bz = convert(b);
            byte[] cz = convert(c);
            int count = 0;
            for (int i = 0; i < 32; i++) {
                if (cz[i] == 0) {
                    if ((az[i] & bz[i]) == 1) {
                        count += 2;
                    } else if ((az[i] | bz[i]) == 1) {
                        count += 1;
                    }
                }
                if (cz[i] == 1) {
                    if ((az[i] | bz[i]) == 0) {
                        count += 1;
                    }
                }
            }
            return count;

        }

        private byte[] convert(int num) {
            byte[] bits = new byte[32];

            for (int i = 0; i < 32; i++) {
                bits[i] = (byte)(num % 2);
                num = num / 2;
            }
            return bits;
        }
    }
}
