package com.audi.other.test;

/**
 * 实现二分查找
 *
 * @author : wangquanzhou
 * @date : 2025/12/29 15:20
 */
public class HalfSearch {

    // 在nums数组中查找target，找到则返回数组下标，否则返回-1
    private int halfSearch(int[] nums, int target) {
        if (null == nums || nums.length < 1) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
