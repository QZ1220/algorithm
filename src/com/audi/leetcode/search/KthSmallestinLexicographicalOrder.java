package com.audi.leetcode.search;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/
 * <p>
 * 求字典序的第k小的元素
 * <p>
 * Input: n = 13, k = 2
 * Output: 10
 * Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
 * <p>
 * Input: n = 1, k = 1
 * Output: 1
 *
 * @author : wangquanzhou
 * @date : 2023/7/29 21:59
 */
public class KthSmallestinLexicographicalOrder {

    /**
     * 使用优先队列，但是这种方案在n比较大的时候，回超时
     *
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber2(int n, int k) {
        Queue<String> queue = new PriorityQueue<>(Comparator.comparing(String::valueOf).reversed());
        for (int i = 1; i <= n; i++) {
            queue.add(String.valueOf(i));
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return Integer.valueOf(queue.peek());
    }

    /**
     * 借助堆的思想建立一个大小为k的堆   实际提交发现内存超限
     *
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int n, int k) {

        String[] strs = new String[k];
        for (int i = 0; i < k; i++) {
            strs[i] = String.valueOf(i + 1);
        }

        // 构建大顶堆
        for (int i = k / 2 - 1; i >= 0; i--) {
            heapify(strs, k, i);
        }

        for (int i = k; i < n; i++) {
            if (String.valueOf(i+1).compareTo(strs[0]) > 0) {
                continue;
            }
            strs[0] = String.valueOf(i+1);
            heapify(strs, k, 0);
        }
        return Integer.valueOf(strs[0]);
    }

    /**
     * 构建一个大顶堆
     *
     * @param strs
     * @param n
     * @param i
     */
    private void heapify(String[] strs, int n, int i) {
        int parent = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && strs[left].compareTo(strs[parent]) > 0) {
            parent = left;
        }
        if (right < n && strs[right].compareTo(strs[parent]) > 0) {
            parent = right;
        }
        if (parent != i) {
            String tmp = strs[i];
            strs[i] = strs[parent];
            strs[parent] = tmp;
            heapify(strs, n, parent);
        }
    }


    public static void main(String[] args) {
        KthSmallestinLexicographicalOrder lexicographicalOrder = new KthSmallestinLexicographicalOrder();
        int number = lexicographicalOrder.findKthNumber(10, 3);
        System.out.println(number);
    }
}
