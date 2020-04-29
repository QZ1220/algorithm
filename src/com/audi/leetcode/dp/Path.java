package com.audi.leetcode.dp;

/**
 * @author WangQuanzhou
 * @date 2020-04-29
 */
public class Path {

    public int uniquePaths(int m, int n) {
        if (m < 2) {
            return n;
        }
        if (n < 2) {
            return m;
        }
        // dp表示从左上角走到右下角可能的路线条数
        int[][] dp = new int[m][n];

        //
        dp[0][0] = 0;
        return 0;
    }

    public static void main(String[] args) {

    }
}
