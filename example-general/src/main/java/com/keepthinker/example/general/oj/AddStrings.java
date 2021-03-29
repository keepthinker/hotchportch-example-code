package com.keepthinker.example.general.oj;

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
