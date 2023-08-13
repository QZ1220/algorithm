package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 * <p>
 * 求一个数组，是否可以将所有元素拆分成两个子集，使得二者的和相等
 * <p>
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * <p>
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 * @author : wangquanzhou
 * @date : 2023/8/13 12:32
 */
public class PartitionEqualSubsetSum {

    /**
     * 使用动态规划思想求解
     * <p>
     * 本题可以使用0-1背包思想求解
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {

        // 长度小于2的数组，不可能被分成两半
        if (null == nums || nums.length < 2) {
            return false;
        }

        int sum = 0;
        int maxNum = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }

        // 数组和不是偶数，也不可能被分成两半，使得二者和相等
        if (0 != sum % 2) {
            return false;
        }

        int mid = sum / 2;
        // 如果数组存在一个元素，大于中间值，那么也不可能满足题意
        if (mid < maxNum) {
            return false;
        }
        int length = nums.length;

        // dp[i][j]表示以元素i结尾，和为j
        int[][] dp = new int[length + 1][mid + 1];

        for (int i = 1; i <= length; i++) {
            int w = nums[i - 1];
            for (int j = 1; j <= mid; j++) {
                if (j >= w) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + w);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[length][mid] == mid;

    }

    public boolean canPartition2(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // 如果总和为奇数，则无法分割成两个等和子集
        if (totalSum % 2 != 0) {
            return false;
        }

        int targetSum = totalSum / 2;
        int n = nums.length;
        // dp[i][j]来记录前i个数字是否可以组成和为j的子集
        boolean[][] dp = new boolean[n + 1][targetSum + 1];

        // 初始化边界条件
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // 动态规划过程
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= targetSum; j++) {
                // 如果当前数字超过目标值，则无法选取该数字
                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 考虑选取或不选取当前数字
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[n][targetSum];
    }

}
