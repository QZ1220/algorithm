package com.audi.leetcode.math;


import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator/
 * <p>
 * 这道题容易忽略的几个点：
 * 1、字符串里可能没有括号
 * 2、对于-的处理，并且-可能出现在字符串的开头
 * 3、字符串可能没有+-号，就是个纯数字
 *
 * @author: WangQuanzhou
 * @date: 2020/3/31 20:42
 */
public class BasicCalculator {
    public int calculate(String s) {
        if (null == s || "".equals(s)) {
            return 0;
        }
        // 去除前后无用空格
        s = s.trim();
        // 使用两个栈，完成字符串的翻转
        Stack<Character> firstStack = new Stack<>();
        Stack<Character> secondStack = new Stack<>();
        int result = 0;

        // 循环遍历字符串
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 空格直接丢弃
            if (' ' == c) {
                continue;
            }
            // 遇到右括号就对firstStack进行出栈操作 数据依次存入secondStack
            if (')' == c) {
                while (firstStack.peek() != '(') {
                    secondStack.add(firstStack.pop());
                }
                firstStack.pop();
                // 对secondStack进行求和
                result = sum(result, secondStack);
                // 将求和的结果继续入栈到firstStack，进行后续的运算
                push(result, firstStack);
                result = 0;
            } else {
                firstStack.add(c);
            }
        }

        secondStack.clear();
        while (!firstStack.empty()) {
            char c = firstStack.pop();
            if (' ' == c) {
                continue;
            }
            if (!secondStack.empty() && secondStack.peek() == c && '-' == c) {
                secondStack.pop();
                secondStack.add('+');
                continue;
            }
            secondStack.add(c);
        }
        return sum(result, secondStack);
    }

    /**
     * 对stack进行求和
     *
     * @param result
     * @param stack
     * @return 返回求和的结果
     */
    private int sum(int result, Stack<Character> stack) {
        if (stack.empty()) {
            return result;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.empty()) {
            Character head = stack.pop();
            // 遇到+号就对之前stringBuilder内的数据进行求和
            if ('+' == head) {
                result = result + Integer.valueOf(stringBuilder.toString());
                //计算完成需要清空stringBuilder,下次append的时候才不会有之前的计算数据
                stringBuilder = new StringBuilder();
            } else if ('-' == head) {
                // 遇到-号，需要特殊判断因为-号可以出现在字符串的开头，+就不会有这种情况
                result = result + (stringBuilder.length() == 0 ? 0 : Integer.valueOf(stringBuilder.toString()));
                // -号当成加负数的形式处理
                stringBuilder = new StringBuilder().append(head);
            } else {
                stringBuilder.append(head);
            }
        }
        // 处理结尾的数据
        String tempString = stringBuilder.toString();
        if ("null" != tempString || "" != tempString) {
            result = result + Integer.valueOf(tempString);
        }
        return result;
    }

    /**
     * 将中间过程计算的结果num push进入stack
     *
     * @param num
     * @param stack
     */
    private void push(int num, Stack<Character> stack) {
        String s = String.valueOf(num);
        for (int i = 0; i < s.length(); i++) {
            if (!stack.empty() && stack.peek() == s.charAt(i) && stack.peek() == '-') {
                stack.pop();
                stack.push('+');
                continue;
            }
            stack.push(s.charAt(i));
        }
    }

    public static void main(String[] args) {
//        String s = "(1+(4+5+2)-3)+(6+8)";
//        String s = "(5-(1+(5)))";
//        String s = "5-(1+(5))";
//        String s = "(1+1)";
//        String s = "(5-6)";
        String s = "(11+(-11)-1+(7-9))";
        BasicCalculator basicCalculator = new BasicCalculator();
        System.out.println(basicCalculator.calculate(s));
    }
}
