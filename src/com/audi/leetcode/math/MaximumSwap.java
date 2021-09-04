package com.audi.leetcode.math;


/**
 * https://leetcode.com/problems/maximum-swap/
 * <p>
 * 对于一个非负整数，只能交换交换其中的两位数字一次，求这次交换以后可以得到的最大值
 * <p>
 * 将数字当成字符串看待，设max=0，使用一个指针从字符串的末尾往前遍历，遇到一个比max大的数就替换max为该数，并且记录该位置
 * 最后交换最大值出现的位置的数字与第0个数字
 * 即可得到最大值
 * <p>
 * 上面的解题思路在  98368  这种测试用例的情况下会失败
 *
 * @author: WangQuanzhou
 * @date: 2021-09-04 5:46 PM
 */
public class MaximumSwap {

    public int maximumSwap(int num) {
        String s = String.valueOf(num);
        if (s.length() < 2) {
            return num;
        }

        char max = s.charAt(s.length() - 1);
        int maxPos = s.length() - 1;

        char[] chars = s.toCharArray();


        for (int i = s.length() - 2; i >= 0; i--) {
            char c = chars[i];
            if (c > max) {
                max = c;
                maxPos = i;
            }
        }
        if (maxPos != 0) {
            char tempCh = chars[0];
            chars[0] = chars[maxPos];
            chars[maxPos] = tempCh;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
        }
        return Integer.valueOf(sb.toString());
    }

    public static void main(String[] args) {
        int num = 1993;
        MaximumSwap maximumSwap = new MaximumSwap();
        int i = maximumSwap.maximumSwap(num);
        System.out.println(i);
    }

}


