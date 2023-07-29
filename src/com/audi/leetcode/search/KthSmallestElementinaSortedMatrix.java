package com.audi.leetcode.search;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 *
 * @author : wangquanzhou
 * @date : 2023/7/29 12:16
 */
public class KthSmallestElementinaSortedMatrix {

    /**
     * 使用优先队列的思想解题
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        Queue<Integer> queue = new PriorityQueue<>();
        int H = matrix.length;
        int L = matrix[0].length;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < L; j++) {
                queue.offer(matrix[i][j]);
            }
        }
        for (int i = 0; i < k - 1; i++) {
            queue.poll();
        }
        return queue.peek();
    }


    public static void main(String[] args) {

    }
}
