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
     * <p>
     * 本题：target相当于背包容量，coins数组相当于物品
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        // dp[j]表示，凑成金额j，总的可能的组合数
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        // 注意本题求的是组合，不是排列，也就是不关心硬币选取的先后顺序，只要能够凑成target就行
        // 如果要关心硬币的先后顺序，那么两层for循环的位置就要颠倒
        // 本题和https://leetcode.com/problems/combination-sum-iv/description/形成鲜明对比，那道题就是要求解排列
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
