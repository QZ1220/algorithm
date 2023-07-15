package com.audi.other;

import java.util.LinkedList;
import java.util.List;

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

    public List<List<Integer>> subsetsWithDup(int[] nums) {

    }

    public static void main(String[] args) {

    }
}
