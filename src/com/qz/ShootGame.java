package com.qz;

/**
 * 题目，一次射箭，可以射中0-10环中的任意一环，求射击m次，总共射中n环的可能的情况有多少种
 * <p>
 * eg：m=2,n=3  则总计有（0，3）（1，2）（2，1）（3，0）总计4种可能的情况
 *
 * @author : wangquanzhou
 * @date : 2023/8/2 22:38
 */
public class ShootGame {

    /**
     * 这道题可以使用回溯的方法求解  但是时间负责度是11的m次方，虽然可以做一些剪枝操作，但是复杂度依然很高
     *
     * @param m
     * @param n
     * @return
     */
    int count = 0;

    public int shootingWays(int m, int n) {
        if (m * 10 < n || m * 0 > n) {
            return -1;
        }

        dfs(m, n, 0, 0);
        return count;
    }

    private void dfs(int m, int n, int step, int tmpSum) {
        if (tmpSum > n) {
            return;
        }
        if ((m - step) * 10 < (n - tmpSum)) {
            return;
        }
        if (m == step && tmpSum == n) {
            count++;
        }
        for (int i = 0; i < 11; i++) {
            dfs(m, n, step + 1, tmpSum + i);
        }
    }

    /**
     * 这道题还可以使用动态规划来求解
     * <p>
     * dp[i][j]表示射击i次，总计得到j环的可能射击方法总数
     * <p>
     * dp[i][j]=dp[i-1][j-0]+dp[i-1][j-1]+...+dp[i-1][j-10]
     *
     * @param m
     * @param n
     * @return
     */
    public int shootingWays2(int m, int n) {
        if (m * 10 < n || m * 0 > n) {
            return -1;
        }

        int[][] dp = new int[m + 1][n + 1];
        // dp[0][0]表示在不射击的情况下，就可以得到0环，只有一种方式（不射击），因此dp[0][0]=1
        dp[0][0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k < 11; k++) {
                    // j-k >=0  才有继续射击的可能，否则直接停止射击
                    if ((j - k) >= 0) {
                        dp[i][j] += dp[i - 1][j - k];
                    }
                }
            }
        }
        return dp[m][n];
    }


    public static void main(String[] args) {
        ShootGame game = new ShootGame();
        System.out.println(game.shootingWays(2, 3));
        System.out.println(game.shootingWays2(2, 3));
    }

}
