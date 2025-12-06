package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 * <p>
 * 采用动态规划的思路求解
 * <p>
 * 建立二维dp数组
 * dp[i][0]表示当前持有股票的最大收益
 * dp[i][1]表示当前不持有股票的最大收益
 *
 * @author : wangquanzhou
 * @date : 2025/12/6 11:13
 */
public class BestTimetoBuyandSellStock {
    public int maxProfit(int[] prices) {
        if (null == prices || prices.length < 2) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            // dp[i][0]可以由前一天已经持有，以及第i天买入股票  求最大值得到
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            // dp[i][1]可以由前一天不持有，以及前一天持有且第i天卖出，求最大值得到
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][1]);
    }

    public static void main(String[] args) {

    }
}
