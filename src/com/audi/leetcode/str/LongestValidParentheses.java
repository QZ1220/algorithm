package com.audi.leetcode.str;


import java.util.Stack;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 *
 * <p>
 * 参考 https://zhuanlan.zhihu.com/p/41951874
 * <p>
 * 本题的关键：
 * 1、只入栈'('
 * 2、需要使用start记录下个有效括号的开始，并且两个有效括号可能紧邻，此时需要两者的和
 *
 * @author: WangQuanzhou
 * @date: 2020/4/19 14:04
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if (null == s || s.length() < 2) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int max = 0, start = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    start = i + 1;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        max = Math.max(max, i - start + 1);
                    } else {
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "()(())";
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        System.out.println(longestValidParentheses.longestValidParentheses(s));
    }
}
