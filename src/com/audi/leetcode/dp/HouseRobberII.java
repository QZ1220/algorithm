package com.audi.leetcode.dp;

import com.audi.bst.Main;

import java.util.logging.Level;

/**
 * https://leetcode.com/problems/house-robber-ii/
 * <p>
 * 这个题其实很类似于https://leetcode.com/problems/house-robber/
 * <p>
 * 唯一的区别是这里的商店是环状的
 * <p>
 * 这题的解法和https://leetcode.com/problems/house-robber/也很类似，只是需要考虑首尾的情况
 * <p>
 * 一开始自己的思路是：
 * 1、使用两个值分别记录首尾的两个元素是否被使用，
 * 2、如果同时被使用那么就分别去掉首个元素求最大值，
 * 3、去掉尾部元素求最大值，二者再求最大值。
 * <p>
 * 网上搜了一下，发现其实第一步可以省去。
 * <p>
 * 因为后续两个天然的就避开了首尾相接的情况
 *
 * @author: WangQuanzhou
 * @date: 2020/5/10 22:42
 */
public class HouseRobberII {
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length < 2) {
            return nums[0];
        }
        if (length < 3) {
            return Math.max(nums[0], nums[1]);
        }
        int head = nums[0];

        nums[0] = 0;
        int first = simpleRob(nums);
        nums[0] = head;
        nums[length - 1] = 0;
        int last = simpleRob(nums);

        return Math.max(first, last);
    }

    private int simpleRob(int[] nums) {
        int length = nums.length;
        if (length < 2) {
            return nums[0];
        }
        if (length < 3) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[length - 1];

    }

    public static void main(String[] args) {
        int[] nums = {};
        HouseRobberII houseRobberII = new HouseRobberII();
        System.out.println(houseRobberII.rob(nums));
    }
}
