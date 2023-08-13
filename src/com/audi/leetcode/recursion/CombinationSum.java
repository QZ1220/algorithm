package com.audi.leetcode.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 * <p>
 * 给定一个整型数组，一个target，求数组中元素和为target的不重复子集情况
 * <p>
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 *
 * @author : wangquanzhou
 * @date : 2023/8/14 07:38
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backTrack(candidates, target, 0, res, new LinkedList<>());
        return res;
    }

    public void backTrack(int[] candidates, int target, int tempSum, List<List<Integer>> res, List<Integer> item) {
        if (tempSum > target) {
            return;
        }
        if (tempSum == target) {
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            int candidate = candidates[i];
            tempSum += candidate;
            item.add(candidate);
            backTrack(candidates, target, tempSum, res, item);
            tempSum -= candidate;
            item.remove(item.size() - 1);
        }
    }

    public static void main(String[] args) {

        int[] arr = {2, 3, 6, 7};
        CombinationSum combinationSum = new CombinationSum();
        System.out.println(combinationSum.combinationSum(arr, 7));

    }
}
