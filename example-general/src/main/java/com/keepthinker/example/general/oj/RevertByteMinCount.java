package com.keepthinker.example.general.oj;

/**
 * 或运算的最小翻转次数
 *
 * 给你三个正整数 a、b 和 c。
 *
 * 你可以对 a 和 b 的二进制表示进行位翻转操作，返回能够使按位或运算   a OR b == c  成立的最小翻转次数。
 *
 * 「位翻转操作」是指将一个数的二进制表示任何单个位上的 1 变成 0 或者 0 变成 1 。
 *
 * 输入：a = 2, b = 6, c = 5
 * 输出：3
 * 解释：翻转后 a = 1 , b = 4 , c = 5 使得 a OR b == c
 * 示例 2：
 *
 * 输入：a = 4, b = 2, c = 7
 * 输出：1
 * 示例 3：
 *
 * 输入：a = 1, b = 2, c = 3
 * 输出：0
 *
 */
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
