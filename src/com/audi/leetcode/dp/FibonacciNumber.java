package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/fibonacci-number/description/?envType=study-plan&id=level-1
 * <p>
 * 直接使用递推公式求解
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 *
 * @author : wangquanzhou
 * @date : 2023/3/27 19:55
 */
public class FibonacciNumber {

    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        FibonacciNumber fibonacciNumber = new FibonacciNumber();
        System.out.println(fibonacciNumber.fib(3));
    }
}
