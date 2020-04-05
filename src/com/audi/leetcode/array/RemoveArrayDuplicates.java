package com.audi.leetcode.array;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * <p>
 * 移除数组中重复项，使得数组中各元素只出现一次，并且返回数组的新长度。
 * <p>
 * 需要注意的是：重复的元素需要删除，或者移动到数组的末尾
 *
 * @author WangQuanzhou
 * @date 2020-04-04
 */
public class RemoveArrayDuplicates {
    public int removeDuplicates(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return 0;
        }
        if (same(nums, 0)) {
            return 1;
        }

        int length = nums.length;
        for (int i = 1; i < length; i++) {
            int item = nums[i];
            if (same(nums, i)) {
                return i + 1;
            }
            if (nums[i] == nums[i - 1]) {
                transform(nums, i);
                length--;
                i--;

            }
        }

        return length;
    }

    /**
     * 将num[pos]的元素移动到数组的末尾
     *
     * @param nums
     * @param pos
     */
    public void transform(int[] nums, int pos) {
        if (1 == nums.length) {
            return;
        }
        for (int i = pos; i < nums.length - 1; i++) {
            int temp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = temp;
        }
    }

    /**
     * 从pos位置开始（包含pos）   后续的元素是否全部都等于nums[pos]
     *
     * @param nums
     * @param pos
     * @return
     */
    public boolean same(int[] nums, int pos) {
        for (int i = pos; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
//        int[] nums = {1, 1, 5, 5};
        int[] nums = {5};
//        int[] nums = {1, 5, 5, 5, 6, 7};
        RemoveArrayDuplicates removeArrayDuplicates = new RemoveArrayDuplicates();
        int length = removeArrayDuplicates.removeDuplicates(nums);
        System.out.println("length = " + length);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }

    }
}