package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/target-sum/description/
 * <p>
 * 给定一个数组，以及一个target，在数组的每一项之前拼接+或者-，使得算式运算的结果等于target
 * <p>
 * 问按照上述组合方式，总共有多少种放置+-号的方式，使得结果等于target
 * <p>
 * 可以使用0-1背包的思想求解
 * <p>
 * 假设数组的元素最终可以拆分为整数部分和left、负数部分和right，使得left-right=target
 * 又有left+right=sum
 * <p>
 * 可以推出：left=（sum+target）/2
 * <p>
 * 递推公式：dp[j]+=dp[j-nums[i]]
 *
 * @author : wangquanzhou
 * @date : 2025/12/4 15:59
 */
public class TargetSum {

    public int findTargetSumWays(int[] nums, int target) {
        if (null == nums || nums.length < 1) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int left = (sum + target) / 2;
        // 确保背包的容量是正的
        if (left < 0) {
            return 0;
        }
        // 满足下面的if，说明任意添加正负号的方式，都不能满足和为target
        if (left * 2 != (sum + target)) {
            return 0;
        }
        // 定义dp数组，dp[j]表示满足和为j的总共的组合个数
        int dp[] = new int[left + 1];
        // 初始化，表示target=0时，有一种放置方式
        dp[0] = 1;
        // 先物品
        for (int i = 0; i < nums.length; i++) {
            // 再背包，背包采用逆序遍历，保证一个元素只使用一次
            for (int j = left; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[left];
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        TargetSum targetSum = new TargetSum();
        System.out.println(targetSum.findTargetSumWays(nums, 3));
    }
}
