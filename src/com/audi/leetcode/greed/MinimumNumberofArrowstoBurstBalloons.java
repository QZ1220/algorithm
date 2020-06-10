package com.audi.leetcode.greed;


import org.omg.Messaging.SyncScopeHelper;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 * <p>
 * 射爆所有的气球需要的最少射击次数
 * <p>
 * Input:
 * [[10,16], [2,8], [1,6], [7,12]]
 * <p>
 * Output:
 * 2
 * <p>
 * Explanation:
 * One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 *
 * @author WangQuanzhou
 * @date 2020-06-09
 */
public class MinimumNumberofArrowstoBurstBalloons {

    /**
     * https://www.jianshu.com/p/31d06c53f76d
     * <p>
     * 既然所有的气球都是要刺破的，对于某一个气球来说，在哪一个位置刺破可以顺带刺破的其他气球越多，就是刺破这个气球的最佳位置。
     * 统计所有的“最佳位置”，就可以得出最终答案。
     * <p>
     * 对所有的气球排序，排序按照终止位置end从小到大,则从头遍历所有气球，刺破第一个气球的“最佳射击点”就是第一个气球end位置，
     * 同时清除顺带刺破的其他气球以后，下一个“最佳射击点”就是下一个气球的end位置..统计射击的次数，就是最终答案。
     * <p>
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (null == points || 0 == points.length) {
            return 0;
        }

        // 按每个气球的x_start进行排序
        Arrays.sort(points, Comparator.comparing(e -> e[0]));


        int x_end = points[0][1];

        // 注意这里初始值是1
        int total = 1;

        for (int i = 1; i < points.length; i++) {
            int[] point = points[i];
            if (point[0] > x_end) {
                total++;
                x_end = point[1];
            } else {
                x_end = Math.min(point[1], x_end);
            }

        }
        return total;
    }

    public static void main(String[] args) {
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};

        MinimumNumberofArrowstoBurstBalloons balloons = new MinimumNumberofArrowstoBurstBalloons();
        System.out.println(balloons.findMinArrowShots(points));

    }

}
