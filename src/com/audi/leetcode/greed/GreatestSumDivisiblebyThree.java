package com.audi.leetcode.greed;


/**
 * https://leetcode.com/problems/greatest-sum-divisible-by-three/
 * <p>
 * 题目给了一个正整数数组，求这些数组元素能组成的最大累加sum，并且这个sum%3==0
 * <p>
 * 参考
 * https://leetcode.com/problems/greatest-sum-divisible-by-three/
 * 使用状态机来实现
 *
 * @author: WangQuanzhou
 * @date: 2021-08-15 9:19 AM
 */
public class GreatestSumDivisiblebyThree {

    public int maxSumDivThree(int[] nums) {
        // state[0]、state[1]、state[2]表示余数为0、1、2的num和
        int[] state = {0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        int a, b, c;
        for (int num : nums) {
            // 如果num刚好是3的倍数，那么state0、1、2加上num，均不影响原来的state状态机
            if (num % 3 == 0) {
                state[0] += num;
                state[1] += num;
                state[2] += num;
                continue;
            } else if (num % 3 == 1) {
                // 2状态机加上（num % 3 == 1）的num，会转换为为0状态，其余类推
                a = Math.max(state[2] + num, state[0]);
                b = Math.max(state[0] + num, state[1]);
                c = Math.max(state[1] + num, state[2]);
            } else {
                a = Math.max(state[1] + num, state[0]);
                b = Math.max(state[2] + num, state[1]);
                c = Math.max(state[0] + num, state[2]);
            }
            state[0] = a;
            state[1] = b;
            state[2] = c;
        }
        return state[0];
    }

    public static void main(String[] args) {

        int[] nums = {3, 6, 14, 1, 8};
        GreatestSumDivisiblebyThree divisiblebyThree = new GreatestSumDivisiblebyThree();
        System.out.println(divisiblebyThree.maxSumDivThree(nums));
    }
}
