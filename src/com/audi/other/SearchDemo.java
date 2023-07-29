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

    /**
     * 矩阵的元素每一行都递增，每一列也是递增
     * 编写一个高效的算法在矩阵中寻找指定的target值
     *
     * @param matrix
     * @param target 存在返回true
     * @return
     */
    boolean flag = false;

    public boolean searchMatrix(int[][] matrix, int target) {
        int H = matrix.length;
        int L = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[H - 1][L - 1]) {
            return false;
        }
        // 从矩阵的左下角开始搜索，左下角的元素为该行的最小值，该列的最大值
        find(matrix, target, H - 1, 0);
        return flag;
    }

    private void find(int[][] matrix, int target, int x, int y) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || flag) {
            return;
        }

        if (matrix[x][y] == target) {
            flag = true;
            return;
        }
        // 如果target小于当前位置元素，则说明应该向上移动一行
        if (matrix[x][y] > target) {
            find(matrix, target, x - 1, y);
        } else {
            // 如果target大于当前位置元素，则说明应该向右移动一列
            find(matrix, target, x, y + 1);
        }
    }


    /**
     * 给定一个n*n的二维矩阵，矩阵的每一行都是从小到大排列，每一列也都是从小到大排列
     * 求矩阵的第k小的元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int kthSmallest(int[][] nums, int k) {
        int n = nums.length;
        int left = nums[0][0];
        int right = nums[n - 1][n - 1];
        while (left < right) {
            /**
             * (left + right) / 2 和 left + (right - left) / 2 在大多数情况下是等价的，都可以用来计算两个数的中间值。
             *
             * 然而，在涉及整数除法时，两者有微小的差异。如果 (right - left) 是奇数，那么使用 left + (right - left) / 2 计算中间值会向下取整，
             * 而 (left + right) / 2 会向上取整。这个差异可能会在一些特定情况下引起问题。
             *
             * 为了保证结果的准确性和一致性，在大多数情况下，我们建议使用 left + (right - left) / 2 来计算中间值。
             * 这样可以确保得到正确的结果，并防止潜在的问题出现。
             *
             *
             *
             *
             * 对于有符号数，left + (right - left) / 2 和 left + (right - left) >> 1 在大多数情况下是等价的。由于右移操作保留符号位，
             * 所以可以正确处理有符号数的取整操作。
             *
             * 然而，需要注意的是如果 left 和 right 是负数，并且 (right - left) 是奇数，使用右移操作 (right - left) >> 1 可能导致舍入错误。
             * 由于右移操作保留符号位，这的舍入错误可能会使得最终结果比预期的稍微偏小。
             *
             * 为了避免这种问题，在处理有符号数时，建议使用 left + (right - left) / 2 来计算中间值，以确保得到正确的结果。
             * */
//            int mid = left + (right - left) / 2;
            int mid = left + (right - left) >> 1;
            int mid222 = (left + right) / 2;
            System.out.println("mid = " + mid + " ---   mid222 = " + mid222);
            if (countLessOrEqual(nums, mid) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int countLessOrEqual(int[][] nums, int target) {
        int count = 0;
        int H = nums.length - 1;
        int L = 0;
        // 行方向递减，列方向递增
        while (H >= 0 && L < nums[0].length) {
            if (nums[H][L] <= target) {
                count = count + H + 1;
                L++;
            } else {
                H--;
            }
        }
        return count;
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

        int[][] nums = {{-5, -4}, {-5, -4}};
        SearchDemo searchDemo = new SearchDemo();
        System.out.println(searchDemo.kthSmallest(nums, 2));

    }
}
