package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/can-i-win/
 * <p>
 * 给定的maxChoosableInteger不能重复使用
 * <p>
 * 博弈
 *
 * @author WangQuanzhou
 * @date 2020-04-23
 */
public class CanIWin {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        // desiredTotal在maxChoosableInteger  第一个玩家直接就赢了
        if (desiredTotal <= maxChoosableInteger) {
            return true;
        }


        return false;
    }

    public static void main(String[] args) {

    }
}
