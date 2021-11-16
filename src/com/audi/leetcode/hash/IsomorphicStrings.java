package com.audi.leetcode.hash;

/**
 * https://leetcode.com/problems/isomorphic-strings/
 * <p>
 * 判断输入的两个字符串是否是同构的
 * <p>
 * Example 1:
 * <p>
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 * <p>
 * Input: s = "paper", t = "title"
 * Output: true
 *
 * @author : wangquanzhou
 * @date : 2021/11/16 22:54
 */
public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        // 题设s、t长度大于0，且二者长度相等
        int len = s.length();
        if (len == 1) {
            return Boolean.TRUE;
        }
        for (int i = 0; i < len - 1; i++) {
            if ((s.charAt(i) == s.charAt(i + 1) && t.charAt(i) == t.charAt(i + 1))
                    || (s.charAt(i) != s.charAt(i + 1) && t.charAt(i) != t.charAt(i + 1))) {
                continue;
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public static void main(String[] args) {

    }
}
