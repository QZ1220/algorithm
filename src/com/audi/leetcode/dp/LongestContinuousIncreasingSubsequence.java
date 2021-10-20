package com.audi.leetcode.dp;


/**
 * https://leetcode.com/problems/longest-continuous-increasing-subsequence/
 * <p>
 * 题目给出了一个整型数组，求数组中的最长且连续的上升子序列
 * <p>
 * Input: nums = [1,3,5,4,7]
 * Output: 3
 * Explanation: The longest continuous increasing subsequence is [1,3,5] with length 3.
 * Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element
 * 4.
 * <p>
 * <p>
 * Input: nums = [2,2,2,2,2]
 * Output: 1
 * Explanation: The longest continuous increasing subsequence is [2] with length 1. Note that it must be strictly
 * increasing.
 *
 * @author: WangQuanzhou
 * @date: 2021-10-20 8:27 AM
 */
public class LongestContinuousIncreasingSubsequence {

    public int findLengthOfLCIS(int[] nums) {
        int max = 1;
        int tempMax = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                tempMax++;
            } else {
                max = max > tempMax ? max : tempMax;
                tempMax = 1;
            }
        }
        return max > tempMax ? max : tempMax;
    }

    public static void main(String[] args) {
//        int[] nums = {2, 2, 2, 2, 2};
//        int[] nums = {1, 3, 5, 4, 7};
        int[] nums = {1, 3, 5, 4, 7, 100, 111, 222, 223, 333, 444};
        LongestContinuousIncreasingSubsequence subsequence = new LongestContinuousIncreasingSubsequence();
        System.out.println(subsequence.findLengthOfLCIS(nums));
    }
}
