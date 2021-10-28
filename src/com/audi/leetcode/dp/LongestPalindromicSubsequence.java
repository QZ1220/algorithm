package com.audi.leetcode.dp;


/**
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 * <p>
 * 求给定字符串的最长回文串
 * <p>
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb".
 *
 * @author: WangQuanzhou
 * @date: 2021-10-28 10:40 AM
 */
public class LongestPalindromicSubsequence {

    /**
     * 借助LCS的解法，使用动态规划，将s反序，得到s1，求s和s1的LCS，结果就是本题求解的结果
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = len - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        String s1 = sb.toString();
        int[][] dp = new int[len + 1][len + 1];
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= len; j++) {
                if (s.charAt(i - 1) == s1.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[len][len];
    }

    public static void main(String[] args) {
    }
}
