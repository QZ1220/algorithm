package com.audi.leetcode.recursion;


import java.util.*;

/**
 * https://leetcode.com/problems/subsets-ii/
 * <p>
 * 与subsets的差异在于  初始的数组内是允许有重复元素存在的
 * <p>
 * 由于nums中元素存在重复的，那么如果沿用subsets的算法，就会产生重复的子集，所以这里需要想办法去掉重复的元素。可以使用set来保证元素唯一
 * <p>
 * 但是这里唯一的概念还需要注意  例如1 4 4子集  和4 1 4子集也是一样的{解决这个问题可以先对初始nums进行排序}
 *
 * @author: WangQuanzhou
 * @date: 2020/6/21 22:18
 */
public class SubsetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 使用排序  避免重复子集
        Arrays.sort(nums);
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


    private void backtrack(int[] nums, int startIndex, List<Integer> path, List<List<Integer>> res, BitSet usedSet) {
        // 每一步都需要收集结果
        res.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            // 剪枝去重
            if (i > startIndex && nums[i] == nums[i - 1] && !usedSet.get(i - 1)) {
                continue;
            }
            path.add(nums[i]);
            usedSet.set(i);
            backtrack(nums, i + 1, path, res, usedSet);
            path.remove(path.size() - 1);
            usedSet.clear(i);
        }
    }

    public static void main(String[] args) {
        SubsetsII subsets = new SubsetsII();
        int[] nums = {4, 4, 4, 1, 4};
        System.out.println(subsets.subsetsWithDup(nums));
    }
}
