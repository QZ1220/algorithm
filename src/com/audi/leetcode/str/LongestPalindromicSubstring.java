package com.audi.leetcode.str;


/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * <p>
 * 参考链接：https://www.jianshu.com/p/a7741619dd58
 * https://www.cxyxiaowu.com/2869.html
 *
 * @author: WangQuanzhou
 * @date: 2020/11/1 15:39
 */
public class LongestPalindromicSubstring {

    private static final Character c = ',';

    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        int length = s.length();
        StringBuilder stringBuilder = new StringBuilder(c);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(s.charAt(i)).append(c);
        }
        String newStr = stringBuilder.toString();

        int newLen = newStr.length();
        int midPos = 1;
        int maxLen = 1;
        for (int i = 2; i < newLen; i++) {
            midPos = i;


        }

    }

    public static void main(String[] args) {

    }
}
