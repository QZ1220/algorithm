package com.audi.leetcode.recursion;


import java.util.ArrayList;
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
        // 总共的子集的个数  1左移nums.length位
        // 之所以可以这么算，因为所有的子集个数可以这么算：nums的每一个元素都可以放或者不放，若用1表示放，0表示不放，其实就是二进制，也就是2^(nums.length)
        // 正好可以使用移位算法
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

    // 回溯，每一步收集结果，这种方法，就不再需要在调用backtrack方法之前，放入空集合了
    private void backtrack(int[] nums, int startIndex, List<Integer> path, List<List<Integer>> res) {
        // 每一步都需要收集结果，注意要在结束条件之前进行收集，而非之后
        res.add(new ArrayList<>(path));
        // 这里的递归结束条件其实可以不加，因为后续的for循环在startIndex不满足条件时，会自动退出
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }


    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        int[] nums = {1, 2, 3};
        System.out.println(subsets.subsets2(nums));
    }
}
