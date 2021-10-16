package com.audi.leetcode.search;


/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * <p>
 * 给定一个递增数组，判断一个给定的数字是否存在于数组中，存在的话这个数字会在数组出现两次，返回数字的位置
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * <p>
 * 思路：
 * 直接先使用二分，判断target是否存在，存在的话，再使用target的下标左右判断一下数组元素是否等于target即可
 *
 * @author: WangQuanzhou
 * @date: 2021-10-16 9:27 AM
 */
public class FindFirstandLastPositionofElementinSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (null == nums || nums.length < 1) {
            return res;
        }
        // 先使用二分查找找到元素的位置
        int pos = binarySearch(nums, target, 0, nums.length - 1);
        if (pos == -1) {
            return res;
        }
        int leftPos = pos;
        int rightPos = pos;
        // 先向左查找是否相等
        while (leftPos >= 0 && nums[leftPos] == nums[pos]) {
            leftPos--;
        }
        // 再向右查找是否相等
        while (rightPos < nums.length && nums[rightPos] == nums[pos]) {
            rightPos++;
        }

        res[0] = leftPos + 1;
        res[1] = rightPos - 1;

        return res;
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

    }
}
