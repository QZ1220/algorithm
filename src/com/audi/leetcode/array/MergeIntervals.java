package com.audi.leetcode.array;

import javafx.util.Pair;
import sun.awt.image.ImageWatched;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;

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

    /**
     * 按照子数组的第一个元素的大小进行排序
     *
     * @param arrs
     */
    private void bubbleSort(int[][] arrs) {
        for (int i = 0; i < arrs.length; i++) {
            for (int j = i + 1; j < arrs.length; j++) {
                if (arrs[i][0] > arrs[j][0]) {
                    int[] arr = arrs[i];
                    arrs[i] = arrs[j];
                    arrs[j] = arr;
                }
            }
        }
    }

    private List<List<Integer>> convert(int[][] arrs) {
        List<List<Integer>> list = new LinkedList<>();
        for (int i = 0; i < arrs.length; i++) {
            list.add(Arrays.asList(arrs[i][0], arrs[i][1]));
        }
        return list;
    }

    private int[][] reverseConvert(List<List<Integer>> list) {
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i][0] = list.get(i).get(0);
            res[i][1] = list.get(i).get(1);
        }
        return res;
    }

    public int[][] mergeSubArr2(int[][] arrs) {
        bubbleSort(arrs);
        List<List<Integer>> pairs = convert(arrs);
        Iterator<List<Integer>> iterator = pairs.iterator();
        List<Integer> front = iterator.next();
        while (iterator.hasNext()) {
            List<Integer> rear = iterator.next();
            // 如果前后区间没有重合部分
            if (rear.get(0) > front.get(1)) {
                front = rear;
                continue;
            } else {
                // 如果后区间完全被前区间包围
                if (rear.get(1) <= front.get(1)) {
                    iterator.remove();
                } else {
                    // 如果后区间与前区间相交
                    front.set(1, rear.get(1));
                    iterator.remove();
                }
            }
        }

        return reverseConvert(pairs);
    }

    public static void main(String[] args) {
        int[][] arrs = {{1, 2}, {-1, 5}, {4, 16}};
        MergeIntervals intervals = new MergeIntervals();
        int[][] res = intervals.mergeSubArr2(arrs);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }
}
