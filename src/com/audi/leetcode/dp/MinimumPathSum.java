package com.audi.leetcode.dp;


import sun.security.util.Length;

import java.util.logging.Level;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 * <p>
 * 这道题可以参考一下https://leetcode.com/problems/unique-paths/   的思路
 *
 * @author: WangQuanzhou
 * @date: 2020/5/16 22:25
 */
public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        // 行数
        int height = grid.length;
        // 列数
        int width = grid[0].length;
        int[][] dp = new int[height][width];

        dp[0][0] = grid[0][0];

        // 第一行的元素，只能水平向右累加
        for (int i = 1; i < width; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        // 第一列的元素，只能水平向下累加
        for (int i = 1; i < height; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[height - 1][width - 1];

    }

    public static void main(String[] args) {
        int[][] grid = {{1, 2, 5},
                {3, 2, 1}};
        MinimumPathSum minimumPathSum = new MinimumPathSum();
        System.out.println(minimumPathSum.minPathSum(grid));
    }
}
