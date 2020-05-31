package com.audi.leetcode.dp;


import java.util.Arrays;

/**
 * https://leetcode.com/problems/coin-change/
 * <p>
 * 组成指定面值所使用的的钞票张数
 *
 * @author: WangQuanzhou
 * @date: 2020/5/16 21:53
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        if (null == coins || coins.length == 0) {
            return -1;
        }
        int length = coins.length;
        // dp[i]代表组成金额i时的最优解
        int[] dp = new int[amount + 1];
        // 初始设置所有金额都不可达
        Arrays.fill(dp, -1);

        // 为了地推方便，设置金额0可以使用0个硬币组成
        dp[0] = 0;

        // 出事金额从1开始，因为严格意义上来说没有0元这个说法
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < length; j++) {
                if (i - coins[j] >= 0 && dp[i - coins[j]] != -1) {
                    // 下面的dp[i] > dp[i - coins[j]] + 1体现出了求最小的dp[i]的思想
                    if (dp[i] == -1 || dp[i] > dp[i - coins[j]] + 1) {
                        // 递推公式
                        dp[i] = dp[i - coins[j]] + 1;
                    }
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChange(coins, 11));
    }
}
