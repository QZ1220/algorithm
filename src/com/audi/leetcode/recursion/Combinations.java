package com.audi.leetcode.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/combinations/
 * <p>
 * 题目给定两个数字n、k，求在【1-n】之间，选择k个数，可以获得的组合情况
 * <p>
 * Input: n = 4, k = 2
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * Explanation: There are 4 choose 2 = 6 total combinations.
 * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
 * <p>
 * Input: n = 1, k = 1
 * Output: [[1]]
 * Explanation: There is 1 choose 1 = 1 total combination.
 *
 * @author : wangquanzhou
 * @date : 2023/8/13 22:56
 */
public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(n, k, 1, res, new LinkedList<>());
        return res;
    }

    private void dfs(int n, int k, int startIndex, List<List<Integer>> res, List<Integer> item) {
        if (item.size() == k) {
            res.add(new ArrayList<>(item));
            return;
        }

        for (int i = startIndex; i <= n; i++) {
            // 剪枝操作，如果剩下的可选的元素个数，达不到k-item.size 证明无法满足题意，直接忽略
            if ((k-item.size())>(n-startIndex+1)){
                continue;
            }
            item.add(i);
            dfs(n, k, i + 1, res, item);
            item.remove(item.size() - 1);
        }
    }

    public static void main(String[] args) {
        Combinations combinations = new Combinations();
        System.out.println(combinations.combine(4, 2));
    }
}
