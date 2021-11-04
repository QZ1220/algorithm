package com.audi.leetcode.math;


/**
 * https://leetcode.com/problems/ugly-number/
 * <p>
 * 题目给了一个整数，判断这个数是不是丑数：
 * 1、丑数是一个正整数
 * 2、丑数的质数因子只能是2、3、5的组合
 * 3、1也是丑数
 * <p>
 * 直接使用输入的数字与「2、3、5」的其中一个相除，如果能整出就继续上面的过程，如果不能整出就换一个数字继续除
 * 如果都不能整出就返回false
 * 如果除到最后等于1，就返回true
 *
 * @author: WangQuanzhou
 * @date: 2021-11-04 1:22 PM
 */
public class UglyNumber {

    public boolean isUgly(int n) {
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
            // 这种情况说明，n可以被2、3、5的组合整除
            if (n == 1) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public static void main(String[] args) {
    }
}
