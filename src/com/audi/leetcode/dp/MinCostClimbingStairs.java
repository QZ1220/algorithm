package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/min-cost-climbing-stairs/description/?envType=study-plan&id=level-1
 * <p>
 * 使用动态规划求解，寻找递推公式
 * 假设第i步的最小花费为dp[i]，那么他此时有两个选择，走一步或者走两步，选择花费较小的即可
 * dp[i]=min(dp[i-2]+cost[i-2],dp[i-1]+cost[i-1])
 *
 * @author : wangquanzhou
 * @date : 2023/3/28 19:52
 */
public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        // a表是到达i-2步的最小cost，b表示到达i-1步的最小cost，c表示达到第i步的最小cost
        int a = 0, b = 0, c = 0;
        for (int i = 2; i <= cost.length; i++) {
            c = Math.min(a + cost[i - 2], b + cost[i - 1]);
            a = b;
            b = c;
        }
        return c;
    }

    public static void main(String[] args) {

    }
}
