package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/unique-binary-search-trees/
 * <p>
 * 给定数字n，求【1-n】区间的所有数字可以组成的二叉搜索树的个数
 * <p>
 * 使用动态规划思想求解，实际上本题不是很好想到DP的思想
 *
 * @author : wangquanzhou
 * @date : 2023/8/14 22:30
 */
public class UniqueBinarySearchTrees {

    /**
     * 使用动态规划的思路求解
     * <p>
     * 参考视频：https://www.bilibili.com/video/BV1eK411o7QA/?vd_source=d1530fb814268f770330143e24aaf1e6
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {

        // dp[i] ： 1到i为节点组成的二叉搜索树的个数为dp[i]。
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                // dp[j - 1]表示总共有i个节点的情况下，以j为头结点，左边的二叉搜索树的可能情况的个数
                // dp[i - j]表示总共有i个节点的情况下，以j为头结点，右边的二叉搜索树的可能情况的个数
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {

    }
}
