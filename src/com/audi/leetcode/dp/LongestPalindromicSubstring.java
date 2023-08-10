package com.audi.leetcode.dp;


/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * <p>
 * 求一个字符串的最长回文子串
 * <p>
 * 可以使用动态规划求解
 * dp[i][j]表示字符串s的[i,j]区间是否是回文串
 * <p>
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 *
 * @author: WangQuanzhou
 * @date: 2020/11/1 15:39
 */
public class LongestPalindromicSubstring {


    /**
     * 动态规划法
     *
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if (null == s || s.length() < 2) {
            return s;
        }

        int len = s.length();
        boolean[][] dp = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] chars = s.toCharArray();
        int start = 0, max = 1;
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
//                if (s.charAt(i) != s.charAt(j)) {
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && j - i + 1 > max) {
                    max = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + max);
    }


    public String longestPalindrome(String s) {
        if (null == s || s.length() < 2) {
            return s;
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];

        int start = 0, max = 1;
        // 这样子写for循环会有问题，相当于是固定左端点，右端点不断右移
        // 实际上，应该是固定右端点，左端点右移
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    dp[i][j] = true;
                } else if (s.charAt(i) == s.charAt(j)) {
                    if (i + 1 == j || i + 2 == j) {
                        dp[i][j] = true;
                    } else {
                        // 这里会依赖前面的数据，因此只能使用「固定右端点，左端点右移」的循环方式
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && j - i + 1 > max) {
                    max = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + max);
    }

    public static void main(String[] args) {
//        String s = "ac";
//        String s = "babad";
//        String s = "cbbd";
//        String s = "a";
        String s = "aaaa";
        LongestPalindromicSubstring palindromicSubstring = new LongestPalindromicSubstring();
        System.out.println(palindromicSubstring.longestPalindrome(s));
    }
}
