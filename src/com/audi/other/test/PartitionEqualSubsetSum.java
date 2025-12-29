package com.audi.other.test;

import java.util.Arrays;

/**
 * 给定一个整数数组，求这个数组能不能划分为两个两个子集，并且两个子集的和相等
 *
 * @author : wangquanzhou
 * @date : 2025/12/29 19:58
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        if (null == nums || nums.length < 2) {
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        int mid = sum / 2;
        if (mid * 2 != sum) {
            return false;
        }
        // 定义bp数组，明确含义，dp[j]表示容量为j的背包，所装物品的最大价值
        // 本题背包是nums数组，物品是nums数组元素的值
        int dp[] = new int[mid + 1];
        // 初始化
        dp[0] = 0;
        // 先物品，后背包
        for (int i = 0; i < nums.length; i++) {
            for (int j = mid; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        return dp[mid] == mid;
    }

    public static void main(String[] args) {

    }
}
