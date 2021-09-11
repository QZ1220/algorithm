package com.audi.leetcode.search;


import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/count-sub-islands/
 * <p>
 * 题目给出了两个0、1矩阵，grid1，grid2，
 * 求grid2中的岛屿是grid1的子岛屿的个数
 * <p>
 * 这个题可以在number-of-islands的基础上稍微改一改就行
 * <p>
 * 思路：
 * 先分别求出grid1，grid2的岛屿，结果分别放入list1，list2中，然后拿出list2中的元素，看看这个元素是在list1中存在。
 *
 * @author: WangQuanzhou
 * @date: 2021-09-10 7:21 AM
 */
public class CountSubIslands {

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

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int count = 0;

        Set<Set<Pair<Integer, Integer>>> island1 = findIsland(grid1);
        Set<Set<Pair<Integer, Integer>>> island2 = findIsland(grid2);

        if ((null == island2 || island2.size() < 1) || (null == island1 || island1.size() < 1)) {
            return count;
        }

        for (Set<Pair<Integer, Integer>> pairSet : island2) {
            // fixme 这里这么搞不好判断两个set的元素是否相等，此方法作废，更好的解决方案是先遍历grid2，每遍历grid2的一块陆地时，都判断其在grid1中是不是陆地，不是就直接return
            if (island1.contains(pairSet)) {
                count++;
            }
        }

        return count;
    }

    /**
     * 使用深搜 来搜索岛屿
     *
     * @param grid
     * @return
     */
    private Set<Set<Pair<Integer, Integer>>> findIsland(int[][] grid) {

        if (null == grid || grid.length < 1) {
            return null;
        }
        int row = grid.length;
        int column = grid[0].length;

        Set<Set<Pair<Integer, Integer>>> resSet = new HashSet<>();
        Set<Pair<Integer, Integer>> visitSet = new HashSet<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == LAND) {
                    Set<Pair<Integer, Integer>> islandSet = new HashSet<>();
                    DFS(visitSet, islandSet, grid, row, column, i, j);
                    if (islandSet.size() > 0) {
                        resSet.add(islandSet);
                    }
                }
            }
        }
        return resSet;
    }

    private void DFS(Set<Pair<Integer, Integer>> visitSet, Set<Pair<Integer, Integer>> islandSet, int[][] grid, int row, int column, int x, int y) {
        if (visitSet.contains(new Pair<>(x, y))) {
            return;
        }

        visitSet.add(new Pair<>(x, y));
        islandSet.add(new Pair<>(x, y));

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            // 防止数组越界
            if (newX < 0 || newY < 0 || newX >= row || newY >= column) {
                continue;
            }
            if (grid[newX][newY] == LAND) {
                DFS(visitSet, islandSet, grid, row, column, newX, newY);
            }
        }
    }

    public static void main(String[] args) {

        int[][] grid1 = {{1, 1, 1, 0, 0}, {0, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 1, 1}};
        int[][] grid2 = {{1, 1, 1, 0, 0}, {0, 0, 1, 1, 1}, {0, 1, 0, 0, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}};
        CountSubIslands countSubIslands = new CountSubIslands();
        int count = countSubIslands.countSubIslands(grid1, grid2);
        System.out.println(count);

    }
}
