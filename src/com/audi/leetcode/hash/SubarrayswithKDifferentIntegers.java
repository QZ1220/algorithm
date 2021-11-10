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
 * <p>
 * Input: nums = [1,2,1,2,3], k = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 * 看上面给出的Explanation其实也是按照滑动窗口的思想来写的答案
 *
 * @author: WangQuanzhou
 * @date: 2021/11/8 21:55
 */
public class SubarrayswithKDifferentIntegers {

    /**
     * 下面这种滑窗写法正确性是没有问题，只是会TLE
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
        // 题设限定nums不能为空
        int len = nums.length;
        int count = 0;
        int initK = k;
        // 开始滑窗  滑窗的窗口在每一轮循环完成以后需要+1，判断滑窗内的不同元素的个数是否等于原始的输入k
        while (k <= len) {
            int left = 0, right = k - 1;
            while (right < len) {
                // 统计  left-right区间内不同元素的个数
                Set<Integer> set = new HashSet<>(k);
                for (int i = left; i <= right; i++) {
                    if (set.size() > initK) {
                        break;
                    }
                    set.add(nums[i]);
                }
                if (set.size() == initK) {
                    count++;
                }
                left++;
                right++;
            }
            k++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 4};
        int k = 4;
        SubarrayswithKDifferentIntegers differentIntegers = new SubarrayswithKDifferentIntegers();
        System.out.println(differentIntegers.subarraysWithKDistinct(nums, k));
    }
}
