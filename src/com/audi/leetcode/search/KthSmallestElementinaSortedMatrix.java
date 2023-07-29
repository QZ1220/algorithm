package com.audi.leetcode.search;

import java.util.Comparator;
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
        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparing(Integer::intValue).reversed());
        int H = matrix.length;
        int L = matrix[0].length;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < L; j++) {
                queue.offer(matrix[i][j]);
                if (queue.size() > k) {
                    queue.poll();
                }
            }
        }

        return queue.peek();
    }

    /**
     * 使用二分查找的思想解题
     * <p>
     * 解题思路：
     * <p>
     * 使用二分查找的思想，设定搜索的范围是从矩阵最小值到最大值。最小值是矩阵的左上角元素，最大值是矩阵的右下角元素。
     * <p>
     * 在每一次的二分查找中，计算矩阵中小于等于当前中间值的元素个数。具体地，从矩阵的左下角开始遍历，如果当前元素小于等于中间值，
     * 就将当前所在列的元素个数加到计数器中，并向右移动一列；否则，向上移动一行。
     * <p>
     * 根据计数器的值与k的关系，缩小搜索范围。
     * 如果计数器小于k，说明第k小的元素位于当前范围的右侧，将搜索范围缩小到大于中间值的部分；
     * 如果计数器大于等于k，说明第k小的元素位于当前范围的左侧，将搜索范围缩小到小于等于中间值的部分。
     * <p>
     * 当搜索范围缩小到只有一个元素时，即左右指针相遇时，这个元素就是第k小的元素。
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0], right = matrix[n - 1][n - 1];

        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countLessOrEqual(matrix, mid, n);

            if (count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private int countLessOrEqual(int[][] matrix, int target, int n) {
        int count = 0;
        int row = n - 1, col = 0; // 从左下角开始遍历

        while (row >= 0 && col < n) {
            if (matrix[row][col] <= target) {
                count += row + 1;
                col++;
            } else {
                row--;
            }
        }

        return count;
    }


    public static void main(String[] args) {
        int[][] nums = {{-5, -4}, {-5, -4}};
        KthSmallestElementinaSortedMatrix sortedMatrix = new KthSmallestElementinaSortedMatrix();
        System.out.println(sortedMatrix.kthSmallest2(nums, 2));
    }
}
