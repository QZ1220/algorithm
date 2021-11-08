package com.audi.leetcode.array;


/**
 * https://leetcode.com/problems/subarrays-with-k-different-integers/
 * <p>
 * 给定一个整型数组，求该数组的符合条件的子数组【区别于子序列】的个数
 * 1、子数组长度大于等于k
 * 2、子数组中不重复元素个数等于k
 *
 * @author: WangQuanzhou
 * @date: 2021/11/8 21:55
 */
public class SubarrayswithKDifferentIntegers {

    public int subarraysWithKDistinct(int[] nums, int k) {
        // 题设限定nums不能为空
        int len = nums.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + k - 1; j < len; j++) {

            }
        }

    }

    public static void main(String[] args) {

    }
}
