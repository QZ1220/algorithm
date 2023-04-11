package com.audi.leetcode.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/decode-string/description/
 * <p>
 * 给定一个加密的字符串，求其解密后的字符串
 * <p>
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * <p>
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * <p>
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 * <p>
 * 借助stack的特性求解
 *
 * @author : wangquanzhou
 * @date : 2023/4/9 12:35
 */
public class DecodeString {

    public static final Character LEFT_SQUARE_BRACKET = '[';
    public static final Character RIGHT_SQUARE_BRACKET = ']';


    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 如果遍历到右括号，那么就反向寻找左括号，以及左括号前面的数字
            // 注意数字可能不止是个位数
            if (RIGHT_SQUARE_BRACKET.equals(c)) {
                pop(stack);
            } else {
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    private String decode(int times, String subStr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= times; i++) {
            sb.append(subStr);
        }
        return sb.toString();
    }

    private void push(Stack<Character> stack, String str) {
        if (null != str && str.length() > 0) {
            for (int i = 0; i < str.length(); i++) {
                stack.push(str.charAt(i));
            }
        }
    }

    private void pop(Stack<Character> stack) {
        StringBuilder tmpSb = new StringBuilder();
        Stack<Integer> tmpStack = new Stack<>();
        int times = 1;
        for (; ; ) {
            Character popChar = stack.pop();
            if (popChar.equals(LEFT_SQUARE_BRACKET)) {

                while (!stack.isEmpty()) {
                    Character pop = stack.peek();
                    if (pop >= 48 && pop <= 57) {
                        tmpStack.push(stack.pop() - 48);
                    } else {
                        break;
                    }
                }

                times = convert(tmpStack);
                String decodeSubStr = decode(times, tmpSb.reverse().toString());
                push(stack, decodeSubStr);
                break;
            } else {
                tmpSb.append(popChar);
            }
        }
    }

    /**
     * 将栈的元素弹出组成一个数字，注意出栈顺序就是数字的顺序
     *
     * @param stack
     * @return
     */
    private Integer convert(Stack<Integer> stack) {
        int times = stack.pop();
        while (!stack.isEmpty()) {
            times = times * 10 + stack.pop();
        }
        return times;
    }

    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        String s1 = "10[leet]";
        DecodeString decodeString = new DecodeString();
        System.out.println(decodeString.decodeString(s));
        System.out.println(decodeString.decodeString(s1));

    }
}
