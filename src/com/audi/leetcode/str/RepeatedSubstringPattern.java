package com.audi.leetcode.str;

/**
 * https://leetcode.com/problems/repeated-substring-pattern/
 * <p>
 * 求一个字符串是否可以由其子串重复多次得到
 * <p>
 * <p>
 * Input: s = "abab"
 * Output: true
 * Explanation: It is the substring "ab" twice.
 *
 * <p>
 * Input: s = "aba"
 * Output: false
 *
 * @author : wangquanzhou
 * @date : 2023/8/18 19:28
 */
public class RepeatedSubstringPattern {

    /**
     * 移动匹配算法
     * <p>
     * 如果s满足可以由其子串重复多次获得，那么s+s的字符串，除去首尾字母，也包含s
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        if (s.length() < 2) {
            return false;
        }
        String tmpS = s;
        s = s + s;
        return s.substring(1, s.length() - 1).contains(tmpS);
    }

    public static void main(String[] args) {
        String s = "abcd";
        s = s + s;
        System.out.println(s);
        System.out.println(s.substring(1, 4));
        System.out.println(s.substring(1, s.length() - 1));
    }
}
