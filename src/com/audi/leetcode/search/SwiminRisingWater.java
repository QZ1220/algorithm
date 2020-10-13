package com.audi.leetcode.search;


import javafx.util.Pair;

import java.util.*;

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

    // x、y方向向量
    public static final int[] dx = {0, 0, 1, -1};
    public static final int[] dy = {1, -1, 0, 0};

    /**
     * https://blog.csdn.net/fuxuemingzhu/article/details/82926674
     * <p>
     * 参考这篇文章的第二种解题方法做的
     *
     * @param grid
     * @return
     */
    // 以grid左上角为原点，横向为X轴，纵向为Y轴
    public int swimInWater2(int[][] grid) {
        // 定义一个优先级队列  按照h从小到大排列
        Queue<Pair<Integer, Pair<Integer, Integer>>> queue = new PriorityQueue<>(Comparator.comparing(Pair::getKey));
        queue.add(new Pair<>(grid[0][0], new Pair<>(0, 0)));
        // 已经遍历过的点
        Set<Pair<Integer, Integer>> visitSet = new HashSet<>();
        visitSet.add(new Pair<>(0, 0));

        int res = 0;
        int length = grid.length;

        while (!queue.isEmpty()) {
            Pair<Integer, Pair<Integer, Integer>> top = queue.poll();
            Integer x = top.getValue().getKey();
            Integer y = top.getValue().getValue();
            res = Math.max(res, top.getKey());
            if (x == top.getValue().getValue() && y == length) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int newY = y + dy[i];
                int newX = x + dx[i];
                if (newX < 0 || newY < 0 || newX >= length || newY >= length || visitSet.contains(new Pair<>(newX, newY))) {
                    // 直接忽略
                    continue;
                }
                queue.add(new Pair<>(grid[newX][newY], new Pair<>(newX, newY)));
                visitSet.add(new Pair<>(newX, newY));
            }
        }
        return res;
    }

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
        // 已经便利过的点
        Set<Point> visitSet = new HashSet<>();
        visitSet.add(startPoint);

        // t时刻
        int max = max(grid, rows, columns);

        for (int t = 0; t <= max; t++) {
            System.out.println("t = " + t);
            updateGrid(grid, t, rows, columns);
            queue.add(startPoint);
            while (!queue.isEmpty()) {
                // still have problem...
                int initSize = queue.size();
                // fixme 如何跳出while循环 进入for循环？？？  2020年09月28日21:00:16
                Point point = queue.peek();
                int x = point.x;
                int y = point.y;
                for (int k = 0; k < 4; k++) {
                    int newY = y + dy[k];
                    int newX = x + dx[k];
                    Point newPoint = new Point(newX, newY);
                    // 判断是否超出边界，或者已经访问过，则忽略该点
                    // 注意这里x y方向与grid数组的行列关系
                    if (newX < 0 || newY < 0 || newX >= columns || newY >= rows || visitSet.contains(newPoint)) {
                        // 直接忽略
                        continue;
                    }

                    // 寻找可联通位置「寻找与当前位置高度相等的点」
                    if (grid[y][x] == grid[newY][newX]) {
                        if (newX == columns - 1 && newY == rows - 1) {
                            // 已经到达右下角，搜索结束
                            return t;
                        }
                        // 将newPoint标记为已经访问过
                        visitSet.add(newPoint);
                        // 将newPoint存入队列，便于下一次以其为基点进行搜索
                        queue.add(newPoint);
                    }
                }
                // 移除队头，也就是point点
                queue.remove();
            }
        }
        return -1;
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


    /**
     * 找到grid二维数组中值最大的节点
     *
     * @param grid
     * @param rows
     * @param columns
     * @return
     */
    private int max(int[][] grid, int rows, int columns) {
        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] > max) {
                    max = grid[i][j];
                }
            }
        }
        return max;
    }

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * 重写hashCode方法，便于后续Set判重
         *
         * @return
         */
        @Override
        public int hashCode() {
            int base = 64;
            return base << 1 + this.x << 1 + this.y << 2;
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
//        int[][] grid = {{0, 2}, {1, 3}};
        int[][] grid = {{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}};
        SwiminRisingWater swiminRisingWater = new SwiminRisingWater();
//        int time = swiminRisingWater.swimInWater(grid);
        int time = swiminRisingWater.swimInWater2(grid);
        System.out.println(time);
    }
}
