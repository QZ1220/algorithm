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
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] > target) {
                return left;
            }
            if (nums[right] < target) {
                return right + 1;
            }
            if (target > nums[mid]) {
                left = mid + 1;
            }
            if (target < nums[mid]) {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 二分搜索的递归实现
     *
     * @param arr
     * @param target
     * @param low
     * @param high
     * @return
     */
    public static int binarySearch22(int[] arr, int target, int low, int high) {
        if (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                return binarySearch22(arr, target, mid + 1, high);
            } else {
                return binarySearch22(arr, target, low, mid - 1);
            }
        }
        return -1;
    }

    /**
     * 二分搜索的非递归实现
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert33(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1,-1};
        if (null==nums||nums.length<1){
            return  res;
        }
        int left=-1;
        boolean meet = false;
        int right=-1;
        for (int i = 0; i < nums.length; i++) {
            if (target==nums[i]){
                if (!meet){
                    left=i;
                    meet=true;
                }
                right=i;
            }
        }
        if (meet){
            res[0]=left;
            res[1]=right;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums={5,7,7,8,8,10};
        BinarySearchDemo binarySearchDemo = new BinarySearchDemo();
        int[] range = binarySearchDemo.searchRange(nums, -1);
        for (int i = 0; i < range.length; i++) {
            System.out.println(range[i]);
        }
    }
}
