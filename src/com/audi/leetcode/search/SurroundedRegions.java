package com.audi.leetcode.search;


import javafx.util.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/surrounded-regions/
 * <p>
 * 给出一个矩阵 矩阵的每个元素都是X或者O，将所有被X包围的O替换成X
 * <p>
 * 本质上可以使用宽度搜索解决
 *
 * @author: WangQuanzhou
 * @date: 2020/10/27 23:05
 */
public class SurroundedRegions {

    private static final char X = 'X';
    private static final char O = 'O';
    private static final char N = 'N';

    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {1, -1, 0, 0};

    /**
     * Runtime: 7 ms, faster than 12.49% of Java online submissions for Surrounded Regions.
     * Memory Usage: 40.2 MB, less than 13.57% of Java online submissions for Surrounded Regions.
     *
     * @param board
     */
    public void solve(char[][] board) {
        // 如果为空||只有一行||只有一列  都不能形成包围
        if (board == null || board.length < 2 || board[0].length < 2) {
            return;
        }

        int height = board.length;
        int width = board[0].length;

        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        Set<Pair<Integer, Integer>> visitSet = new HashSet<>();

        // 首位行的O存入queue
        for (int i = 0; i < width; i++) {
            if (board[0][i] == O) {
                queue.add(new Pair<>(0, i));
            }
            if (board[height - 1][i] == O) {
                queue.add(new Pair<>(height - 1, i));
            }
        }

        // 首位列的O存入queue
        for (int i = 1; i < height - 1; i++) {
            if (board[i][0] == O) {
                queue.add(new Pair<>(i, 0));
            }
            if (board[i][width - 1] == O) {
                queue.add(new Pair<>(i, width - 1));
            }
        }

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> top = queue.poll();
            visitSet.add(top);
            int x = top.getKey();
            int y = top.getValue();
            board[x][y] = N;

            // 四个方向遍历  如果元素等于O 就将其加入队列  直至队列为空
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                Pair<Integer, Integer> newPair = new Pair<>(newX, newY);
                if (newX < 0 || newX >= height || newY < 0 || newY >= width || visitSet.contains(newPair) || X == board[newX][newY]) {
                    continue;
                }

                board[x][y] = N;
                queue.add(newPair);
            }
        }

        //将队列剩余的O全部替换成X  所有的N全部替换成O  即可实现题目的要求
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (O == board[i][j]) {
                    board[i][j] = X;
                    continue;
                }
                if (N == board[i][j]) {
                    board[i][j] = O;
                }
            }
        }
    }

    public static void main(String[] args) {
        SurroundedRegions regions = new SurroundedRegions();
        char[][] board = {{X, X, X, X}, {X, O, O, X}, {X, X, O, X}, {X, O, X, X}};
        regions.solve(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
