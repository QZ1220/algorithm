package com.audi.leetcode.array;

import javafx.util.Pair;

import javax.swing.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/merge-intervals/
 * <p>
 * 合并数组区间
 * <p>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 *
 * @author : wangquanzhou
 * @date : 2023/4/19 22:55
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if (null == intervals || intervals.length == 1) {
            return intervals;
        }

        Set<Integer> set = new HashSet<>();
        for (int[] arr : intervals) {
            put(set, arr);
        }

        int start = -100;
        boolean findStart = false;
        int end = 100;

        List<Pair<Integer, Integer>> list = new LinkedList<>();

        for (int i = -100; i <= 100; i++) {
            if (set.contains(i)) {
                if (!findStart) {
                    start = i;
                    findStart = true;
                }
            } else {
                if (findStart) {
                    end = i - 1;
                    list.add(new Pair<>(start, end));
                    findStart = false;
                }
            }
        }

        int[][] res = new int[list.size()][2];

        for (int i = 0; i < list.size(); i++) {
            res[i][0] = list.get(i).getKey();
            res[i][1] = list.get(i).getValue();
        }

        return res;
    }

    private int put(Set<Integer> set, int[] arr) {
        int min = 100;
        for (int i = arr[0]; i <= arr[1]; i++) {
            set.add(i);
            if (min > i) {
                min = i;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[][] arrs = {{1, 2}, {4, 5}};
        MergeIntervals intervals = new MergeIntervals();
        int[][] res = intervals.merge(arrs);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }
}
