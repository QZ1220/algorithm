package com.audi.leetcode.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/backspace-string-compare/
 * <p>
 * 给定两个字符串s、t，判断字符串是否相等，其中字符串中的的#表示删除符号
 * <p>
 * Input: s = "ab#c", t = "ad#c"
 * Output: true
 * Explanation: Both s and t become "ac".
 * <p>
 * Input: s = "ab##", t = "c#d#"
 * Output: true
 * Explanation: Both s and t become "".
 * <p>
 * Input: s = "a#c", t = "b"
 * Output: false
 * Explanation: s becomes "c" while t becomes "b".
 *
 * @author : wangquanzhou
 * @date : 2023/4/9 11:35
 */
public class BackspaceStringCompare {

    public boolean backspaceCompare(String s, String t) {
        Stack<Character> stackS = new Stack<>();
        Stack<Character> stackT = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#') {
                if (!stackS.empty()) {
                    stackS.pop();
                }
                continue;
            }
            stackS.push(s.charAt(i));
        }
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == '#') {
                if (!stackT.empty()) {
                    stackT.pop();
                }
                continue;
            }
            stackT.push(t.charAt(i));
        }
        return stackS.equals(stackT);
    }

    public static void main(String[] args) {
        BackspaceStringCompare compare = new BackspaceStringCompare();
        System.out.println(compare.backspaceCompare("y#fo##f", "y#f#o##f"));
    }
}
