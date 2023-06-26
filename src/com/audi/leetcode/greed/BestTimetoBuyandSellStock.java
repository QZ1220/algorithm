package com.audi.leetcode.greed;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/?envType=study-plan&id=level-1
 * <p>
 * 给定一个整形数组，每个元素代表当天的股价，你需要在其中某一天买入，后面的某一天卖出，目的是要获取最大的利润，也是卖出-买入的差价最大
 * <p>
 * 从数组的末尾往前遍历，求每个元素位置，其右边的最大值，最大值与本位置元素的差值，求一个最大值，即可求解。
 *
 * @author : wangquanzhou
 * @date : 2023/3/22 22:14
 */
public class BestTimetoBuyandSellStock {

    public int maxProfit(int[] prices) {
        int len = prices.length;
        // 其实，这里也并不是非要使用数组，使用一个遍历存储即可
        int[] rightMax = new int[len];
        rightMax[len - 1] = prices[len - 1];
        int maxProfit = 0;
        for (int i = len - 2; i >= 0; i--) {
            if (prices[i] > rightMax[i + 1]) {
                rightMax[i] = prices[i];
            } else {
                rightMax[i] = rightMax[i + 1];
            }
            maxProfit = (rightMax[i] - prices[i]) > maxProfit ? (rightMax[i] - prices[i]) : maxProfit;
        }
        return maxProfit;
    }

    /**
     * 这个解法使用了动态规划的思想。我们用一个变量minPrice记录遍历过的最低价格，然后通过不断更新maxProfit来求得最大利润。
     * 遍历数组时，如果当前价格比minPrice小，我们将其更新为minPrice；
     * 如果当前价格减去minPrice大于maxProfit，我们将其更新为maxProfit。最终返回maxProfit即可。
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {

    }
}
