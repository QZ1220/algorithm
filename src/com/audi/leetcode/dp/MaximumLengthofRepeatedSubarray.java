package com.audi.leetcode.dp;


/**
 * https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 * <p>
 * 求两个子数组的最长重叠区间的长度
 * <p>
 * Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * Output: 3
 * Explanation: The repeated subarray with maximum length is [3,2,1].
 * <p>
 * Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * Output: 5
 * <p>
 * 使用动态规划求解
 *
 * @author: WangQuanzhou
 * @date: 2021-10-26 9:07 PM
 */
public class MaximumLengthofRepeatedSubarray {

    /**
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int max = 0;
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                // 状态转移方程
                dp[i][j] = nums1[i - 1] == nums2[j - 1] ? dp[i - 1][j - 1] + 1 : 0;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
    }
}
