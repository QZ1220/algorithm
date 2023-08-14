package com.audi.leetcode.recursion;


import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/combination-sum-ii/
 * <p>
 * 给出一组数字nums，以及一个目标值target，问能找到多少个nums的子集，使得子集的和为target
 * <p>
 * 其实这道题和SubsetsII类似，只是初始时以及循环的过程需要做一些优化（剪枝操作）
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
        // 这里如果使用倒序排序的话，会提升一点效率
//        linkedList = linkedList.stream().sorted().collect(Collectors.toList());
        linkedList = linkedList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());


        List<Integer> item = new LinkedList<>();
        // 递归回溯
        subset(0, linkedList, item, set, 0, target);
        return new LinkedList<>(set);
    }

    private void subset(int i, List<Integer> linkedList, List<Integer> item, Set<List<Integer>> resultSet, int tempSum, int target) {
        if (i >= linkedList.size() || tempSum > target) {
            return;
        }
        tempSum += linkedList.get(i);
        item.add(linkedList.get(i));
        // 注意这里要新建一个对象放入result，不能直接放入item到result
        if (target == tempSum) {
            resultSet.add(new LinkedList<>(item));
        }
        // 选择nums[i+1]
        subset(i + 1, linkedList, item, resultSet, tempSum, target);

        // 不选择nums[i+1]
        item.remove(item.size() - 1);
        tempSum -= linkedList.get(i);
        subset(i + 1, linkedList, item, resultSet, tempSum, target);
    }




    public List<List<Integer>> combinationSum4(int[] candidates, int target) {
        int[] array = Arrays.stream(candidates).filter(v -> v <= target).sorted().toArray();
        Set<List<Integer>> res = new HashSet<>();
        dfs(array, array.length - 1, target, 0, res, new LinkedList<>());
        return new LinkedList<>(res);
    }

    private void dfs(int[] nums, int i, int target, int tempSum, Set<List<Integer>> res, List<Integer> item) {
        if (i < 0) {
            return;
        }
        tempSum += nums[i];
        item.add(nums[i]);
        if (tempSum == target) {
            res.add(new LinkedList<>(item));
        }
        dfs(nums, i - 1, target, tempSum, res, item);

        tempSum -= nums[i];
        item.remove(item.size() - 1);
        dfs(nums, i - 1, target, tempSum, res, item);
    }


    public List<List<Integer>> combinationSum10(int[] candidates, int target) {
        int[] arr = Arrays.stream(candidates).filter(v -> v <= target).sorted().toArray();
        int sum = Arrays.stream(arr).sum();
        if (sum<target){
            return new LinkedList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        backTrack(arr,target,0,res,new LinkedList<>(),0);
        return res;
    }

    private void backTrack(int[] candidates, int target, int tempSum, List<List<Integer>> res, List<Integer> item, int startIndex) {
        if (tempSum == target) {
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            //正确剔除重复解的办法
            //跳过同一树层使用过的元素
            if ( i > startIndex && candidates[i] == candidates[i - 1] ) {
                // 注意这里使用的是 continue 不是break
                continue;
            }
            int candidate = candidates[i];
            // 剪枝优化
            if (tempSum + candidate > target) {
                // 注意这里使用的是 continue 不是break
                break;
            }

            tempSum += candidate;
            item.add(candidate);
            backTrack(candidates, target, tempSum, res, item, i + 1);
            tempSum -= candidate;
            item.remove(item.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
//        [[6,6,7,8],[6,7,14],[6,8,13],[6,9,12],[6,10,11],[6,21],[7,8,12],[7,9,11],[7,20],[8,8,11],[8,9,10],[9,9,9],[9,18],[10,17],[11,16],[13,14],[27]]
//        int[] nums = {14, 6, 25, 9, 30, 20, 33, 34, 28, 30, 16, 12, 31, 9, 9, 12, 34, 16, 25, 32, 8, 7, 30, 12, 33,
//                20, 21, 29, 24, 17, 27, 34, 11, 17, 30, 6, 32, 21, 27, 17, 16, 8, 24, 12, 12, 28, 11, 33, 10, 32, 22, 13, 34, 18, 12};
        CombinationSumII combinationSumII = new CombinationSumII();
//        List<List<Integer>> lists = combinationSumII.combinationSum2(nums, 27);
//        List<List<Integer>> lists = combinationSumII.combinationSum3(nums, 27);
        List<List<Integer>> lists = combinationSumII.combinationSum10(nums, 8);
        System.out.println(lists);
    }
}
