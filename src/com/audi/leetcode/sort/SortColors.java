package com.audi.leetcode.sort;


/**
 * https://leetcode.com/problems/sort-colors/
 * <p>
 * nums数组内的内容只会是0、1、2等
 * <p>
 * 要求将nums内容按递增进行排序
 *
 * @author: WangQuanzhou
 * @date: 2020/10/17 19:00
 */
public class SortColors {

    public void sortColors(int[] nums) {
        if (null == nums || nums.length < 2) {
            return;
        }

        int[] countArray = new int[3];
        for (int num : nums) {
            countArray[num]++;
        }

        int pos = 0;
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                nums[pos++] = i;
            }
        }
    }

    public static void main(String[] args) {
//        int[] nums = {2, 0, 2, 1, 1, 0};
        int[] nums = {2, 0, 1};


        SortColors sortColors = new SortColors();
        sortColors.sortColors(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
