package com.audi.sort;


/**
 * https://developer.51cto.com/art/201403/430986.htm
 * <p>
 * https://github.com/AudiVehicle/learn/blob/master/2018%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0%E4%B8%80.md#java%E5%AE%9E%E7%8E%B0%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F%E5%92%8C%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F
 * <p>
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
