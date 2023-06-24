package com.audi.leetcode.array;

/**
 * https://leetcode.com/problems/remove-element/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 「移除」nums数组中等于val的元素，假设有k各不等于val的元素，那么移除以后，num数组的前k个元素都是不等于val的，后面的元素不关心
 *
 * @author : wangquanzhou
 * @date : 2023/6/18 11:22
 */
public class RemoveElement {

    /**
     * 使用双指针法解决
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int k = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // 从前往后，依次寻找不等于val的元素
            if (nums[left] != val) {
                left++;
                k++;
                continue;
            }

            // 从后往前，依次寻找等于val的元素
            if (nums[right] == val) {
                right--;
                continue;
            }

            // 将等于val的元素移动到数组尾部
            nums[left] = nums[right];
            left++;
            right--;
            k++;

        }
        return k;
    }


    public static void main(String[] args) {

        int nums[] = {3, 2, 2, 3};
        int val = 3;
        RemoveElement removeElement = new RemoveElement();
        int k = removeElement.removeElement(nums, val);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        System.out.println("k=" + k);
    }
}
