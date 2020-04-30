package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/unique-paths/
 *
 * @author WangQuanzhou
 * @date 2020-04-29
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        // dp表示从左上角走到右下角可能的路线条数
        int[][] dp = new int[m][n];

        // 如果只有一列，那每个节点只能上一个节点直走，因此都是1
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        // 如果只有一行，那每个节点只能上一个节点直走，因此都是1
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 递推公式：每个节点只能从左上或者前面节点走过来
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths(3, 2));
        System.out.println(uniquePaths.uniquePaths(7, 3));
        System.out.println(uniquePaths.uniquePaths(1, 1));
    }
}
