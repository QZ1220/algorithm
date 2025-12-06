package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
 * <p>
 * 本题对买卖的次数做了限制  要求最多只能买卖2次股票，其余要求与之前一致，求最大利润。
 * <p>
 * 参考视频：https://www.bilibili.com/video/BV1D24y1Q7Ls?spm_id_from=333.788.videopod.sections&vd_source=d1530fb814268f770330143e24aaf1e6
 *
 * <p>
 * 采用动态规划的思路求解
 * <p>
 * 建立二维dp数组
 * dp[i][0]表示不做任何操作（其实这个状态可以省略，为了方便理解，所以还是列出来）
 * dp[i][1]表示第一次持有的最大利润
 * dp[i][2]表示第一次不持有的最大利润
 * dp[i][3]表示第二次持有的最大利润
 * dp[i][4]表示第二次不持有的最大利润
 *
 * @author : wangquanzhou
 * @date : 2025/12/6 11:13
 */
public class BestTimetoBuyandSellStockIII {
    public int maxProfit(int[] prices) {
        if (null == prices || prices.length < 2) {
            return 0;
        }
        int[][] dp = new int[prices.length][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;
        for (int i = 1; i < prices.length; i++) {
            // dp[i][0]表示不操作，所以值直接等于前一天不操作的值
            dp[i][0] = dp[i-1][0];
            // dp[i][1]可以由前一天持有，以及前一天不操作且第i天买入，求最大值得到
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            // dp[i][2]可以由前一天不持有，以及前一天持有且第i天卖出，求最大值得到
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            // dp[i][3]可以由前一天持有，以及前一天不持有且第i天买入，求最大值得到
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            // dp[i][4]可以由前一天不持有，以及前一天持有且第i天卖出，求最大值得到
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        // 最终最大利润只可能是在第一次不持有，以及第二次不持有之间产生一个最大的值
        return Math.max(dp[prices.length - 1][2], dp[prices.length - 1][4]);
    }

    public static void main(String[] args) {

    }
}
