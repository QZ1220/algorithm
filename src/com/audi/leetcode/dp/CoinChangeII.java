package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/coin-change-ii/
 * <p>
 * 给定一个coins数组，其中每个元素都可以重复使用无限次，求是否可以使用这些数组元素组成和为target的情况
 * 返回可能的情况个数
 *
 * @author : wangquanzhou
 * @date : 2023/8/19 11:52
 */
public class CoinChangeII {

    /**
     * 借助完全背包的思想求解
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        // dp[j]表示，凑成金额j，总的可能的组合数
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                // dp[j]等于所有的dp[j - coins[i]]相加
                dp[j] += dp[j - coins[i]];
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 5};
        CoinChangeII coinChangeII = new CoinChangeII();
        System.out.println(coinChangeII.change(5, nums));
    }
}
