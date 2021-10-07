package com.audi.leetcode.math;


import java.util.HashSet;
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

    /**
     * 下面这种解法存在一些不必要的计算
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum2(int c) {
        if (c < 3) {
            return true;
        }
        int d = ((Double) Math.floor(Math.sqrt(c))).intValue();
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
                System.out.println(a);
                System.out.println(Math.sqrt(a));
                System.out.println(b);
                System.out.println(Math.sqrt(b));
                return true;
            }
        }
        return false;
    }

    /**
     * 尝试使用双指针的思想来借这个题，性能会提升很多
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {
        if (c < 3) {
            return true;
        }
        int d = ((Double) Math.floor(Math.sqrt(c))).intValue();

        // 如果不使用double类型，后续的求和会越界
        double left = 0;
        double right = d;
        while (left <= right) {
            double sum = left * left + right * right;
            if (sum == c) {
                // 方便调试  可以打印输出结果
//                System.out.println(left);
//                System.out.println(right);
                return true;
            }
            if (sum < c) {
                left++;
            }
            if (sum > c) {
                right--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SumofSquareNumbers numbers = new SumofSquareNumbers();
        System.out.println(numbers.judgeSquareSum(2147483600));
        System.out.println(numbers.judgeSquareSum2(2147483600));
    }
}
