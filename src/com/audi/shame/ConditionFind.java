package com.audi.shame;


import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个整型数组，希望找出数组符合条件的数据
 * 1、当前数字左边的数字都不比当前数字大
 * 2、当前数字右边的数字都不比当前数字小
 * 3、时间复杂度小于O(N^2)
 * <p>
 * 输入：1，4，2，8，22，19
 * 输出：1，8
 *
 * @author: WangQuanzhou
 * @date: 2021-10-13 10:38 PM
 */
public class ConditionFind {

    /**
     * 利用双指针的思想，类比于接雨水的题
     * <p>
     * 时间复杂度O(N)
     *
     * @param nums
     * @return
     */
    private List<Integer> condition(int[] nums) {
        if (null == nums || nums.length < 1) {
            return null;
        }
        int length = nums.length;

        int[] left = new int[length];
        int[] right = new int[length];

        List<Integer> result = new LinkedList<>();

        // 预设初值
        left[0] = nums[0];
        right[length - 1] = nums[length - 1];
        // 从左向右，找当前位置左边最大的元素
        for (int i = 1; i < length; i++) {
            left[i] = nums[i] > left[i - 1] ? nums[i] : left[i - 1];
        }

        // 从右向左，找当前位置右边最小的元素
        for (int i = length - 2; i >= 0; i--) {
            right[i] = nums[i] < right[i + 1] ? nums[i] : right[i + 1];
        }

        // 按照题设进行判断
        for (int i = 0; i < length; i++) {
            if (nums[i] >= left[i] && nums[i] <= right[i]) {
                result.add(nums[i]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4};
//        int[] nums = {1, 4, 2, 8, 22, 19};
//        int[] nums = {4, 3, 2, 1};
        int[] nums = {1, 23, 12, 89, 8, 5, 3, 100, 100};
//        int[] nums = {1, 23, 12, 89, 8, 5, 3, 100, 78, 20, 12, 34};
        ConditionFind conditionFind = new ConditionFind();
        List<Integer> list = conditionFind.condition(nums);
        System.out.println(list);
    }
}
