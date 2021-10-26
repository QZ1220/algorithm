package com.audi.leetcode.dp;


/**
 * https://leetcode.com/problems/longest-common-subsequence/
 * <p>
 * 求两个字符串的最长公共子序列，这里并没有要求子序列是连续的
 * <p>
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * <p>
 * https://www.ixigua.com/6977914039502373383?logTag=0501029da05c84292aae
 * <p>
 * https://www.cnblogs.com/grandyang/p/14230663.html
 * <p>
 * 借助动态规划的思想来解题
 *
 * @author: WangQuanzhou
 * @date: 2021-10-20 8:17 AM
 */
public class LongestCommonSubsequence {

    /**
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
    }
}
