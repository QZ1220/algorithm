package com.audi.leetcode.search;


/**
 * https://leetcode.com/problems/binary-search/
 * <p>
 * 实现基本的二分查找
 *
 * @author: WangQuanzhou
 * @date: 2021-10-16 9:41 AM
 */
public class BinarySearch {

    public int search(int[] nums, int target) {
        if (null == nums || nums.length < 1) {
            return -1;
        }

        return binarySearch(nums, target, 0, nums.length - 1);

    }

    /**
     * 基本的二分查找，找到返回数组下标，没找返回-1
     * <p>
     * 递归实现
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

    /**
     * 非递归实现
     *
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        if (null == nums || nums.length < 1) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
    }
}
