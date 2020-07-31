package com.audi.sort;


import java.util.Arrays;

/**
 * https://developer.51cto.com/art/201403/430986.htm
 * 快速排序
 *
 * @author: WangQuanzhou
 * @date: 2020/7/30 21:57
 */
public class QuickSort {

    private void quickSort(int[] nums, int left, int right) {
        if (null == nums || nums.length < 2) {
            return;
        }
        if (left >= right) {
            return;
        }

        int i = left;
        int j = right;
        int base = nums[left];
        while (left < right) {
            while (right > left && nums[right] >= base) {
                right--;
            }

            while (left < right && nums[left] <= base) {
                left++;
            }

            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }

        nums[i] = nums[left];
        nums[left] = base;

        quickSort(nums, i, left - 1);
        // 注意下面不能使用left++
        quickSort(nums, left + 1, j);

    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums = {1, 2, 4, 5, 2, 1, 8, 5};
//        int[] nums = {5, 6, 9, 2, 3};
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        quickSort.quickSort(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
