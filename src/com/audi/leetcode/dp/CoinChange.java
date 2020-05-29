package com.audi.leetcode.dp;


/**
 * https://leetcode.com/problems/coin-change/
 * <p>
 * https://www.cnblogs.com/grandyang/p/5138186.html
 * <p>
 * 组成指定面值所使用的最少的钞票张数
 * <p>
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * @author: WangQuanzhou
 * @date: 2020/5/16 21:53
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        if (amount < 1) {
            return 0;
        }


    }


    public static void main(String[] args) {

    }
}
