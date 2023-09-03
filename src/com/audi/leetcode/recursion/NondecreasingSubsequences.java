package com.audi.leetcode.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/non-decreasing-subsequences/
 * <p>
 * 给定一个整型数组，求数组的自增子序列（要求子序列至少包含两个元素）
 * <p>
 * Input: nums = [4,6,7,7]
 * Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 *
 * @author : wangquanzhou
 * @date : 2023/9/3 11:42
 */
public class NondecreasingSubsequences {

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, res, new LinkedList<>());
        return res;
    }

    private void backtrack(int[] nums, int startIndex, List<List<Integer>> res, List<Integer> item) {
        if (startIndex > nums.length) {
            return;
        }
        if (item.size() > 1) {
            res.add(new ArrayList<>(item));
        }
        for (int i = startIndex; i < nums.length; i++) {
            if (!item.isEmpty() && item.get(item.size() - 1) > nums[i]) {
                continue;
            }
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            item.add(nums[i]);
            backtrack(nums, i + 1, res, item);
            item.remove(item.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 1};
        NondecreasingSubsequences subsequences = new NondecreasingSubsequences();
        System.out.println(subsequences.findSubsequences(nums));
    }
}
