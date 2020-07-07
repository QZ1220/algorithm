package com.audi.leetcode.recursion;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/combination-sum-ii/
 * <p>
 * 给出一组数字nums，以及一个目标值target，问能找到多少个nums的子集，使得子集的和为target
 * <p>
 * 其实这道题和SubsetsII类似，只是初始时以及循环的过程需要做一些优化
 *
 * @author: WangQuanzhou
 * @date: 2020/6/27 22:14
 */
public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        // 预先去除比target还大的元素
        List<Integer> linkedList = new LinkedList<>();
        for (int i : candidates) {
            if (i < target) {
                linkedList.add(i);
            }
        }

        // 使用排序  避免重复子集
        // 这里如果使用倒序排序的话，下面的第二层for循环的次数更少
//        linkedList = linkedList.stream().sorted().collect(Collectors.toList());
        linkedList = linkedList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        Set<List<Integer>> set = new HashSet<>();
        // 总共的子集的个数
        int total = 1 << linkedList.size();
        for (int i = 0; i < total; i++) {
            // 临时记录求和结果
            int tempSum = 0;
            // 单个子集
            List<Integer> item = new LinkedList<>();
            for (int j = 0; j < linkedList.size(); j++) {
                // 左移操作
                if ((i & (1 << j)) != 0) {
                    Integer temp = linkedList.get(j);
                    tempSum = tempSum + temp;
                    // 加判断  减少循环次数
                    if (tempSum > target) {
                        break;
                    }
                    item.add(temp);
                }
            }
            if (item.stream().mapToInt(Integer::intValue).sum() == target) {
                set.add(item);
            }
        }
        return new LinkedList<>(set);
    }

    public static void main(String[] args) {
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
        CombinationSumII combinationSumII = new CombinationSumII();
        List<List<Integer>> lists = combinationSumII.combinationSum2(nums, 8);
        System.out.println(lists);
    }
}
