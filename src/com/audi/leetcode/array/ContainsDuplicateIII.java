package com.audi.leetcode.array;


/**
 * https://leetcode.com/problems/contains-duplicate-iii/
 * <p>
 * 求一个数组中，是否存在i,j两个位置的元素（i！=j），使得abd(i-j)<=k,abs(nums[i]-nums[j])<=t，其中k、t为入参
 * <p>
 * 使用大小为k+1滑动窗口的思想来解题
 *
 * @author: WangQuanzhou
 * @date: 2021-09-03 7:30 AM
 */
public class ContainsDuplicateIII {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (null == nums || nums.length < 2) {
            return false;
        }

        for (int i = 0; i < nums.length; i++) {
            int front = nums[i];
            for (int j = 1; j <= k; j++) {
                if (i + j >= nums.length) {
                    break;
                }
                int last = nums[i + j];
                // 有可能数据越界，使用long求差
                long res = Long.valueOf(front) - Long.valueOf(last);
                if (Math.abs(res) <= t) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {-2147483648, 2147483647};
//        int[] nums = {2147483646, 2147483647};
        ContainsDuplicateIII duplicateIII = new ContainsDuplicateIII();
//        boolean b = duplicateIII.containsNearbyAlmostDuplicate(nums, 3, 3);
        boolean b = duplicateIII.containsNearbyAlmostDuplicate(nums, 1, 1);
        System.out.println(b);
    }
}
