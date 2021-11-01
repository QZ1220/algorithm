package com.audi.leetcode.sort;


import java.util.Arrays;

/**
 * https://leetcode.com/problems/wiggle-sort-ii/
 * <p>
 * 题目给了一串无序的整型数组，要求输出一个排序的摇摆序列
 * <p>
 * Input: nums = [1,5,1,1,6,4]
 * Output: [1,6,1,5,1,4]
 * Explanation: [1,4,1,5,1,6] is also accepted.
 * <p>
 * Input: nums = [1,3,2,2,3,1]
 * Output: [2,3,1,3,1,2]
 * <p>
 * 题设说明，对于给定的输入，一定存在至少一个符合题意的解
 * <p>
 * 在不考虑时间和空间复杂度的情况下，可以先对数组进行从小到大的排序，
 * 然后新建一个临时数组，从后向前使用原数组的元素填充临时数组，先填充偶数位置，再填充奇数位置
 *
 * @author: WangQuanzhou
 * @date: 2021-11-01 11:05 AM
 */
public class WiggleSortII {

    public void wiggleSort(int[] nums) {
        if (null == nums || nums.length < 3) {
            return;
        }
        // 对数组元素进行排序
        Arrays.sort(nums);
        int pos = nums.length - 1;
        int[] temp = new int[nums.length];
        for (int i = 1; i < nums.length; i = i + 2) {
            temp[i] = nums[pos];
            pos--;
        }
        for (int i = 0; i < nums.length; i = i + 2) {
            temp[i] = nums[pos];
            pos--;
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 2, 3, 1};
        WiggleSortII wiggleSortII = new WiggleSortII();
        wiggleSortII.wiggleSort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
