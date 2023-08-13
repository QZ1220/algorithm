package com.audi.leetcode.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-iii/
 * <p>
 * 给定整数k、n，使得在1-9区间选择不重复的数字k个，组成的和等于n
 * <p>
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Explanation:
 * 1 + 2 + 4 = 7
 * There are no other valid combinations.
 * <p>
 * Input: k = 3, n = 9
 * Output: [[1,2,6],[1,3,5],[2,3,4]]
 * Explanation:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * There are no other valid combinations.
 * <p>
 * Input: k = 4, n = 1
 * Output: []
 * Explanation: There are no valid combinations.
 * Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.
 *
 * @author : wangquanzhou
 * @date : 2023/8/13 23:28
 */
public class CombinationSumIII {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        // 此时怎么选都不可能满足题意
        if (n > 45) {
            return res;
        }

        boolean[] used = new boolean[9];
        dfs(k, n, 1, res, new LinkedList<>());
        return res;
    }

    private void dfs(int k, int n, int startIndex, List<List<Integer>> res, List<Integer> item) {
        if (item.size() > k) {
            return;
        }
        if (item.size() == k && n == item.stream().mapToInt(Integer::intValue).sum()) {
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = startIndex; i < 10; i++) {

            // 剪枝
            // 剩余的元素加起来都不能等于n，直接终止
            if ((45 - ((startIndex-1) * ((startIndex-1) + 1)) / 2) < (n - item.stream().mapToInt(Integer::intValue).sum())) {
                continue;
            }

            item.add(i);
            dfs(k, n, i + 1, res, item);
            item.remove(item.size() - 1);
        }
    }

    public static void main(String[] args) {

    }
}
