package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/maximum-subarray/
 * <p>
 * 求一个整型数组的和最大的子数组
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * <p>
 * 使用dp求解
 * 递推公式：dp[i]=max(dp[i-1]+nums[i],nums[i])，但是这里我们不需要维护dp数组，只需要维护一个dp变量即可
 * <p>
 *
 * @author WangQuanzhou
 * @date 2020-04-23
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        if (null == nums) {
            return 0;
        }
        // 表示所有子串的最大值
        int sum = nums[0];
        // dp表示单个子串的最大值
        int dp = nums[0];
        // 注意下面从i=1开始循环
        for (int i = 1; i < nums.length; i++) {
            if (dp < 0) {
                dp = nums[i];
            } else {
                dp = nums[i] + dp;
            }
            if (dp > sum) {
                sum = dp;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
//        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums = {1};
        MaximumSubarray maximumSubarray = new MaximumSubarray();
        System.out.println(maximumSubarray.maxSubArray(nums));
    }
}
