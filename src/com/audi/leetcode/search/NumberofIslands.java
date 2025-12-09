package com.audi.leetcode.search;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/number-of-islands/
 * <p>
 * 求小岛数量
 * <p>
 * 可以使用深度优先搜索  也可以使用宽（广）度优先搜索
 * <p>
 * 从leetcode提交的结果来看  深度优先耗时略少于广度优先搜索
 *
 * @author WangQuanzhou
 * @date 2020-08-09
 */
public class NumberofIslands {

    /**
     * 1代表陆地
     */
    private static final char LAND = '1';

    /**
     * 0代表水域
     */
    private static final char WATER = '0';

    // 定义方向向量  在N皇后问题的求解时也使用了方向向量，只不过那里是8方向，这里只需要4方向
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {1, -1, 0, 0};


    public int numIslands(char[][] grid) {
        if (null == grid || grid.length == 0) {
            return 0;
        }

        // 行数
        int rows = grid.length;
        // 列数
        int columns = grid[0].length;

        int count = 0;

        // 采用bool数组存储访问历史
        boolean[][] mark = new boolean[rows][columns];

        // i代表行
        for (int i = 0; i < rows; i++) {
            // j代表的是列
            for (int j = 0; j < columns; j++) {
                // 如果当前位置是陆地 且没有被搜索过
                if (grid[i][j] == LAND && !mark[i][j]) {
//                    DFS(grid, mark, i, j, rows, columns);
                    BFS(grid, mark, i, j, rows, columns);
                    count += 1;
                }
            }
        }

        return count;
    }

    /**
     * 深度优先搜索
     * <p>
     * 按照上、下、左、右（这个取决于你创建的方向向量）的顺序进行递归的更新mark
     * <p>
     * 当探测到grid边缘 或者水域时 则停止搜索  继续下一次搜索（递归的过程）  直至本轮搜索结束 返回
     *
     * @param grid    初始的grid
     * @param mark    根据grid更新mark
     * @param x       横坐标
     * @param y       纵坐标
     * @param rows    grid数组的行数
     * @param columns grid数组的列数
     */
    private void DFS(char[][] grid, boolean[][] mark, int x, int y, int rows, int columns) {

        // 如果遇到海洋  或者 当前节点已经访问过  则终止递归
        if (grid[x][y] == WATER||mark[x][y]) {
            return;
        }

        // 更新mark矩阵
        mark[x][y] = true;

        for (int i = 0; i < 4; i++) {
            // 注意下面的对新坐标的计算 一定要新指定一个变量名称  否则下面的continue语句会遍历不到4个方向
            int newX = x + dx[i];
            int newY = y + dy[i];
            // 如果超出边界  直接返回
            if (newX < 0 || newY < 0 || newX >= rows || newY >= columns) {
                continue;
            }

            // 如果mark矩阵的newX，newY位置未被便利过  且  grid矩阵的该位置是陆地  那么就进行进行搜索
            // 其实下面的这个if可以省略，因为递归函数的入口位置已经保证了下面的if条件是满足的，无需再次判断
            if (!mark[newX][newY] && grid[newX][newY] == LAND) {
                DFS(grid, mark, newX, newY, rows, columns);
            }
        }
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
    private void BFS(char[][] grid, boolean[][] mark, int x, int y, int rows, int columns) {
        if (mark[x][y]) {
            return;
        }

        // 使用队列保存当前节点的上下左右节点
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

        // 更新mark矩阵
        mark[x][y] = true;

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
                if (!mark[newX][newY] && grid[newX][newY] == LAND) {
                    // 新元素入队
                    queue.offer(new Pair<>(newX, newY));
                    // 更新mark矩阵
                    mark[newX][newY] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = new char[4][5];

//        String[] str = {"11110", "11010", "11000", "00000"};
        String[] str = {"11000", "11000", "00100", "00011"};

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = str[i].charAt(j);
            }
        }


        NumberofIslands numberofIslands = new NumberofIslands();
        System.out.println(numberofIslands.numIslands(grid));
//        System.out.println(numberofIslands.numIslands(new char[0][0]));
    }
}
