package com.audi.other;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

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

    /**
     * 接雨水II
     * <p>
     * 三维空间内的接雨水问题
     * <p>
     * 使用广搜，考虑heightMap外部水位不断升高，往heightMap中灌入的过程，就有点类似于搜索的过程
     *
     * @param heightMap
     * @return
     */
    public int trapRainWater(int[][] heightMap) {

        int waterCnt = 0;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1,};
        int H = heightMap.length;
        int L = heightMap[0].length;

        boolean[][] visitSet = new boolean[H][L];
        Queue<Item> queue = new PriorityQueue<>(Comparator.comparing(v -> v.h));

        // 将首尾行放入有限队列
        for (int i = 1; i < L - 1; i++) {
            queue.add(new Item(0, i, heightMap[0][i]));
            visitSet[0][i] = true;
            queue.add(new Item(H - 1, i, heightMap[H - 1][i]));
            visitSet[H - 1][i] = true;
        }
        // 将首尾列放入优先级队列
        for (int i = 1; i < H - 1; i++) {
            queue.add(new Item(i, 0, heightMap[i][0]));
            visitSet[i][0] = true;
            queue.add(new Item(i, L - 1, heightMap[i][L - 1]));
            visitSet[i][L - 1] = true;
        }
        // 由于四个角不能作为起点，直接默认已经访问过
        visitSet[0][0] = true;
        visitSet[0][L - 1] = true;
        visitSet[H - 1][0] = true;
        visitSet[H - 1][L - 1] = true;

        while (!queue.isEmpty()) {
            Item head = queue.poll();
            int x = head.x;
            int y = head.y;
            int h = head.h;

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX < 0 || newX >= H || newY < 0 || newY >= L || visitSet[newX][newY]) {
                    continue;
                }
                if (h > heightMap[newX][newY]) {
                    waterCnt = waterCnt + h - heightMap[newX][newY];
                    heightMap[newX][newY] = h;
                }
                visitSet[newX][newY] = true;
                queue.add(new Item(newX, newY, heightMap[newX][newY]));
            }
        }
        return waterCnt;
    }

    class Item {
        int x;
        int y;
        int h;

        public Item(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }

    public static void main(String[] args) {
//        char[][] grid = {
//                {'1', '1', '0', '0', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '1', '0', '0'},
//                {'0', '0', '0', '1', '1'}};
//
//        SearchDemo searchDemo = new SearchDemo();
//        int islands = searchDemo.numIslands(grid);
//        System.out.println(islands);

    }
}
