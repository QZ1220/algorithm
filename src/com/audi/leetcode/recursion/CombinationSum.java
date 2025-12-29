package com.audi.leetcode.recursion;

import java.util.ArrayList;
import java.util.Arrays;
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

        // 提前判断 过滤掉不满足题意的元素
        int[] array = Arrays.stream(candidates).filter(v -> v <= target).sorted().toArray();
        // 这里不能做这个判断，因为 元素可以重复使用，如果元素不允许重复使用，那么可以增加这个判断
//        if (sum < target) {
//            return new LinkedList<>();
//        }

        List<List<Integer>> res = new ArrayList<>();
        backTrack2(array, target, 0, 0, res, new LinkedList<>());
        return res;
    }

    /**
     * 未剪枝优化版本
     *
     * @param candidates
     * @param target
     * @param tempSum
     * @param startIndex
     * @param res
     * @param item
     */
    public void backTrack(int[] candidates, int target, int tempSum, int startIndex,
                          List<List<Integer>> res, List<Integer> item) {
        if (tempSum > target) {
            return;
        }
        if (tempSum == target) {
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            int candidate = candidates[i];
            tempSum += candidate;
            item.add(candidate);
            // 不用i+1了，表示可以重复读取当前的数
            backTrack(candidates, target, tempSum, i, res, item);
            tempSum -= candidate;
            item.remove(item.size() - 1);
        }
    }

    /**
     * 剪枝优化版本
     *
     * @param candidates
     * @param target
     * @param tempSum
     * @param startIndex
     * @param res
     * @param item
     */
    public void backTrack2(int[] candidates, int target, int tempSum, int startIndex,
                           List<List<Integer>> res, List<Integer> item) {

        if (tempSum == target) {
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            int candidate = candidates[i];
            // 如果 sum + candidates[i] > target 就终止遍历
            // 这里剪枝要求candidates一定要是从小到大经过排序的，否则不行
            if (tempSum + candidate > target) {
                // 注意这里是break，不是continue，因为前置已经对数组做了排序，当前位置的元素不能满足题意，那么它后面的元素都不能满足题意
                break;
            }
            tempSum += candidate;
            item.add(candidate);
            // 不用i+1了，表示可以重复读取当前的数
            backTrack2(candidates, target, tempSum, i, res, item);
            tempSum -= candidate;
            item.remove(item.size() - 1);
        }
    }

    public static void main(String[] args) {

        int[] arr = {8, 7, 4, 3};
        CombinationSum combinationSum = new CombinationSum();
        System.out.println(combinationSum.combinationSum(arr, 11));

    }
}
