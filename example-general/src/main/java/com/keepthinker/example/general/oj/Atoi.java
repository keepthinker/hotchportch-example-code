package com.keepthinker.example.general.oj;

/**
 * Created by keepthinker on 2018/9/30.
 */
public class Atoi {
    public static void main(String[] args){

        System.out.println(new Atoi.Solution().myAtoi("12314"));
        System.out.println(new Atoi.Solution().myAtoi("-12314"));
        System.out.println(new Atoi.Solution().myAtoi("--+-12314"));
        System.out.println(new Atoi.Solution().myAtoi("asdfasd--+-12314asdfasdf123"));
        System.out.println(new Atoi.Solution().myAtoi("asdf---asd--+-123141231"));
        System.out.println(new Atoi.Solution().myAtoi("words and 987"));
        System.out.println(new Atoi.Solution().myAtoi("4193 with words"));
        System.out.println(new Atoi.Solution().myAtoi("-48748465465468764343"));
        System.out.println(new Atoi.Solution().myAtoi("+48748465465468764343"));
        System.out.println(new Atoi.Solution().myAtoi("48748465465468764343"));
        System.out.println(new Atoi.Solution().myAtoi("   -42"));
        System.out.println(new Atoi.Solution().myAtoi("1"));
        System.out.println(new Atoi.Solution().myAtoi("-1"));
    }

    public static class Solution {
        public int myAtoi(String str) {
            int positive = 1;
            long result = 0;
            boolean found = false;
            if(str.length() == 1 && (str.charAt(0)>= '0' && str.charAt(0) <= '9')){
                return str.charAt(0) - '0';
            }
            for(int i = 0, len = str.length(); i < len - 1; i++){
                if((str.charAt(i) == '+' || str.charAt(i) == '-') &&
                        (str.charAt(i + 1) >= '0' && str.charAt(i + 1) <= '9')
                        || (str.charAt(i)>= '0' && str.charAt(i) <= '9')){

                    if(str.charAt(i) == '+'){
                        positive = 1;
                    } else if(str.charAt(i) == '-') {
                        positive = 0;
                    }

                    if(str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                        result = str.charAt(i) - '0';
                    }

                    for(int j = i + 1; j < len; j++) {
                        if(str.charAt(j) >= '0' && str.charAt(j) <= '9') {
                            int num = str.charAt(j) - '0';
                            result = (result * 10) + num;
                            if(result > Integer.MAX_VALUE) {
                                return positive == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                            }
//                            System.out.println(result);
                            found = true;
                        } else{
                            break;
                        }
                    }


                } else if(str.charAt(i) == ' '){
                    continue;
                }
                break;

            }
            if(positive == 1) {
                return (int)result;
            } else{
                return -(int)result;
            }
        }
    }
}
