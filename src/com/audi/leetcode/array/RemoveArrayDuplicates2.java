package com.audi.leetcode.array;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 * <p>
 * 本体可以使用删除重复元素的方法，当然也可以使用移动后续元素覆盖前面元素的方法。
 * <p>
 * 从算法效率上来看第二种方案肯定更好，它不是一次移动所有元素，而是一次移动一个元素去覆盖之前出现超过2次的元素，具体可以使用一个简单的例子推演
 *
 * @author WangQuanzhou
 * @date 2020-04-04
 */
public class RemoveArrayDuplicates2 {
    public int removeDuplicates(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        int count = 1;
        for (int i = 1; i < length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
                if (count > 2) {
                    remove(nums, i);
                    length--;
                    i--;
                }
            } else {
                count = 1;
            }

        }
        return length;
    }

    /**
     * 删除nums数组pos位置的元素，后续元素顺次前移
     *
     * @param nums
     * @param pos
     */
    private void remove(int[] nums, int pos) {
        while (pos < nums.length - 1) {
            nums[pos] = nums[pos + 1];
            pos++;
        }
    }

    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int j = 1, count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            if (count <= 2) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }


    public static void main(String[] args) {
//        int[] nums = {1, 1, 5, 5};
//        int[] nums = {5};
//        int[] nums = {1, 5, 5, 5, 6, 7};
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        RemoveArrayDuplicates2 removeArrayDuplicates = new RemoveArrayDuplicates2();
        int length = removeArrayDuplicates.removeDuplicates2(nums);
        System.out.println("length = " + length);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }

    }
}