package com.audi.leetcode.array;


import java.util.Arrays;

/**
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 * <p>
 * 给定一个非递减排列的数组，求数组元素的平方，得到的数组也需要有序，也就是如果是负数的平方的话，结果数组需要进行重排序
 * <p>
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 *
 * @author: WangQuanzhou
 * @date: 2021-10-19 8:28 AM
 */
public class SquaresofaSortedArray {

    /**
     * 对原数组进行转换，每个元素等于当前元素的绝对值
     * <p>
     * 然后排序，再计算平方
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        if (null == nums || nums.length < 1) {
            return nums;
        }
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            nums[i] = nums[i] < 0 ? Math.abs(nums[i]) : nums[i];
        }
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        return nums;
    }

    public static void main(String[] args) {
    }
}
