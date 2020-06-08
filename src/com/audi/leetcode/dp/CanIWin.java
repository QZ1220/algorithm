package com.audi.leetcode.dp;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Stream;

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

        // 如果maxChoosableInteger的总和都小于desiredTotal，则说明都不能赢（记住数字是不可以重复使用的）
//        int sum = 1;
//        for (int i = 2; i <= maxChoosableInteger; i++) {
//            sum += i;
//        }

        // 也可以使用等差数列求和公式
        if ((maxChoosableInteger * (maxChoosableInteger + 1) / 2) < desiredTotal) {
            return false;
        }


        return false;
    }

    public static void main(String[] args) {
        Stream<BigInteger> bigIntStream = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE)).limit(10);
        BigInteger[] bigIntArr = bigIntStream.toArray(BigInteger[]::new);
        System.out.println(Arrays.toString(bigIntArr));

    }
}
