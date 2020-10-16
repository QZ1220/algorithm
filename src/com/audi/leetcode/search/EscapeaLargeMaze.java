package com.audi.leetcode.search;


import javafx.util.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/escape-a-large-maze/
 * <p>
 * <p>
 * In a 1 million by 1 million grid, the coordinates of each grid square are (x, y) with 0 <= x, y < 10^6.
 * <p>
 * We start at the source square and want to reach the target square.  Each move, we can walk to a 4-directionally adjacent square in the grid that isn't in the given list of blocked squares.
 * <p>
 * Return true if and only if it is possible to reach the target square through a sequence of moves.
 * <p>
 * <p>
 * 这题和https://leetcode.com/problems/number-of-islands/类似  但是这题的搜索数量级要大一些
 * <p>
 * 如果直接粗暴的BFS的话，我本机直接OOM了
 *
 * @author: WangQuanzhou
 * @date: 2020/10/16 7:55
 */
public class EscapeaLargeMaze {

    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {1, -1, 0, 0};

    /**
     * @param blocked
     * @param source
     * @param target
     * @return
     */
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        int N = 1000000;
        int[][] maze = new int[N][N];

        for (int[] ints : blocked) {
            int x = ints[0];
            int y = ints[0];
            maze[x][y] = 1;
        }

        Set<Pair<Integer, Integer>> visitSet = new HashSet<>();
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

        Pair<Integer, Integer> start = new Pair<>(source[0], source[1]);
        queue.add(start);
        visitSet.add(start);

        while (!queue.isEmpty()) {

            Pair<Integer, Integer> top = queue.poll();
            Integer x = top.getKey();
            Integer y = top.getValue();

            for (int i = 0; i < 4; i++) {
                int newY = y + dy[i];
                int newX = x + dx[i];
                Pair<Integer, Integer> pair = new Pair<>(newX, newY);
                if (newX < 0 || newY < 0 || newX >= N || newY >= N || visitSet.contains(pair)) {
                    // 直接忽略
                    continue;
                }
                if (newX == target[0] && newY == target[1]) {
                    return Boolean.TRUE;
                }
                queue.add(pair);
                visitSet.add(pair);
            }
        }
        return Boolean.FALSE;
    }

    public static void main(String[] args) {
        int[][] blocked = {{0, 1}, {1, 0}};
        int[] source = {0, 0};
        int[] target = {0, 2};
        EscapeaLargeMaze maze = new EscapeaLargeMaze();
        maze.isEscapePossible(blocked, source, target);
    }
}
