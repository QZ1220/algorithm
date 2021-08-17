package com.audi.leetcode.stack;


import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 * <p>
 * 判断给定的字符串是否合法，字符串只包含()[]{}这几个符号
 *
 * @author: WangQuanzhou
 * @date: 2021-08-17 11:03 AM
 */

public class ValidParentheses {

    public boolean isValid(String s) {
        if (null == s || s.length() < 2) {
            return false;
        }
        // 借助栈来实现合法性判断
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            if ((c == ')' && stack.peek() != '(') || (c == ']' && stack.peek() != '[') || (c == '}' && stack.peek() != '{')) {
                return false;
            }
            stack.pop();
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
    }
}
