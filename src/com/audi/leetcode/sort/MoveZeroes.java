package com.audi.leetcode.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * https://leetcode.com/problems/move-zeroes/
 * <p>
 * 将数组的0移动到数组的末尾，并且需要保持非零元素的相对位置
 * <p>
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * <p>
 * <p>
 * Input: nums = [0]
 * Output: [0]
 * <p>
 * 初步思想是可以采取类似冒泡的思想，对0进行冒泡，直到数组末尾
 *
 * @author : wangquanzhou
 * @date : 2022/7/8 07:32
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        if (null == nums || nums.length <= 1) {
            return;
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 从前往后查找0元素
            if (nums[i] != 0) {
                continue;
            }
            // 找到0元素  开始往后移动
            int temp_i = i;
            while (i < len - 1) {
                if (nums[i + 1] > 0) {
                    swap(nums, i, i + 1);
                }
                i++;
            }
            i = temp_i;
        }
    }

    /**
     * 交换数组nums的i、j位置的元素
     *
     * @param nums
     * @param i
     * @param j
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 整体思路：
     * @param nums
     */
    public void moveZero(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }

    public static void main(String[] args) {
//        int[] nums = {0, 1, 2, 0, 3, 0, 12};
        int[] nums = {0, 0, 1};
        MoveZeroes moveZeroes = new MoveZeroes();
        moveZeroes.moveZeroes(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

}
