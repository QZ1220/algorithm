package com.audi.other.test;

import java.util.Arrays;

/**
 * 给定一个整数数组，可以在数组元素前添加+or-，使得最终加减运算的结果为target。
 * <p>
 * 求一共有多少种放置加减号的方法
 * <p>
 * 采用0-1背包问题求解思路求解
 *
 * @author : wangquanzhou
 * @date : 2025/12/29 20:20
 */
public class TargetSum {

    public int findTargetSumWays(int[] nums, int target) {
        if (null == nums || nums.length < 1) {
            return 0;
        }
        int sum = Arrays.stream(nums).sum();
        int left = (target + sum) / 2;
        if (left * 2 != (target + sum)) {
            return 0;
        }
        // 确定dp数组含义，dp[j]表示容量为j的背包，总共有dp[j]种放置符号的方法，满足题意
        int dp[] = new int[left + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = left; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[left];
    }

    public static void main(String[] args) {

    }
}

