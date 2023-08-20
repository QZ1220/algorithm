package com.audi.leetcode.array;


import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/contains-duplicate/
 * <p>
 * 检查一个数组中是否出现重复元素，使用set来解
 *
 * @author: WangQuanzhou
 * @date: 2021/9/2 23:44
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        if (null == nums || nums.length < 2) {
            return false;
        }
        Set<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = {0};
        ContainsDuplicate containsDuplicate = new ContainsDuplicate();
        System.out.println(containsDuplicate.containsDuplicate(array));
        System.out.println(Integer.MAX_VALUE);
    }
}
