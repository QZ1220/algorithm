package com.audi.leetcode.dp;


import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/
 * <p>
 * https://www.cnblogs.com/grandyang/p/4938187.html
 *
 * @author: WangQuanzhou
 * @date: 2020/5/16 22:20
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int length = nums.length;
        if (nums == null || length == 0) {
            return 0;
        }

        // dp数组表示以第i个元素结尾的最长递增子序列的长度
        int[] dp = new int[length];
        // 注意dp数组初始化全都是1
        Arrays.fill(dp, 1);

        for (int i = 0; i < length; i++) {
            // 循环找最长递增子序列
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // 返回足底啊子串长度
        int max = dp[0];
        for (int i = 1; i < length; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        System.out.println(longestIncreasingSubsequence.lengthOfLIS(nums));
    }
}
