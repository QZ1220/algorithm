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
            if (i <= target) {
                linkedList.add(i);
            }
        }

        // 使用排序  避免重复子集
        // 这里如果使用倒序排序的话，下面的第二层for循环的次数更少
        linkedList = linkedList.stream().sorted().collect(Collectors.toList());
//        linkedList = linkedList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        Set<List<Integer>> set = new HashSet<>();
        // 总共的子集的个数
        // 这里即便使用long  对于比较长的nums输入 也会越界  所以还是需要改成递归回溯的方式来处理
        long total = 1 << linkedList.size();
        for (long i = 0; i < total; i++) {
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


    public List<List<Integer>> combinationSum3(int[] candidates, int target) {

        // 预先去除比target还大的元素
        List<Integer> linkedList = new LinkedList<>();
        for (int i : candidates) {
            if (i <= target) {
                linkedList.add(i);
            }
        }

        Set<List<Integer>> set = new HashSet<>();

        // 使用排序  避免重复子集
        // 这里如果使用倒序排序的话，下面的第二层for循环的次数更少
        linkedList = linkedList.stream().sorted().collect(Collectors.toList());
//        linkedList = linkedList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());


        List<Integer> item = new LinkedList<>();
        // 放入空子集
        set.add(item);
        // 递归回溯
        subset(0, linkedList, item, set, 0);
        return new LinkedList<>(set);
    }

    private void subset(int i, List<Integer> linkedList, List<Integer> item, Set<List<Integer>> resultSet, int tempSum) {
        if (i >= linkedList.size()) {
            return;
        }
        item.add(linkedList.get(i));
        // 注意这里要新建一个对象放入result，不能直接放入item到result
        resultSet.add(new LinkedList<>(item));
        // 选择nums[i+1]
        subset(i + 1, linkedList, item, resultSet);

        // 不选择nums[i+1]
        item.remove(item.size() - 1);
        subset(i + 1, linkedList, item, resultSet);

    }

    public static void main(String[] args) {
//        int[] nums = {10, 1, 2, 7, 6, 1, 5};
//        [[6,6,7,8],[6,7,14],[6,8,13],[6,9,12],[6,10,11],[6,21],[7,8,12],[7,9,11],[7,20],[8,8,11],[8,9,10],[9,9,9],[9,18],[10,17],[11,16],[13,14],[27]]
        int[] nums = {14, 6, 25, 9, 30, 20, 33, 34, 28, 30, 16, 12, 31, 9, 9, 12, 34, 16, 25, 32, 8, 7, 30, 12, 33,
                20, 21, 29, 24, 17, 27, 34, 11, 17, 30, 6, 32, 21, 27, 17, 16, 8, 24, 12, 12, 28, 11, 33, 10, 32, 22, 13, 34, 18, 12};
        CombinationSumII combinationSumII = new CombinationSumII();
        List<List<Integer>> lists = combinationSumII.combinationSum2(nums, 27);
        System.out.println(lists);
        System.out.println(1 << 20);
    }
}
