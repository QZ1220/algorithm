package com.audi.leetcode.search;

/**
 * https://leetcode.com/problems/number-of-islands/
 * <p>
 * 求小岛数量
 * <p>
 * 可以使用深度优先搜索  也可以使用宽度优先搜索
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
        if (null == grid) {
            return 0;
        }

        // 行数
        int rows = grid.length;
        // 列数
        int columns = grid[0].length;

        int count = 0;

        char[][] mark = new char[rows][columns];

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
                if (grid[i][j] == LAND && mark[i][j] == WATER) {
                    DFS(grid, mark, i, j, rows, columns);
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
    private void DFS(char[][] grid, char[][] mark, int x, int y, int rows, int columns) {

        if (mark[x][y] == LAND) {
            return;
        }

        // 更新mark矩阵
        mark[x][y] = LAND;

        for (int i = 0; i < 4; i++) {
            x = x + dx[i];
            y = y + dy[i];
            // 如果超出边界  直接返回
            if (x < 0 || y < 0 || x >= rows || y >= columns || mark[x][y] == LAND) {
                continue;
            }

            // 如果mark矩阵的x，y位置未被便利过  且  grid矩阵的该位置是陆地  那么就进行进行搜索
            if (mark[x][y] == WATER && grid[x][y] == LAND) {
                DFS(grid, mark, x, y, rows, columns);
            }
        }
    }

    private void BFS(char[][] grid, char[][] mark, int x, int y) {

    }

    public static void main(String[] args) {
        char[][] grid = new char[4][5];

        String[] str = {"11110", "11010", "11000", "00000"};

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = str[i].charAt(j);
            }
        }


        NumberofIslands numberofIslands = new NumberofIslands();
        System.out.println(numberofIslands.numIslands(grid));

    }
}
