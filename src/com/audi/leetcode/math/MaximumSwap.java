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

    /**
     * 类似于双指针的思想
     * <p>
     * 从第begin位开始校验，如果找到的最大值位置maxPos==begin，说明不应该交换，指针后移一位，继续比较
     * <p>
     * 如果maxPos!=begin，交换两个位置的元素，程序退出
     *
     * @param num
     * @return
     */
    public int maximumSwap(int num) {
        String s = String.valueOf(num);
        if (s.length() < 2) {
            return num;
        }

        char[] chars = s.toCharArray();
        int maxPos = 0;
        char max = s.charAt(maxPos);
        int begin = maxPos;
        // 循环遍历
        while (begin == maxPos && begin < s.length()) {
            // 做判断，避免死循环
            if (begin == s.length() - 1) {
                break;
            }
            // 遍历数组，找最大元素所在位置，注意这里要从数组尾部开始往前遍历，考虑1993的测试用例
            for (int i = s.length() - 1; i >= begin; i--) {
                char c = chars[i];
                if (c > max) {
                    max = c;
                    maxPos = i;
                }
            }

            // 如果最大元素在当前数组的开头位置，那么不需要进行替换，继续循环找最大元素
            if (maxPos == begin && begin < s.length() - 1) {
                begin++;
                maxPos++;
                max = s.charAt(maxPos);
            }
        }

        // 交换指定位置元素，使得交换一次后得到的数字最大
        if (maxPos != begin) {
            char tempCh = chars[begin];
            chars[begin] = chars[maxPos];
            chars[maxPos] = tempCh;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
        }
        return Integer.valueOf(sb.toString());
    }

    /**
     * 下面的解法  98368  这种测试用例的情况下会失败
     *
     * @param num
     * @return
     */
    public int maximumSwap2(int num) {
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
        int num = 98368;
        MaximumSwap maximumSwap = new MaximumSwap();
        int i = maximumSwap.maximumSwap(num);
        System.out.println(i);
    }

}


