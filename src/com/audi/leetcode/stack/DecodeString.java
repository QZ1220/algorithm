package com.audi.leetcode.stack;

import com.audi.leetcode.tree.LevelTree;

import java.util.LinkedList;
import java.util.Queue;
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
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > 0 && c < 10) {
                stack.push(c);
            } else if (LEFT_SQUARE_BRACKET.equals(c)) {
                stack.push(c);
            }
        }
        return "1";
    }

    private String decode(int times, String subStr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= times; i++) {
            sb.append(subStr);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println((int)'a');
        System.out.println((int)'z');
    }
}
