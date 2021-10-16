package com.audi.leetcode.search;


/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * <p>
 * 在一个旋转数组中判断某个给定的数字是否存在
 * 要求时间复杂度在O(logN)
 * <p>
 * 思路：
 * 首先实现一个基本的二分查找
 * 然后将旋转的数组分成两个排序数组，然后判断应该在哪个数组内做二分查找，然后直接二分查找即可
 *
 * @author: WangQuanzhou
 * @date: 2021-10-16 8:50 AM
 */
public class SearchinRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        if (nums.length < 2) {
            return target == nums[0] ? 0 : -1;
        }

        // 将原始数组拆分成两个有序数组
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (target > nums[i]) {
                    return -1;
                }
                int leftFind = binarySearch(nums, target, 0, i);
                if (leftFind != -1) {
                    return leftFind;
                }
                int rightFind = binarySearch(nums, target, i + 1, length - 1);
                if (rightFind != -1) {
                    return rightFind;
                }
                return -1;
            }
        }
        // 考虑  全部旋转的情况  其实就相当于是没有旋转  也就是基本的二分查找
        return binarySearch(nums, target, 0, length - 1);
    }

    /**
     * 基本的二分查找，找到返回数组下标，没找返回-1
     *
     * @param nums
     * @param target
     * @param left
     * @param right
     * @return
     */
    private int binarySearch(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;

        if (target == nums[mid]) {
            return mid;
        } else if (target > nums[mid]) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
        return binarySearch(nums, target, left, right);
    }

    public static void main(String[] args) {
        SearchinRotatedSortedArray sort = new SearchinRotatedSortedArray();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int search = sort.search(nums, target);
        System.out.println(search);
    }
}
