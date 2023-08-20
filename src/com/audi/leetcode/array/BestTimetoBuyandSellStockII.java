package com.audi.leetcode.array;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 本题本质上和https://leetcode.com/problems/best-time-to-buy-and-sell-stock/是一样的，只是之前是只允许买卖股票一次，求最大利润
 * 现在是可以买卖多次，然后求最大利润和
 *
 * @author : wangquanzhou
 * @date : 2023/6/25 16:27
 */
public class BestTimetoBuyandSellStockII {
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        int[] rightMax = new int[prices.length];
        int maxProfit = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < prices.length && (i + 1) < prices.length; i++) {
            if (prices[i + 1] < prices[i]) {
                right = i;
                maxProfit = maxProfit + maxProfitInSubArr(left, right, prices, rightMax);
                left = right + 1;
                continue;
            }
            right = i + 1;
        }
        if (left == 0 && right == prices.length - 1) {
            maxProfit = maxProfitInSubArr(left, right, prices, rightMax);
        }
        return maxProfit;
    }

    private int maxProfitInSubArr(int left, int right, int[] prices, int[] rightMax) {
        if (left == right) {
            return 0;
        }
        int maxProfit = 0;
        rightMax[right] = prices[right];
        for (int i = right - 1; i >= left; i--) {
            rightMax[i] = prices[i] < rightMax[i + 1] ? rightMax[i + 1] : prices[i];
            maxProfit = maxProfit < (rightMax[i] - prices[i]) ? (rightMax[i] - prices[i]) : maxProfit;
        }
        return maxProfit;
    }


    /**
     * 更为简洁的一个算法
     * <p>
     * 即：每次只要有盈利（第二天的价格比前一天高，就累加利润）
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    public static void main(String[] args) {
//        int[] prices = {1, 2, 3, 4, 5};
//        int[] prices = {7, 1, 5, 3, 6, 4};
//        int[] prices = {7,6,4,3,1};
        int[] prices = {6, 1, 3, 2, 4, 7};
        BestTimetoBuyandSellStockII sellStockII = new BestTimetoBuyandSellStockII();
        System.out.println(sellStockII.maxProfit(prices));
    }
}
