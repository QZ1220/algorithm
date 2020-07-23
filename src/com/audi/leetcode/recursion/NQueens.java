package com.audi.leetcode.recursion;


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
     * 递归放置皇后到第k行
     *
     * @param k      当前放了已放置几个皇后（已处理到第几行）
     * @param n      总共n个皇后
     * @param item   放置皇后的一种解法
     * @param result 所有的皇后放置方法
     * @param mark   放置皇后，半年再放置皇后的标记数组
     */
    private void putQueen(int k, int n, List<String> item, List<List<String>> result, int[][] mark) {
        if (k == n) {
            System.out.println("添加一个可行的放置方案：\n" + item);
            // 这里需要做深拷贝
            result.add(new LinkedList<>(item));
            return;
        }
        // 循环n列
        for (int i = 0; i < n; i++) {
            // 打印  复现循环过程
            System.out.println("k = " + k + " n = " + n + " i = " + i);
            // （k,i）位置可以放置皇后
            if (mark[k][i] == 0) {
                // 暂存更新前的mark  深拷贝
                int[][] tempMark = copy(mark);
                String s = item.get(k);
                StringBuilder stringBuilder = new StringBuilder(s);
                // 注意StringBuilder到replace方法到用法
                stringBuilder.replace(i, i + 1, "Q");
                item.set(k, stringBuilder.toString());
                // 更新mark矩阵
                updateMark(k, i, mark);
                // 递归放置皇后到第k+1行
                putQueen(k + 1, n, item, result, mark);
                // 还原mark矩阵到第k行时的情形
                mark = tempMark;
                // 还原第k行到item
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
            // 循环8个方向
            for (int j = 0; j < 8; j++) {
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


    public static void show1(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }


    /**
     * 二维数组的拷贝
     * <p>
     * 一维数组的拷贝直接使用.clone()方法就可以  二维数组需要拷贝各个一维数组
     *
     * @param src 源数组
     * @return 拷贝后的数组
     */
    private int[][] copy(int[][] src) {
        if (null == src || src.length == 0) {
            return null;
        }

        int length = src.length;
        int width = src[0].length;
        int[][] dst = new int[length][width];
        for (int i = 0; i < length; i++) {
            dst[i] = src[i].clone();
        }
        return dst;
    }


    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        List<List<String>> list = nQueens.solveNQueens(4);
        System.out.println(list.size());
        list.forEach(item -> System.out.println(item));
    }

}
