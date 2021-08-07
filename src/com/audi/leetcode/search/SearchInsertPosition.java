package com.audi.leetcode.search;


/**
 * https://leetcode.com/problems/search-insert-position/
 * <p>
 * 题目给出了一个有序数组，希望在有序数组中查找一个数，如果找到返回下标，否则返回应该插入的下标位置，使得数组仍然保持有序
 *
 * @author: WangQuanzhou
 * @date: 2021-08-07 4:40 PM
 */
public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (target > nums[high]) {
                return high + 1;
            }
            if (target < nums[low]) {
                return low;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            }
            if (nums[mid] < target) {
                low = mid + 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
    }
}
