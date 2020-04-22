package com.audi.leetcode.array;

import java.util.BitSet;

/**
 * https://leetcode.com/problems/first-missing-positive/
 * <p>
 * 看到这个题，就觉得该使用bit数组
 * <p>
 * 这道题有个隐含的条件，我们要使用使用上，否则会超出内存限制
 * 这个条件就是：假设nums数组长度为n，且找的是数组中缺失的第一个正整数
 * 若假设nums元素全为正，且不重复，nums中的最大元素也不应大于nums.length，否则就会缺失数字
 *
 * @author WangQuanzhou
 * @date 2020-04-21
 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        if (null == nums) {
            return 0;
        }
        BitSet bitSet = new BitSet();
        for (int i = 0; i < nums.length; i++) {
            // 要注意下面这个限制条件
            if (nums[i] < 1 || nums[i] > nums.length) {
                continue;
            }
            bitSet.set(nums[i]);
        }
        return bitSet.nextClearBit(1);
    }

    public static void main(String[] args) {
        int[] nums = {2147483647};
        FirstMissingPositive firstMissingPositive = new FirstMissingPositive();
        System.out.println(firstMissingPositive.firstMissingPositive(nums));
    }

}
