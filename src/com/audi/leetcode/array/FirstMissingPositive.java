package com.audi.leetcode.array;

import java.util.BitSet;

/**
 * https://leetcode.com/problems/first-missing-positive/
 * <p>
 * 看到这个题，就觉得该使用bit数组
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
            if (nums[i] < 1) {
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
