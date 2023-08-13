package com.audi.leetcode.dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/maximum-length-of-pair-chain/description/
 * <p>
 * 给定一组数对，求这些数对可以组成的最长数对区间所需要的数对的个数
 * <p>
 * Input: pairs = [[1,2],[2,3],[3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4].
 * <p>
 * Input: pairs = [[1,2],[7,8],[4,5]]
 * Output: 3
 * Explanation: The longest chain is [1,2] -> [4,5] -> [7,8].
 *
 * @author : wangquanzhou
 * @date : 2023/8/13 11:57
 */
public class MaximumLengthofPairChain {

    /**
     * 利用动态规划的思想求解
     * <p>
     * dp[i]表示以数对i结尾，组成最长数对区间，需要的数对的个数
     * <p>
     * 时间复杂度O(N^2)
     *
     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {
        int len = pairs.length;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);

        // 将pairs按照起点进行从小到大排序
        Arrays.sort(pairs, Comparator.comparing(v -> v[0]));

        int max = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    /**
     * 对上面的动态规划算法进行优化
     * <p>
     * 借助贪心的思想
     *
     * @param pairs
     * @return
     */
    public int findLongestChain2(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int n = pairs.length;
        int count = 1;
        int end = pairs[0][1];

        for (int i = 1; i < n; i++) {
            if (pairs[i][0] > end) {
                count++;
                end = pairs[i][1];
            }
        }
        return count;
    }

    public static void main(String[] args) {

    }
}
