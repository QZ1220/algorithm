package com.audi.leetcode.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/description/
 * <p>
 * 题目要求移除一个给定字符串的所有相邻字符相等的子串
 * <p>
 * Input: s = "abbaca"
 * Output: "ca"
 * <p>
 * Input: s = "azxxzy"
 * Output: "ay"
 * <p>
 * 使用栈来实现
 *
 * @author : wangquanzhou
 * @date : 2025/11/20 11:54
 */
public class RemoveAllAdjacentDuplicatesInString {

    public String removeDuplicates(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        // 定义一个栈
        Stack stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!stack.isEmpty() && stack.peek().equals(c)) {
                stack.pop();
            }else {
                stack.push(c);
            }
        }
        if (stack.isEmpty()){
            return "";
        }
        // 获取栈中剩余的字符串内容
        Stack tmpStack = new Stack<Character>();
        while (!stack.isEmpty()){
            tmpStack.push(stack.pop());
        }
        StringBuilder sb = new StringBuilder(tmpStack.size());
        while (!tmpStack.isEmpty()){
            sb.append(tmpStack.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {

    }
}
