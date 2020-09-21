package com.audi.leetcode.search;

/**
 * https://leetcode.com/problems/swim-in-rising-water/
 * <p>
 * N*N的二维框内，grid[i][j]表示（i，j）位置的高度，你可以从上下左右四个方向游动（移动的时间可以忽略），不能游出二维框。
 * <p>
 * 与此同时，天上也在一直下雨，t时刻，雨水的高度也是t，问  最少  需要多久，可以从（0，0）位置游到（N-1,N-1）位置
 * <p>
 * 典型的搜索问题
 *
 * @author WangQuanzhou
 * @date 2020-09-17
 */
public class SwiminRisingWater {

    // 以grid左上角为原点，横向为X轴，纵向为Y轴
    public int swimInWater(int[][] grid) {

        if (null==grid||grid)

        // 行数
        int rows = grid.length;
        int columns = grid[0].length;

    }

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * 重写equals方法，便于后续Set判重
         *
         * @param obj
         * @return
         */
        @Override
        public boolean equals(Object obj) {
            Point point = (Point) obj;
            return this.x == point.x && this.y == point.y;
        }
    }

    public static void main(String[] args) {

    }
}
