package com.audi.leetcode.math;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/sum-of-square-numbers/
 * <p>
 * 题目给出一个整数c，求是否存在整数a、b使得a^2+b^2=c，事实上，a、b最大只能是c/2
 * <p>
 * 令d=c/2
 * 首先构建出数组，数组各元素为【0^2,1^2,2^2...d^2】,借助map结构，查找是否有符合的解
 *
 * @author: WangQuanzhou
 * @date: 2021-10-07 11:25 AM
 */
public class SumofSquareNumbers {

    public boolean judgeSquareSum(int c) {
        if (c < 3) {
            return true;
        }
        int d = c / 2;
        Set<Integer> set = new HashSet<>(d + 1);
        for (int i = 0; i <= d; i++) {
            set.add(i * i);
        }

        if (set.contains(c)) {
            return true;
        }

        for (Integer a : set) {
            if (a > c) {
                continue;
            }
            int b = c - a;
            if (set.contains(b)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
    }
}
