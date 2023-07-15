package com.audi.other;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 回溯相关题目
 *
 * @author : wangquanzhou
 * @date : 2023/7/15 15:14
 */
public class BackTrackDemo {

    /**
     * nums数组的元素无重复，求nums数组的所有合法的子集，空也是一个合法的子集
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        res.add(new LinkedList<>());
        subSet(nums, 0, res, new LinkedList<>());
        return res;
    }

    private void subSet(int[] nums, int i, List<List<Integer>> res, List<Integer> list) {
        if (i >= nums.length) {
            return;
        }
        list.add(nums[i]);
        res.add(new LinkedList<>(list));

        subSet(nums, i + 1, res, list);

        list.remove(list.size() - 1);
        subSet(nums, i + 1, res, list);
    }

    /**
     * 给定一个数组nums，nums的元素可能重复，求其无重复子集的个数
     * <p>
     * 先排序，再递归回溯，使用set进行子集的去重
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> resSet = new HashSet<>(1 << nums.length);
        uniqSubSet(0, nums, resSet, new LinkedList<>());
        LinkedList<List<Integer>> res = new LinkedList<>(resSet);
        res.add(new LinkedList<>());
        return res;
    }


    private void uniqSubSet(int i, int[] nums, Set<List<Integer>> resSet, List<Integer> list) {
        if (i >= nums.length) {
            return;
        }
        list.add(nums[i]);
        resSet.add(new LinkedList<>(list));

        uniqSubSet(i + 1, nums, resSet, list);

        list.remove(list.size() - 1);
        uniqSubSet(i + 1, nums, resSet, list);
    }

    /**
     * 给定一个target，求candidates的子集和为target的子集的集合
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int[] nums = Arrays.stream(candidates).filter(v -> v <= target).sorted().toArray();
        Set<LinkedList<Integer>> resSet = new HashSet<>();
        uniqSubSet(0, nums, resSet, new LinkedList<>(), target, 0);
        return new LinkedList<>(resSet);
    }

    private void uniqSubSet(int i, int[] candidates, Set<LinkedList<Integer>> resSet, List<Integer> list,
                            int target, int subSum) {
        if (i >= candidates.length || subSum > target || candidates[i] > target) {
            return;
        }
        list.add(candidates[i]);
        subSum = subSum + candidates[i];
        if (subSum == target) {
            resSet.add(new LinkedList<>(list));
        }

        uniqSubSet(i + 1, candidates, resSet, list, target, subSum);

        list.remove(list.size() - 1);
        subSum = subSum - candidates[i];
        uniqSubSet(i + 1, candidates, resSet, list, target, subSum);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 5};
        Arrays.sort(nums);
        for (int num : nums) {
            System.out.print(num+" ");
        }
        System.out.println();
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println(list);
        int[] array = Arrays.stream(nums).filter(v -> v != 2).toArray();
        for (int i : array) {
            System.out.print(i+" ");
        }
        System.out.println();
        List<Integer> list1 = Arrays.stream(nums).filter(v -> v != 2).boxed().collect(Collectors.toList());
        System.out.println(list1);
    }
}
