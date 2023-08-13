package com.audi.leetcode.dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/non-overlapping-intervals/description/
 * <p>
 * 给定组数对，求最少去掉多少个数对，使得剩下的数对区间互相不重合
 * <p>
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 *
 * @author : wangquanzhou
 * @date : 2023/8/13 11:14
 */
public class NonOverlappingIntervals {

    /**
     * 使用dp思想求解
     * <p>
     * dp[i]表示，以第i个数对结尾，可以获得的最多的不包含重复区间的数对个数
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (null == intervals || intervals.length < 2) {
            return 0;
        }
        int len = intervals.length;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);

        // 按照数对的起始位置，进行从小到大的排序
        Arrays.sort(intervals, Comparator.comparing(v -> v[0]));

        int max = Integer.MIN_VALUE;
        // 时间复杂度O(N^2)），实际提交，也会TLE
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (intervals[j][1] <= intervals[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return len - max;
    }

    public int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals == null || intervals.length < 2) {
            return 0;
        }
        int len = intervals.length;

        // 按照数对的起始位置，进行从小到大的排序
        Arrays.sort(intervals, Comparator.comparing(v -> v[0]));

        int maxNonOverlap = 1; // 最大不重叠区间数
        int[] preEnd = intervals[0]; // 前一个区间的结束位置
        for (int i = 1; i < len; i++) {
            if (preEnd[1] <= intervals[i][0]) {
                // 当前区间与前一个区间不重叠，则更新最大不重叠区间数和前一个区间的结束位置
                maxNonOverlap++;
                preEnd = intervals[i];
            } else {
                // 当前区间与前一个区间重叠，选择结束位置较小的区间
                preEnd[1] = Math.min(preEnd[1], intervals[i][1]);
            }
        }
        return len - maxNonOverlap;
    }

    public static void main(String[] args) {

    }
}
