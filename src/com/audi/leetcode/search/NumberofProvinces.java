package com.audi.leetcode.search;


import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/number-of-provinces/
 * <p>
 * 题目给出了一个二维矩阵，矩阵由0、1组成，如果matrix[i][j]=1，它的上下左右位置都是0，当然边界也是0，求图中符合这种情况的孤立区域都多少个
 * <p>
 * 第一印象，这题和https://leetcode.com/problems/number-of-islands/  一模一样
 * <p>
 * 理论上，使用DFS、BFS的思想都可以求出答案
 *
 * @author: WangQuanzhou
 * @date: 2021-09-08 6:20 PM
 */
public class NumberofProvinces {

    /**
     * 1代表陆地
     */
    private static final int LAND = 1;

    /**
     * 0代表水域
     */
    private static final int WATER = 0;

    // 定义方向向量  在N皇后问题的求解时也使用了方向向量，只不过那里是8方向，这里只需要4方向
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {1, -1, 0, 0};

    public int findCircleNum(int[][] isConnected) {
        if (null == isConnected || isConnected.length == 0) {
            return 0;
        }

        // 行数
        int rows = isConnected.length;
        // 列数
        int columns = isConnected[0].length;

        int count = 0;

        int[][] mark = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mark[i][j] = WATER;
            }
        }


        // i代表行
        for (int i = 0; i < rows; i++) {
            // j代表的是列
            for (int j = 0; j < columns; j++) {
                // 如果当前位置是陆地 且没有被搜索过
                if (isConnected[i][j] == LAND && mark[i][j] == WATER) {
//                    DFS(grid, mark, i, j, rows, columns);
                    BFS(isConnected, mark, i, j, rows, columns);
                    count += 1;
                }
            }
        }

        return count;
    }

    /**
     * 宽度优先搜索
     * <p>
     * 每遍历一个节点，使用队列保存当前位置的上下左右节点，然后循环出队  直至队列为空为止
     * <p>
     * 注意这不是一个递归的过程  而是一个循环出队的过程
     * <p>
     * 循环的过程中需要更新mark矩阵
     *
     * @param grid    初始的grid
     * @param mark    根据grid更新mark
     * @param x       横坐标
     * @param y       纵坐标
     * @param rows    grid数组的行数
     * @param columns grid数组的列数
     */
    private void BFS(int[][] grid, int[][] mark, int x, int y, int rows, int columns) {
        if (mark[x][y] == LAND) {
            return;
        }

        // 使用队列保存当前节点的上下左右节点
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

        // 更新mark矩阵
        mark[x][y] = LAND;

        queue.add(new Pair<>(x, y));

        while (!queue.isEmpty()) {
            // 出队
            Pair<Integer, Integer> pair = queue.poll();

            for (int i = 0; i < 4; i++) {
                // 注意下面的对新坐标的计算 一定要新指定一个变量名称  否则下面的continue语句会遍历不到4个方向
                int newX = pair.getKey() + dx[i];
                int newY = pair.getValue() + dy[i];
                // 如果超出边界  直接返回
                if (newX < 0 || newY < 0 || newX >= rows || newY >= columns) {
                    continue;
                }

                // 如果mark矩阵的newX，newY位置未被便利过  且  grid矩阵的该位置是陆地  那么就进行进行搜索
                if (mark[newX][newY] == WATER && grid[newX][newY] == LAND) {
                    // 新元素入队
                    queue.offer(new Pair<>(newX, newY));
                    // 更新mark矩阵
                    mark[newX][newY] = LAND;
                }
            }
        }
    }

    public static void main(String[] args) {
        NumberofProvinces provinces = new NumberofProvinces();
//        int[][] grids = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] grids = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println(provinces.findCircleNum(grids));
    }
}
