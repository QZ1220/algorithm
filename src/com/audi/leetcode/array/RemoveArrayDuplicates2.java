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
public class RemoveArrayDuplicates2 {
    public int removeDuplicates(int[] nums) {

        //
        // Initialize the counter and the second pointer.
        //
        int j = 1, count = 1;

        //
        // Start from the second element of the array and process
        // elements one by one.
        //
        for (int i = 1; i < nums.length; i++) {

            //
            // If the current element is a duplicate, increment the count.
            //
            if (nums[i] == nums[i - 1]) {

                count++;

            } else {

                //
                // Reset the count since we encountered a different element
                // than the previous one.
                //
                count = 1;
            }

            //
            // For a count <= 2, we copy the element over thus
            // overwriting the element at index "j" in the array
            //
            if (count <= 2) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    public static void main(String[] args) {
//        int[] nums = {1, 1, 5, 5};
//        int[] nums = {5};
        int[] nums = {1, 5, 5, 5, 6, 7};
//        int[] nums = {1, 5, 5, 5, 5, 5, 5};
        RemoveArrayDuplicates2 removeArrayDuplicates = new RemoveArrayDuplicates2();
        int length = removeArrayDuplicates.removeDuplicates(nums);
        System.out.println("length = " + length);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }

    }
}