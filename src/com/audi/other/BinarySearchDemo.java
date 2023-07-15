package com.audi.other;

/**
 * 二分查找  二叉排序树 相关
 */
public class BinarySearchDemo {

    /**
     * 求目标值target是否在排序数组nums中，在的话就返回nums的下标，否则返回应该插入的位置，使得插入后的数组仍然有序
     * <p>
     * 递归实现
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    private int binarySearch(int[] nums, int target, int left, int right) {
        // 终止条件
        if (left > right) {
            return left;
        }

        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            return binarySearch(nums, target, mid + 1, right);
        } else {
            return binarySearch(nums, target, left, mid - 1);
        }
    }

    /**
     * 求目标值target是否在排序数组nums中，在的话就返回nums的下标，否则返回应该插入的位置，使得插入后的数组仍然有序
     * <p>
     * 非递归实现
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert2(int[] nums, int target) {
        int left = 0;
        int right=nums.length-1;
        while (left<=right){
            int mid = (left+right)/2;
            if (nums[mid]==target){
                return mid;
            }
            if (nums[left]>target){
                return left;
            }
            if (nums[right]<target){
                return right+1;
            }
            if (target>nums[mid]){
                left=mid+1;
            }
            if (target<nums[mid]){
                right=mid-1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        BinarySearchDemo binarySearchDemo = new BinarySearchDemo();
        int i = binarySearchDemo.searchInsert(nums, 6);
        System.out.println(i);
    }
}
