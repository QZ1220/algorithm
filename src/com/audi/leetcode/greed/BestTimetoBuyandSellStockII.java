package com.audi.leetcode.greed;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 使用贪心算法求解
 *
 * @author : wangquanzhou
 * @date : 2023/6/27 07:30
 */
public class BestTimetoBuyandSellStockII {

    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }


}
