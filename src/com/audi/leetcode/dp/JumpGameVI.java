package com.audi.leetcode.dp;


import javafx.util.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/jump-game-vi/
 * <p>
 * 题目给出一个整型数组（数字有正有负），从第0个元素开始，每次跳k步以内的任意步数，求跳跃经过的点的数字的最大和
 * <p>
 * 使用dp的思想：dp[i]=mac(dp[i-k],dp[i])
 * <p>
 * https://www.ixigua.com/6971822231257088519?logTag=563ba79da4fe6e1bdcb4
 *
 * @author: WangQuanzhou
 * @date: 2021-08-22 9:07 AM
 */
public class JumpGameVI {

    /**
     * 下面这样写的话，直接提交会超时
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxResult(int[] nums, int k) {
        if (nums.length < 2) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        // 注意这里不是从第0个位置开始，是从第一个位置开始
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Integer.MIN_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j < 0) {
                    break;
                }
                dp[i] = Math.max(dp[i - j] + nums[i], dp[i]);
            }
        }
        return dp[nums.length - 1];
    }

    /**
     * 优化代码逻辑，使用优先队列的思想
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxResult2(int[] nums, int k) {
        if (nums.length < 2) {
            return nums[0];
        }

        // 优先队列里的每个元素都是一个数组
        final PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        queue.offer(new int[]{0, nums[0]});

        // 注意这里不是从第0个位置开始，是从第一个位置开始
        for (int i = 1; i < nums.length; i++) {
            while (!queue.isEmpty() && queue.peek()[0] < i - k) {
                queue.poll();
            }
            if (i == nums.length - 1) {
                return queue.peek()[1] + nums[i];
            }
            queue.offer(new int[]{i, queue.peek()[1] + nums[i]});
        }
        return -1;
    }

    /**
     * 如果理解解法2中的数组的观念有点别扭的话，可以看下面的这个pair键值对的方式，就很容易理解了「但是性能会差一些」
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxResult3(int[] nums, int k) {
        if (nums.length < 2) {
            return nums[0];
        }

        // 优先队列里的每个元素都是一个数组
        final PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        queue.offer(new Pair<>(0, nums[0]));

        // 注意这里不是从第0个位置开始，是从第一个位置开始
        for (int i = 1; i < nums.length; i++) {
            while (!queue.isEmpty() && queue.peek().getKey() < i - k) {
                queue.poll();
            }
            if (i == nums.length - 1) {
                return queue.peek().getValue() + nums[i];
            }
            queue.offer(new Pair<>(i, queue.peek().getValue() + nums[i]));
        }
        return -1;
    }

    public static void main(String[] args) {
    }
}
