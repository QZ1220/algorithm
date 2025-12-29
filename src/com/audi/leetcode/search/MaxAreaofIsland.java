package com.audi.leetcode.search;


import javafx.util.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/max-area-of-island/
 * <p>
 * 题目和number-of-islands很相似，只是这里计算的结果是岛屿的最大面积，
 * 其实就是每次在遍历岛屿的过程中记录一下岛屿的大小
 * <p>
 * 如果不存在岛屿返回0
 *
 * @author: WangQuanzhou
 * @date: 2021-09-09 7:34 AM
 */
public class MaxAreaofIsland {

    /**
     * 1代表陆地
     */
    private static final int LAND = 1;

    /**
     * 0代表水域
     */
    private static final int WATER = 0;
    private static int maxArea = 0;

    // 定义方向向量  在N皇后问题的求解时也使用了方向向量，只不过那里是8方向，这里只需要4方向
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {1, -1, 0, 0};

    public int maxAreaOfIsland(int[][] grid) {
        // 判空
        if (null == grid || grid.length == 0) {
            return maxArea;
        }

        // 行
        int row = grid.length;
        // 列
        int column = grid[0].length;

//        System.out.println("row = " + row);
//        System.out.println("column = " + column);

        // 使用set记录搜索过的位置
        Set<Pair<Integer, Integer>> visitSet = new HashSet<>();

        int max = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (!visitSet.contains(new Pair<>(i, j)) && grid[i][j] == LAND) {
                    // 开始搜索
//                    DFS(visitSet, grid, i, j, row, column);
                    BFS(visitSet, grid, i, j, row, column);
                    if (maxArea > max) {
                        max = maxArea;
                    }
                    // 注意  这里maxArea在每次搜索完一个岛屿以后都要清零
                    maxArea = 0;
                }
            }
        }
        return max;
    }

    /**
     * 深度优先搜索
     *
     * @param visitSet
     * @param grid
     * @param x
     * @param y
     * @param row
     * @param column
     */
    private void DFS(Set<Pair<Integer, Integer>> visitSet, int[][] grid, int x, int y, int row, int column) {
        if (visitSet.contains(new Pair<>(x, y)) || grid[x][y] == WATER) {
            return;
        }

        maxArea++;
        visitSet.add(new Pair<>(x, y));
//        System.out.println("maxArea = " + maxArea);

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (newX < 0 || newY < 0 || newX >= row || newY >= column) {
                continue;
            }

            if (!visitSet.contains(new Pair<>(newX, newY)) && grid[newX][newY] == LAND) {
                // 程序调试的时候可以使用如下的辅助打印  查看程序执行的分支
//                System.out.println("newX = " + newX + " newY = " + newY);
//                System.out.println();
                DFS(visitSet, grid, newX, newY, row, column);
            }
        }
    }

    /**
     * 宽度优先搜索
     *
     * @param visitSet
     * @param grid
     * @param x
     * @param y
     * @param row
     * @param column
     */
    private void BFS(Set<Pair<Integer, Integer>> visitSet, int[][] grid, int x, int y, int row, int column) {
        if (visitSet.contains(new Pair<>(x, y)) || grid[x][y] == WATER) {
            return;
        }

        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(x, y));

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> head = queue.poll();
            maxArea++;
            visitSet.add(new Pair<>(x, y));
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX < 0 || newY < 0 || newX >= row || newY >= column) {
                    continue;
                }

                if (!visitSet.contains(new Pair<>(newX, newY)) && grid[newX][newY] == LAND) {
                    // 程序调试的时候可以使用如下的辅助打印  查看程序执行的分支
//                    BFS(visitSet, grid, newX, newY, row, column);
                    queue.offer(new Pair<>(newX, newY));
                }
            }

        }
    }

    public static void main(String[] args) {
//        int[][] grid =
//                {
//                        {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                        {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
//                        {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
//                        {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
//                        {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
//                        {1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                        {1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
//                };

        int[][] grid = {{0, 0, 0, 0, 0, 0, 0, 0}};

        MaxAreaofIsland maxAreaofIsland = new MaxAreaofIsland();
        System.out.println(maxAreaofIsland.maxAreaOfIsland(grid));

    }
}
