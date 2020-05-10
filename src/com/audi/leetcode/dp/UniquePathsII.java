package com.audi.leetcode.dp;


/**
 * https://leetcode.com/problems/unique-paths-ii/
 * <p>
 * <p>
 * Input:
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * Output: 2
 * Explanation:
 * There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 *
 * @author: WangQuanzhou
 * @date: 2020/5/10 21:54
 */
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
// dp表示从左上角走到右下角可能的路线条数
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        // 如果只有一列，那每个节点只能上一个节点直走，因此都是1
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
                break;
            }
        }
        // 如果只有一行，那每个节点只能上一个节点直走，因此都是1
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
            if (obstacleGrid[0][i] == 1) {
                dp[0][i] = 0;
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 递推公式：每个节点只能从左上或者前面节点走过来
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

}
