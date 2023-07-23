package com.audi.leetcode.array;

/**
 * https://leetcode.com/problems/running-sum-of-1d-array/
 * <p>
 * 起始就是求数组的前缀和
 *
 * @author : wangquanzhou
 * @date : 2023/7/23 11:28
 */
public class RunningSumof1dArray {

    public int[] runningSum(int[] nums) {
        if (null == nums || nums.length < 2) {
            return nums;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            nums[i] = sum;
        }
        return nums;
    }

    public static void main(String[] args) {

    }
}
