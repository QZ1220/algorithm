package com.audi.leetcode.two.pointer;

/**
 * https://leetcode.com/problems/contains-duplicate-iii/description/
 *
 * @author : wangquanzhou
 * @date : 2023/7/17 21:45
 */
public class ContainsDuplicateIII {

    /**
     * 在给定的数组中，是否存在区间[i,j]，满足以下条件
     * i != j,
     * abs(i - j) <= indexDiff.
     * abs(nums[i] - nums[j]) <= valueDiff
     * <p>
     * 满足返回true，否则返回false
     * <p>
     * 尝试使用双指针解决
     *
     * @param nums
     * @param indexDiff
     * @param valueDiff
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + indexDiff; j++) {
                if (j < nums.length) {
                    if (Math.abs(i - j) <= indexDiff && Math.abs(nums[i] - nums[j]) <= valueDiff) {
                        return true;

                    }
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {

    }
}
