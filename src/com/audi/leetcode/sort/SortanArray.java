package com.audi.leetcode.sort;


/**
 * 对一个无序的整型数组按照升序进行排序
 * <p>
 * https://leetcode.com/problems/sort-an-array/
 * <p>
 * 直接使用快排的思想进行排序
 *
 * @author: WangQuanzhou
 * @date: 2021-10-31 3:13 PM
 */
public class SortanArray {

    public int[] sortArray(int[] nums) {
        // 事假复杂度  O(N)*Log(N)
        quickSort(nums, 0, nums.length);
        return nums;
    }

    /**
     * 快排
     *
     * @param nums
     * @param low
     * @param high
     */
    public void quickSort(int[] nums, int low, int high) {
        if (null == nums || nums.length < 2) {
            return;
        }
        if (low < high) {
            int i = low, j = high, pivot = nums[low];
            while (i < j) {
                while (i < j && nums[j] >= pivot) {
                    j--;
                }
                while (i < j && nums[i] <= pivot) {
                    i++;
                }
                // 交换左右两边的与基准值比较，顺序不符的元素
                if (i < j) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
            // 交换基准值与左右指针相遇的位置处的元素
            nums[low] = nums[i];
            nums[i] = pivot;
            // 递归处理左右两边的数据
            quickSort(nums, low, i - 1);
            quickSort(nums, i + 1, high);
        }
    }

    public static void main(String[] args) {

    }
}
