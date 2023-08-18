package com.audi.leetcode.recursion;

import java.util.*;

/**
 * https://leetcode.com/problems/permutations-ii/description/
 * <p>
 * 给定一个整型数组，数组可能包含重复数字，返回所有可能的不重复的重排列
 *
 * @author : wangquanzhou
 * @date : 2023/8/17 22:22
 */
public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return res;
        }
        Arrays.sort(nums);
        backtrack(nums, res, new LinkedList<>(), new BitSet());
        return res;
    }

    public void backtrack(int[] nums, List<List<Integer>> res, List<Integer> item, BitSet bitSet) {
        if (item.size() == nums.length) {
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !bitSet.get(i - 1)) {
                continue;
            }
            if (!bitSet.get(i)) {
                item.add(nums[i]);
                bitSet.set(i);
                backtrack(nums, res, item, bitSet);
                item.remove(item.size() - 1);
                bitSet.clear(i);
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = {3, 3, 0, 3};
        PermutationsII permutationsII = new PermutationsII();
        List<List<Integer>> list = permutationsII.permuteUnique(nums);
        System.out.println(list);
    }
}
