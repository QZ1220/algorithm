package com.audi.leetcode.math;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/ugly-number-ii/
 * <p>
 * 题目输入一个正整数n，这个n代表代表从1开始的第n个丑数，要求输出这第n个丑数
 * <p>
 * 从2开始，一次判断当前这个数字是否是丑数，是就判断是否是第n个，否则将使用一个list将这个丑数存起来
 * 重复前面的过程
 *
 * @author: WangQuanzhou
 * @date: 2021-11-04 2:23 PM
 */
public class UglyNumberII {

    /**
     * 下面这种解法，本质上没错，只是在n=1000时，会TLE
     *
     * @param n
     * @return
     */
    public int nthUglyNumber2(int n) {
        if (n == 1) {
            return 1;
        }
        List<Integer> list = new LinkedList<>();
        list.add(1);
        for (int i = 2; i <= Integer.MAX_VALUE; i++) {
            if (isUgly(i)) {
                list.add(i);
                if (list.size() == n) {
                    return i;
                }
            }
        }
        return 1;
    }

    public static Set<Integer> uglySet = new HashSet<>();

    /**
     * 优化一下解题思路，使用set存储丑数，对于一个数字num，如果它是丑数，那么num*2 or num*3 or num*5 也一定是丑数
     * <p>
     * 不过依然TLE 尴尬
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }
        uglySet.add(1);
        for (int i = 2; i <= Integer.MAX_VALUE; i++) {
            if (isUgly(i)) {
                uglySet.add(i);
                if (uglySet.size() == n) {
                    return i;
                }
            }
        }
        return 1;
    }

    private boolean isUgly(int n) {
        if (n < 1) {
            return Boolean.FALSE;
        }
        if (n == 1) {
            return Boolean.TRUE;
        }
        int[] factor = {2, 3, 5};
        while (n != 1) {
            int temp = n;
            for (int i = 0; i < 3; i++) {
                if (n % factor[i] == 0) {
                    n = n / factor[i];
                    break;
                }
            }
            // 这种情况说明当前的n，被2、3、5都不能整除，因此返回false
            if (temp == n) {
                return Boolean.FALSE;
            }
            if (uglySet.contains(n)) {
                return Boolean.TRUE;
            }
            // 这种情况说明，n可以被2、3、5的组合整除
            if (n == 1) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 参考discuss里的解法，不再从1开始计算到第n个丑数
     * <p>
     * 而是直接计算第n个丑数
     *
     * @param n
     * @return
     */
    public int nthUglyNumber3(int n) {
        int result[] = new int[n];
        result[0] = 1;
        int multipleOf_2 = 0, multipleOf_3 = 0, multipleOf_5 = 0;
        for (int i = 1; i < n; i++) {
            result[i] = Math.min(Math.min(result[multipleOf_2] * 2, result[multipleOf_3] * 3), result[multipleOf_5] * 5);
            multipleOf_2 = result[multipleOf_2] * 2 == result[i] ? multipleOf_2 + 1 : multipleOf_2;
            multipleOf_3 = result[multipleOf_3] * 3 == result[i] ? multipleOf_3 + 1 : multipleOf_3;
            multipleOf_5 = result[multipleOf_5] * 5 == result[i] ? multipleOf_5 + 1 : multipleOf_5;
        }
        return result[n - 1];
    }

    public static void main(String[] args) {

        UglyNumberII uglyNumberII = new UglyNumberII();
        System.out.println(uglyNumberII.nthUglyNumber(150));
    }
}
