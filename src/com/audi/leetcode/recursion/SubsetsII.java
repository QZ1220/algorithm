package com.audi.leetcode.recursion;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/subsets-ii/
 * <p>
 * 与subsets的差异在于  初始的数组内是允许有重复元素存在的
 * <p>
 * 由于nums中元素存在重复的，那么如果沿用subsets的算法，就会产生重复的子集，所以这里需要想办法去掉重复的元素。可以使用set来保证元素唯一
 *
 * 但是这里唯一的概念还需要注意  例如1 4 4子集  和4 1 4子集也是一样的
 *
 * @author: WangQuanzhou
 * @date: 2020/6/21 22:18
 */
public class SubsetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
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
            set.add(item);
        }
        return new LinkedList<>(set);
    }

    public static void main(String[] args) {
        SubsetsII subsets = new SubsetsII();
        int[] nums = {4, 4, 4, 1, 4};
        System.out.println(subsets.subsetsWithDup(nums));
    }
}
