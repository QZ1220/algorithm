package com.audi.leetcode.dp;


import java.util.Arrays;

/**
 * https://leetcode.com/problems/coin-change/
 * <p>
 * 组成指定面值所使用的的钞票张数
 * <p>
 * 注意这道题不可以使用贪心算法，因为coins的值是未知的，既然未知就满足贪心思想的一个必备条件：当前金额是比它小的任意金额的倍数，
 * 比如{1,5,10,20,100}，100是20、10、5、1的整倍数
 * <p>
 * 比如coins[]={1,2,5,7,10}, amount=14
 * <p>
 * 如果使用贪心算法求解出来的结果将是3 （10+2+2），实际上使用2张（7+7）才是最优解
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
