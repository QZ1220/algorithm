package com.audi.leetcode.recursion;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/permutations/
 * <p>
 * 给定一些包含不重复数字的数组，求这个数组的可能的所有全排列的集合
 * <p>
 * Input: nums = [1,2,3]
 * Output:
 * [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
 *
 * @author : wangquanzhou
 * @date : 2023/7/21 07:22
 */
public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        dfs(nums, new LinkedList<>(), res, new HashSet<>());
        return res;
    }

    private void dfs(int[] nums, List<Integer> list, List<List<Integer>> res, Set<Integer> visitSet) {
        if (list.size() == nums.length) {
            res.add(new LinkedList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visitSet.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            visitSet.add(nums[i]);
            dfs(nums, list, res, visitSet);

            list.remove(list.size() - 1);
            visitSet.remove(nums[i]);
        }
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Permutations permutations = new Permutations();
        System.out.println(permutations.permute(nums));
    }


}
