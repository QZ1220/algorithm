package com.audi.leetcode.recursion;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/
 *
 * @author: WangQuanzhou
 * @date: 2020/6/11 0:05
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();

        List<Integer> item = new LinkedList<>();
        // 放入空子集
        result.add(item);
        // 递归回溯
        subset(0, nums, item, result);
        return result;
    }

    private void subset(int i, int[] nums, List<Integer> item, List<List<Integer>> result) {
        if (i >= nums.length) {
            return;
        }
        item.add(nums[i]);
        // 注意这里要新建一个对象放入result，不能直接放入item到result
        result.add(new LinkedList<>(item));
        // 选择nums[i+1]
        subset(i + 1, nums, item, result);

        // 不选择nums[i+1]
        item.remove(item.size() - 1);
        subset(i + 1, nums, item, result);

    }

    //  本题还可以使用位运算进行优化
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        // 总共的子集的个数
        int total = 1 << nums.length;
        for (int i = 0; i < total; i++) {
            // 单个子集
            List<Integer> item = new LinkedList<>();
            for (int j = 0; j < nums.length; j++) {
                // 左移操作
                if ((i & (1 << j)) != 0) {
                    item.add(nums[j]);
                }
            }
            result.add(item);
        }
        return result;
    }


    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        int[] nums = {1, 2, 3};
        System.out.println(subsets.subsets2(nums));
    }
}
