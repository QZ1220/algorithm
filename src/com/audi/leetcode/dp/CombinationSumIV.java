package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/combination-sum-iv/description/
 * <p>
 * 给定一个nums数组，一个数字target，求使用数组中的数字组成的和为target的可能的排列个数
 * 1、nums数组的元素可以无限次重复使用
 * 2、注意结果求的是排列，不是组合，及需要考虑元素顺序  2，1，1  和  1，1，2是不同的排列
 *
 * @author : wangquanzhou
 * @date : 2023/8/19 12:09
 */
public class CombinationSumIV {
    /**
     * 尝试使用完全背包的思想求解本题
     * <p>
     * 如果结果求的是组合：则先遍历物品，再遍历背包
     * 如果结果求的是排列：则先遍历背包，再遍历物品
     * <p>
     * 如果本题求的不是排列，那么将和coin change ii 那道题解法一模一样
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        // dp[j]表示，和为j的可能的组合的情况个数
        int[] dp = new int[target + 1];
        dp[0] = 1;
        // 先遍历背包
        for (int j = 1; j <= target; j++) {
            // 再遍历物品
            for (int i = 0; i < nums.length; i++) {
                if (j >= nums[i]) {
                    dp[j] += dp[j - nums[i]];
//                    System.out.println("j=" + j + " i=" + i + " dp[j]=" + dp[j]);
                }
            }
        }
//        for (int i : dp) {
//            System.out.print(i + "  ");
//        }
//        System.out.println();
        return dp[target];
    }

    public static void main(String[] args) {
        CombinationSumIV combinationSumIV = new CombinationSumIV();
        int[] nums = {1, 2, 3};
        System.out.println(combinationSumIV.combinationSum4(nums, 4));
    }
}
