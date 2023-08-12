package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/perfect-squares/
 * <p>
 * 给定一个数字n，求最少可以使用多少个完全平方数，使得这几个完全平方数的和等于n
 * <p>
 * 使用动态规划求解
 *
 * @author : wangquanzhou
 * @date : 2023/8/12 19:52
 */
public class PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        // dp[0]初不初始化都行，因为dp数组元素默认就都是0
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, dp[i - j * j]);
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }

    public static void main(String[] args) {

    }
}
