package com.audi.leetcode.array;


import java.util.BitSet;

/**
 * https://leetcode.com/problems/missing-number/
 * <p>
 * 一个数组，其中有一个数字缺失，其余数字都在，找出这个确实的数字，数字范围0-n
 * <p>
 * 使用bit数组的思想
 * <p>
 * 看了下网上的题解，也可以使用等差数列的性质先求0-n的和，然后减去nums的所有元素和，差值就是缺失的元素
 *
 * @author: WangQuanzhou
 * @date: 2021-08-21 7:10 PM
 */
public class MissingNumber {

    public int missingNumber(int[] nums) {
        int len = nums.length;
        BitSet bitSet = new BitSet(len);
        for (int num : nums) {
            bitSet.set(num);
        }
        return bitSet.nextClearBit(0);
    }

    public static void main(String[] args) {
    }
}
