package com.audi.other;

/**
 * 搜索相关的题目
 */
public class SearchDemo {

    /**
     * 1代表陆地  0代表水
     * 求小岛的数量
     *
     * @param grid
     * @return
     */
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1,};

    char WATER = '0';
    char ISLAND = '1';

    public int numIslands(char[][] grid) {
        int H = grid.length;
        int L = grid[0].length;
        boolean[][] visitSet = new boolean[H][L];

        int count = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < L; j++) {
                if (grid[i][j] == WATER || visitSet[i][j]) {
                    continue;
                }
                dfs(visitSet, i, j, grid);
                count++;
            }
        }
        return count;
    }

    private void dfs(boolean[][] visitSet, int x, int y, char[][] grid) {
        if (x >= grid.length || x < 0 || y >= grid[0].length || y < 0 || visitSet[x][y] || grid[x][y] == WATER) {
            return;
        }
        visitSet[x][y] = true;
        for (int i = 0; i < dx.length; i++) {
                dfs(visitSet, x + dx[i], y + dy[i], grid);
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};

        SearchDemo searchDemo = new SearchDemo();
        int islands = searchDemo.numIslands(grid);
        System.out.println(islands);
    }
}
