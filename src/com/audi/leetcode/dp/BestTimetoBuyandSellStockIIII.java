package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
 * <p>
 * 本题对买卖的次数做了限制  要求最多只能买卖K次股票，其余要求与之前一致，求最大利润。
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
 * 本题其实可以是买卖股票三的一个扩展，这里不再穷举dp数组，而是使用循环代替，如下面的第41行代码
 *
 * @author : wangquanzhou
 * @date : 2025/12/6 11:13
 */
public class BestTimetoBuyandSellStockIIII {
    public int maxProfit(int k, int[] prices) {
        if (null == prices || prices.length < 2) {
            return 0;
        }
        int[][] dp = new int[prices.length][2*k + 1];
        for (int i = 0; i <= 2*k; i++) {
            if (i % 2 == 1) {
                dp[0][i] = -prices[0];
            } else {
                dp[0][i] = 0;
            }
        }
        for (int i = 1; i < prices.length; i++) {
            // dp[i][0]表示不操作，所以值直接等于前一天不操作的值
            dp[i][0] = dp[i - 1][0];
            for (int j = 1; j <= 2*k; j++) {
                if (j % 2 == 1) {
                    // 进行买入操作
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]);
                } else {
                    // 进行卖出操作
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + prices[i]);
                }
            }
//            // dp[i][1]可以由前一天持有，以及前一天不操作且第i天买入，求最大值得到
//            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
//            // dp[i][2]可以由前一天不持有，以及前一天持有且第i天卖出，求最大值得到
//            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
//            // dp[i][3]可以由前一天持有，以及前一天不持有且第i天买入，求最大值得到
//            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
//            // dp[i][4]可以由前一天不持有，以及前一天持有且第i天卖出，求最大值得到
//            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return dp[prices.length - 1][2*k];
    }

    public static void main(String[] args) {

    }
}
