package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/count-numbers-with-unique-digits/
 * <p>
 * 给定一个整数n，表示数字的位数。求所有的区间位数内的数字的个数，除开包含重复数字的数
 * <p>
 * Input: n = 2
 * Output: 91
 * Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100, excluding 11,22,33,44,55,66,77,88,99
 *
 * @author : wangquanzhou
 * @date : 2023/8/12 20:05
 */
public class CountNumberswithUniqueDigits {

    public int countNumbersWithUniqueDigits(int n) {
        // dp[i]表示小于等于i位数字，相加的和（除开重复数字）
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 10;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + (dp[i - 1] - dp[i - 2]) * (10 - (i - 1));
        }
        return dp[n];
    }

    public static void main(String[] args) {

    }
}
