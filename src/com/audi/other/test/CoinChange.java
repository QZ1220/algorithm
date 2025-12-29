package com.audi.other.test;

import java.util.Arrays;

/**
 * 硬币兑换，凑成金额amount需要的最少的硬币个数，凑不成则返回-1
 *
 * @author : wangquanzhou
 * @date : 2025/12/29 20:36
 */
public class CoinChange {

    // 使用完全背包思想求解，因为硬币的数量不限
    public int coinChange(int[] coins, int amount) {
        if (null == coins || coins.length < 1) {
            return -1;
        }
        int dp[] = new int[amount];
        // 初始都不可达
        Arrays.fill(dp, amount + 1);
        // 初始化，金额0可以由0个硬币组成
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {

    }
}
