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
    public int findKthNumber(int n, int k) {
        Queue<String> queue = new PriorityQueue<>(Comparator.comparing(String::valueOf).reversed());
        for (int i = 1; i <= n; i++) {
            queue.add(String.valueOf(i));
            if (queue.size()>k){
                queue.poll();
            }
        }
        return Integer.valueOf(queue.peek());
    }

    public static void main(String[] args) {
        Queue<String> queue = new PriorityQueue<>();
        queue.add("11");
        queue.add("10");
        queue.add("1");

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

        KthSmallestinLexicographicalOrder lexicographicalOrder = new KthSmallestinLexicographicalOrder();
        System.out.println(lexicographicalOrder.findKthNumber(13, 2));
        System.out.println(lexicographicalOrder.findKthNumber(1, 1));
    }
}
