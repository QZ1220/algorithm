package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 * <p>
 * 给定一个数组，求其的一个子数组，满足子数组内各元素的乘积最大，返回最大积
 *
 * @author : wangquanzhou
 * @date : 2023/7/17 21:34
 */
public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        if (null == nums) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int maxRes = nums[0];
        int maxP = nums[0];
        int minP = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                maxP = Math.max(nums[i], nums[i] * maxP);
                minP = Math.min(nums[i], nums[i] * minP);
            } else if (nums[i] < 0) {
                int tmp = maxP;
                maxP = Math.max(nums[i], nums[i] * minP);
                minP = Math.min(nums[i], nums[i] * tmp);
            } else {
                maxP = 0;
                minP = 0;
            }
            maxRes = Math.max(maxP, maxRes);
        }
        return maxRes;
    }

    public static void main(String[] args) {
        int[] nums = {-2, -3, -2, -4};
        MaximumProductSubarray maximumProductSubarray = new MaximumProductSubarray();
        System.out.println(maximumProductSubarray.maxProduct(nums));
    }
}
