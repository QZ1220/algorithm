package com.audi.other.test;

public class CombinationSumIV {

    // 使用完全背包思想求解，并且此题求的排列数，因此遍历顺序是先背包，后物品
    public int combinationSum4(int[] nums, int target) {
        if (null == nums || nums.length < 1) {
            return 0;
        }
        int dp[] = new int[target + 1];
        // 先背包
        for (int j = 1; j <= target; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j >= nums[i]) {
                    dp[j] += dp[j - nums[i]];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {

    }
}
