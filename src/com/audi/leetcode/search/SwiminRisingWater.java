package com.audi.leetcode.search;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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

    public static final int[] dx = {0, 0, 1, -1};
    public static final int[] dy = {1, -1, 0, 0};

    // 以grid左上角为原点，横向为X轴，纵向为Y轴
    public int swimInWater(int[][] grid) {

        // 如果grid为空 或者 grid只有一个点  皆不需要移动即可到达
        if (null == grid || (grid.length < 2 && grid[0].length < 2)) {
            return 0;
        }

        // 行数
        int rows = grid.length;
        int columns = grid[0].length;

        Point startPoint = new Point(0, 0);

        // 采用广度优先搜索来解决本题
        Queue<Point> queue = new LinkedList<>();
        queue.add(startPoint);
        // 已经便利过的点
        Set<Point> visitSet = new HashSet<>();
        visitSet.add(startPoint);

        // t时刻
        int t = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                for (int k = 0; k < 4; k++) {
                    int newX = i + dx[k];
                    int newY = j + dy[k];
                    // 判断是否超出边界，或者已经访问过，则忽略改点
                    if ()
                }
            }
        }

    }

    /**
     * 在t时刻更新grid矩阵的数据 使用雨水将grid的各个点填充高
     *
     * @param grid
     * @param t
     */
    private void updateGrid(int[][] grid, int t, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] < t) {
                    grid[i][j] = t;
                }
            }
        }
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
