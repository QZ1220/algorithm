package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/matchsticks-to-square/
 * <p>
 * 给定一个整型数组，数组元素表示长度
 * 求是否可以使用这些长度，拼接出一个正方形，要求所有的数组元素都要用上
 * <p>
 * 使用动态规划思想求解，这道题也可以使用搜索的思想求解
 *
 * @author : wangquanzhou
 * @date : 2023/8/13 12:58
 */
public class MatchstickstoSquare {
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) {
            return false;
        }

        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        if (totalSum % 4 != 0) {
            return false;
        }

        int sideLength = totalSum / 4;
        int N = nums.length;
        // dp[i][j]表示前i个火柴是否可以构成长度为j的边
        boolean[][] dp = new boolean[N + 1][sideLength + 1];
        dp[0][0] = true;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= sideLength; j++) {
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][sideLength];
    }

    public static void main(String[] args) {
        int[] nums = {3, 3, 3, 3, 4};
        MatchstickstoSquare matchstickstoSquare = new MatchstickstoSquare();
        System.out.println(matchstickstoSquare.makesquare(nums));

        System.out.println(1<<1);
    }
}
