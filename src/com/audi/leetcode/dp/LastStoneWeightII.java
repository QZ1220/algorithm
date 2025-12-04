package com.audi.leetcode.dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/last-stone-weight-ii/
 * <p>
 * 给定nums数组，各元素表示石头的重量，现在要将石头分成两堆，要求两堆石头的重量差尽可能的小
 * <p>
 * 使用01背包的思想求解，一维dp
 *
 * @author : wangquanzhou
 * @date : 2023/8/16 21:41
 */
public class LastStoneWeightII {

    /**
     * 使用dp思想求解
     * 石头的重量和体积可以认为是一样的
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        // 题设保证了数组的长度>=1
        if (stones.length < 2) {
            return stones[0];
        }

        int sum = Arrays.stream(stones).sum();
        int target = sum / 2;

        // 背包问题：dp[j]表示装满容量为j的背包的最大价值
        // 本题问题：dp[j]表示总体积为j的数组的最大重量  dp[0]=0
        int[] dp = new int[target + 1];
        // 先物品
        for (int i = 0; i < stones.length; i++) {
            // 后背包，且背包只能采用倒序遍历
            for (int j = target; j >= stones[i]; j--) {
                // 第一个stones[i]是01背包中体积的概念
                // 第二个stones[i]是01背包中重量的概念
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - 2 * dp[target];
    }

    public static void main(String[] args) {

    }
}
