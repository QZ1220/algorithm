package com.audi.leetcode.search;


import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/escape-a-large-maze/
 * <p>
 * <p>
 * In a 1 million by 1 million grid, the coordinates of each grid square are (x, y) with 0 <= x, y < 10^6.
 * <p>
 * We start at the source square and want to reach the target square.
 * Each move, we can walk to a 4-directionally adjacent square in the grid that isn't in the given list of blocked squares.
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
     * 直接BFS  开发机直接OOM
     *
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


    /**
     * BFS需要进行剪枝操作
     *
     * @param blocked
     * @param source
     * @param target
     * @return
     */
    public boolean isEscapePossible2(int[][] blocked, int[] source, int[] target) {
        // 一个点是围不住任何点的
        if (blocked.length < 2) {
            return Boolean.TRUE;
        }

        // 分别从起点和终点出发  看看能否走出包围圈  或者即便在包围圈内，但是起点终点都在包围圈内也行
        return walk(blocked, source, target) && walk(blocked, target, source);
    }

    /**
     * 从source点出发，看能不能被blocked围成的区域封闭住，或者能否直接走到target
     * <p>
     * 参考https://leetcode.jp/leetcode-1036-escape-a-large-maze-%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/
     *
     * @param blocked
     * @param source
     * @return
     */
    private Boolean walk(int[][] blocked, int[] source, int[] target) {
        int N = 1000000;

        Set<Pair<Integer, Integer>> visitSet = new HashSet<>();
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

        Pair<Integer, Integer> start = new Pair<>(source[0], source[1]);
        queue.add(start);
        visitSet.add(start);

        // 转换成set  方便后续判断
        Set<Pair> blockedSet = Arrays.stream(blocked).map(item -> new Pair(item[0], item[1])).collect(Collectors.toSet());

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> top = queue.poll();
            Integer x = top.getKey();
            Integer y = top.getValue();

            for (int i = 0; i < 4; i++) {
                int newY = y + dy[i];
                int newX = x + dx[i];
                Pair<Integer, Integer> pair = new Pair<>(newX, newY);
                if (newX < 0 || newY < 0 || newX >= N || newY >= N || visitSet.contains(pair) || blockedSet.contains(pair)) {
                    // 直接忽略
                    continue;
                }
                queue.add(pair);
                visitSet.add(pair);
                // 要想围住N个点  至少需要N+1个点  即要想封住queue内的点，至少需要queue.size() + 1 个点
                if (queue.size() >= blocked.length || (newX == target[0] && newY == target[1])) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }


    public static void main(String[] args) {
//        int[][] blocked = {{0, 1}, {1, 0}};
//        int[] source = {0, 0};
//        int[] target = {0, 2};
//        int[][] blocked = {};
//        int[] source = {0, 0};
//        int[] target = {999999, 999999};

        int[][] blocked = {{0, 3}, {1, 0}, {1, 1}, {1, 2}, {1, 3}};
        int[] source = {0, 0};
        int[] target = {0, 2};
        EscapeaLargeMaze maze = new EscapeaLargeMaze();
        if (maze.isEscapePossible2(blocked, source, target)) {
            System.out.println("success");
        } else {
            System.out.println("failed");
        }
    }
}
