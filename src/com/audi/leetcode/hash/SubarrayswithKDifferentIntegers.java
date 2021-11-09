package com.audi.leetcode.hash;


import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/subarrays-with-k-different-integers/
 * <p>
 * 给定一个整型数组，求该数组的符合条件的子数组【区别于子序列】的个数
 * 1、子数组长度大于等于k
 * 2、子数组中不重复元素个数等于k
 * <p>
 * 利用滑动窗口的思想求解
 *
 * @author: WangQuanzhou
 * @date: 2021/11/8 21:55
 */
public class SubarrayswithKDifferentIntegers {

    public int subarraysWithKDistinct(int[] nums, int k) {
        // 题设限定nums不能为空
        int len = nums.length;
        int count = 0;
        int initK = k;
        for (int i = 0; i < len; i++) {
            for (int j = i + k - 1; j < len; j++) {
                Set<Integer> set = new HashSet<>();
                for (int m = i; m <= j; m++) {
                    set.add(nums[m]);
                }
                if (set.size() == initK) {
                    System.out.println("set = " + set);
                    count++;
                }
            }
            k++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 3};
        int k = 2;
        SubarrayswithKDifferentIntegers differentIntegers = new SubarrayswithKDifferentIntegers();
        System.out.println(differentIntegers.subarraysWithKDistinct(nums, k));
    }
}
