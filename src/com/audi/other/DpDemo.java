package com.audi.other;

/**
 * dp algorithm
 *
 * @author : wangquanzhou
 * @date : 2023/7/15 10:03
 */
public class DpDemo {

    /**
     * find the least coins we used to make up the amount
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 0; i < amount + 1; i++) {
            dp[i] = -1;
        }
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if ((i - coins[j] > 0 && dp[i - coins[j]] != -1)) {
                    if (dp[i] == -1 || dp[i] > dp[i - coins[j]] + 1) {
                        dp[i] = dp[i - coins[j]];
                    }
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {

    }
}
