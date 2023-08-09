package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/maximal-square/
 * <p>
 * 给定一个包含0-1的二维矩阵，求矩阵中1可以围成的最大正方形的面积
 * <p>
 * 使用动态规划求解
 *
 * @author : wangquanzhou
 * @date : 2023/8/8 23:21
 */
public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row + 1][col + 1];
        int maxSide = 0;

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    // dp数组的维度比matrix要大一行、一列
                    // dp[i][j]表示以(i, j)为右下角顶点的最大正方形的边长。
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        return maxSide * maxSide;
    }


    public int maximalSquare2(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        int maxSide = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        // dp数组的维度比matrix要大一行、一列
                        // dp[i][j]表示以(i, j)为右下角顶点的最大正方形的边长。
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        return maxSide * maxSide;
    }
}
