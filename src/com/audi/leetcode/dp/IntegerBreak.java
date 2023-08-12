package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/integer-break/
 * <p>
 * 给定一个正整数m，将正整数拆分为k个数，使得k个数的和等于m
 * <p>
 * 求某种拆分方案，使得k个数的乘积最大，返回最大的乘积
 *
 * @author : wangquanzhou
 * @date : 2023/8/12 12:13
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        // dp[i]表示，将整数i进行拆分，可以得到的最大乘积
        int[] dp = new int[n + 1];
        dp[0] = 0;
        // 1不能进行拆分，因此dp[1]=0
        dp[1] = 0;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                // 递推公式中  Math.max(j * (i - j), j * dp[i - j]) 的理解：
                // 对于数字i，可以拆分成j和(i-j)，二者乘积为(j * (i - j))
                // 然后对于数字i-j，其实可以继续拆分，拆分的最大乘积就是dp[i - j]
                // 因此这里是Math.max(j * (i - j), j * dp[i - j])
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {

    }
}
