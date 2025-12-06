package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/house-robber/description/
 * <p>
 * 给定一个数组，每个数组元素表示当前位置的金币数量，现在需要去偷各个位置的金币，希望最后偷到的金币数量最大，相邻位置的金币不能同时偷
 * <p>
 * 借助动态规划思路求解
 *
 * @author : wangquanzhou
 * @date : 2025/12/6 10:20
 */
public class HouseRobber {

    public int rob(int[] nums) {
        if (null == nums || nums.length < 1) {
            return 0;
        }
        if (nums.length==1){
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {

    }
}
