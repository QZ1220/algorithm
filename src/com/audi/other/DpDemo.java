package com.audi.other;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * dp algorithm
 *
 * @author : wangquanzhou
 * @date : 2023/7/15 10:03
 */
public class DpDemo {

    /**
     * find the least coins we used to make up the amount
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        // dp[i]表示组成amount需要的最少的coin的个数
        int[] dp = new int[amount + 1];
        for (int i = 0; i < amount + 1; i++) {
            dp[i] = -1;
        }
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if ((i - coins[j] > 0 && dp[i - coins[j]] != -1)) {
                    if (dp[i] == -1 || dp[i] > dp[i - coins[j]] + 1) {
                        dp[i] = dp[i - coins[j]];
                    }
                }
            }
        }
        return dp[amount];
    }


    /**
     * coin change II
     * <p>
     * Return the number of combinations that make up that amount.
     * If that amount of money cannot be made up by any combination of the coins, return 0.
     *
     * @param amount
     * @param coins
     * @return
     */
//    public int change(int amount, int[] coins) {
//        // dp[i] 表示组成金额i，可能的组合方式的个数
//        int[] dp = new int[amount + 1];
//        Arrays.fill(dp,-1);
//        dp[0]=0;
//
//        for (int i = 1; i <= amount; i++) {
//            for (int j = 0; j < coins.length; j++) {
//                if (i - coins[j] >= 0 && dp[i - coins[j]] != -1) {
//                    dp[i]++;
//                }
//            }
//        }
//        return dp[amount];
//    }
    public int minimumTotal(List<List<Integer>> triangle) {

        // 最大的行数
        int H = triangle.size();
        // 最大的列数
        int L = triangle.get(H - 1).size();

        int[][] dp = new int[H][L];

        // 初始化dp数组的最后一行
        for (int i = 0; i < L; i++) {
            dp[H - 1][i] = triangle.get(H - 1).get(i);
        }

        for (int i = H - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    /**
     * 最长上升子序列
     * <p>
     * 使用动态规划求解，时间复杂度O(N^2)
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int LIS = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            LIS = Math.max(LIS, dp[i]);
        }
        return LIS;
    }


    /**
     * 借助栈的思想
     * <p>
     * 时间复杂度O(NlogN)
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        List<Integer> list = new LinkedList<>();
        list.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > list.get(list.size() - 1)) {
                list.add(nums[i]);
            } else {
                for (int j = 0; j < list.size(); j++) {
                    // 下面的等号不能省去，考虑测试用例：{4, 10, 4, 3, 8, 9}
                    if (nums[i] <= list.get(j)) {
                        list.set(j, nums[i]);
                        break;
                    }
                }
            }
        }
        return list.size();
    }


    public static void main(String[] args) {
        int[] nums = {4, 10, 4, 3, 8, 9};
        DpDemo dpDemo = new DpDemo();
        System.out.println(dpDemo.lengthOfLIS2(nums));
    }
}
