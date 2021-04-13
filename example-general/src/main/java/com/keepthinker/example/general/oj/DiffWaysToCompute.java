package com.keepthinker.example.general.oj;

import java.util.*;

public class DiffWaysToCompute {
    class Solution {
        private Map<String, List<Integer>> map = new HashMap<>();
        public List<Integer> diffWaysToCompute(String expression) {
            if (expression.length() == 0) {
                return Collections.emptyList();
            }
            if (map.containsKey(expression)) {
                return map.get(expression);
            }

            List<Integer> result = new ArrayList<>();
            int num = 0;
            int i = 0;
            while (i < expression.length() && !isOperation(expression.charAt(i))) {
                num = expression.charAt(i) - '0' + num * 10;
                i++;
            }
            if (i == expression.length()) { //如果此时只有一个数字，那么加入结果集，并记录备忘录
                result.add(num);
                map.put(expression, result);
                return result;
            }

            for (int j = 0; j < expression.length(); j++) {
                if (isOperation(expression.charAt(j))) {
                    List<Integer> res1 = diffWaysToCompute(expression.substring(0, j));
                    List<Integer> res2 = diffWaysToCompute(expression.substring(j + 1));
                    for (Integer val1 : res1) {
                        for (Integer val2 : res2) {
                            result.add(compute(val1, expression.charAt(j), val2));
                        }
                    }
                }
            }
            map.put(expression, result);
            return result;
        }
        private int compute(int num1, char oper, int num2) {
            switch (oper) {
                case '+' : return num1 + num2;
                case '-' : return num1 - num2;
                case '*' : return num1 * num2;
            }
            return -1;
        }
        private boolean isOperation(char ch) {
            return ch == '+' || ch == '-' || ch == '*';
        }
    }
}
