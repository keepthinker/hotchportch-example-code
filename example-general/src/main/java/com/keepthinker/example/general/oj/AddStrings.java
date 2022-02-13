package com.keepthinker.example.general.oj;

/**
 * 字符串相加
 * 给定两个字符串形式的非负整数 num1 和 num2 ， 计算它们的和。
 * 提示：
 * num1 和 num2 的长度都小于 5100
 * num1 和 num2 都只包含数字 0-9
 * num1 和 num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 */
public class AddStrings {

    public static void main(String[] args) {
        System.out.println(new AddStrings().addStrings("1231", "123123"));

    }

    public String addStrings(String num1, String num2) {
        String result = "";
        int minSize = Math.min(num1.length(), num2.length());
        int i = num1.length() - 1, j = num2.length() - 1;
        int add = 0;

        while (i >= 0 || j >= 0 || add > 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = x + y;
            if (add >= 0) {
                sum += add;
            }
            add = sum / 10;
            result = (sum % 10) + result;
            i--;
            j--;
        }
        return result;
    }
}
