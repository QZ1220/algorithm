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


    /**
     * 这道题还可以使用动态规划来求解
     * <p>
     * 类比于01背包问题（实际上应该是多重背包，但是可以使用for循环拆解为01背包）
     * <p>
     * 使用一维dp递推公式求解，因为本题求解的是方案的总的可能的情况数，因此递推公式为：
     * <p>
     * dp[j]+=dp[j-nums[i]];
     * <p>
     * 下面的代码，求出的结果貌似与二维dp求出的结果不一致。。。
     *
     * @param m
     * @param n
     * @return
     */
    public int shootingWays2222(int m, int n) {
        if (m * 10 < n || m * 0 > n) {
            return -1;
        }

        // dp[j]表示在射击总环数得到j环时，可能的射击方案的总数
        int[] dp = new int[n + 1];
        // dp[0]表示射击0环，方案数为1，即不射击
        dp[0] = 1;
        for (int i = 1; i <= m; i++) {
            // 倒序遍历背包
            for (int j = n; j >= 0; j--) {
                for (int k = 0; k < 11; k++) {
                    // j-k >=0  才有继续射击的可能，否则直接停止射击
                    if ((j - k) >= 0) {
                        dp[j] += dp[j - k];
                    }
                }
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {
        ShootGame game = new ShootGame();
        int m = 3;
        int n = 5;
        System.out.println(game.shootingWays(m, n));
        System.out.println(game.shootingWays2(m, n));
        System.out.println(game.shootingWays2222(m, n));
    }

}
