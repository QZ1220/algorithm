package com.audi.leetcode.greed;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/last-stone-weight/
 * <p>
 * 给定一个数组，每个元素表示石头的质量，循环进行如下操作：
 * 取出其中最重的两个石头，进行重量相减
 * 如果相等则互相抵消
 * 如果不等，则将二者的差值重新放入数组中
 * <p>
 * 重复以上操作，直到数组为空，或者只剩下一个元素
 * <p>
 * 数组为空则返回0，否则返回剩余的元素
 *
 * @author : wangquanzhou
 * @date : 2023/8/16 22:44
 */
public class LastStoneWeight {

    /**
     * 借助优先级队列，实现元素的自动排序
     *
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        if (stones.length < 2) {
            return stones[0];
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(Integer::intValue).reversed());
        for (int stone : stones) {
            queue.add(stone);
        }
        while (!queue.isEmpty()) {
            if (queue.size() == 1) {
                break;
            }
            Integer big = queue.poll();
            Integer small = queue.poll();
            if (big == small) {
                continue;
            }
            queue.add(big - small);
        }


        return queue.isEmpty() ? 0 : queue.peek();
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(Integer::intValue).reversed());
        queue.add(2);
        queue.add(1);
        queue.add(3);
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
