package com.audi.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个由大写或者小写字母组成的字符串，求由这个字符串法字符可以组成的回文串，返回这个回文串的长度
 *
 * @author : wangquanzhou
 * @date : 2023/3/25 13:46
 */
public class LongestPalindrome {

    public int longestPalindrome(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int len = 0;
        boolean firstOdd = true;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (isEven(entry.getValue())) {
                len += entry.getValue();
            } else {
                if (firstOdd) {
                    len += entry.getValue();
                    firstOdd = false;
                } else {
                    len = len + entry.getValue() - 1;
                }
            }
        }
        return len;
    }

    /**
     * 判断一个数字是否是偶数
     *
     * @param num
     * @return
     */
    public boolean isEven(int num) {
        return ((num / 2) * 2) == num;
    }

    public static void main(String[] args) {
        LongestPalindrome palindrome = new LongestPalindrome();
        System.out.println(palindrome.longestPalindrome("ababababa"));
        System.out.println(palindrome.longestPalindrome("ccc"));
    }
}
