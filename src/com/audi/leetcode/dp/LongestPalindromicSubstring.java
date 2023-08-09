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
     * 中心扩散法  借助辅助字符
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
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
                    dp[i][j]=false;
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

    public static void main(String[] args) {
//        String s = "ac";
//        String s = "babad";
//        String s = "cbbd";
        String s = "a";
        LongestPalindromicSubstring palindromicSubstring = new LongestPalindromicSubstring();
        System.out.println(palindromicSubstring.longestPalindrome(s));
    }
}
