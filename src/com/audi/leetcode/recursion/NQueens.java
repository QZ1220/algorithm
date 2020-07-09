package com.audi.leetcode.recursion;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/n-queens/
 * <p>
 * N皇后问题
 *
 * @author: WangQuanzhou
 * @date: 2020/7/5 11:54
 */
public class NQueens {

    public List<List<String>> solveNQueens(int n) {

        int[][] mark = new int[n][n];

        List<String> item = new LinkedList<>();

        List<List<String>> result = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < n; j++) {
                stringBuilder.append(".");
            }
            item.add(stringBuilder.toString());
        }

        putQueen(0, n, item, result, mark);
        return result;

    }

    /**
     * 递归放置皇后
     *
     * @param k      当前放了已放置几个皇后（已处理到第几行）
     * @param n      总共n个皇后
     * @param item   放置皇后的一种解法
     * @param result 所有的皇后放置方法
     * @param mark   放置皇后，半年再放置皇后的标记数组
     */
    private void putQueen(int k, int n, List<String> item, List<List<String>> result, int[][] mark) {
        if (k == n) {
            result.add(item);
        }
        // 循环n列
        for (int i = 0; i < n; i++) {
            // （k,i）位置可以放置皇后
            if (mark[k][i] == 0) {
                // 暂存更新前的mark
                int[][] tempMark = mark.clone();
                String s = item.get(k);
                StringBuilder stringBuilder = new StringBuilder(s);
                stringBuilder.replace(i, i, "Q");
                item.set(k, stringBuilder.toString());

                updateMark(k, i, mark);
                putQueen(k + 1, n, item, result, mark);

                mark = tempMark;
                item.set(k, s);
            }
        }
    }


    /**
     * 将皇后放置在（x，y）位置时，更新相应的mark数组
     *
     * @param x    行坐标
     * @param y    列坐标
     * @param mark 标记数组，表示在（x，y）位置放置了皇后以后，相应哪些位置的不能再放置皇后
     */
    private void updateMark(int x, int y, int[][] mark) {
        // 两个方向数组  这两个方向数组只是标记了以（x，y）为中心的东南西北以及4个45度夹角的方向。
        // 要更新全部的mark数组，是依靠下面的两层循环来控制的
        final int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
        final int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

        mark[x][y] = 1;
        for (int i = 1; i < mark.length; i++) {
            for (int j = 0; j < mark.length; j++) {
                int newX = x + i * dx[j];
                int newY = y + i * dy[j];

                // 新的x和y位置是否超出mark数组边界
                if (newX >= 0 && newX < mark.length && newY >= 0 && newY < mark.length) {
                    // 更新mark数组
                    mark[newX][newY] = 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        System.out.println(nQueens.solveNQueens(8));


        int[][] mark = new int[2][2];
        System.out.println(mark[1][1]);
        int[][] mark2 = Arrays.copyOf(mark);
        mark[1][1] = 100;
        System.out.println(Arrays.toString(mark));
        System.out.println(Arrays.toString(mark2));


        int[] a1 = {1, 3};
        int[] a2 = a1.clone();

        a1[0] = 666;
        System.out.println(Arrays.toString(a1));   //[666, 3]
        System.out.println(Arrays.toString(a2));   //[1, 3]
    }
}
