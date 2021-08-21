package com.audi.leetcode.array;


/**
 * https://leetcode.com/problems/single-number/
 * <p>
 * 一个数组中，有一个数字只出现了一次，其余数据都出响了两次，找出这个只出现一次的数字
 * <p>
 * 题目要求实现o（n）的时间复杂度，常数级的空间复杂度
 * <p>
 * 直接使用异或的思想
 * <p>
 * 如果重复的数字是成对出现的话，还可以使用累加的思想，当然效率还是没有位运算快
 *
 * @author: WangQuanzhou
 * @date: 2021-08-21 2:01 PM
 */
public class SingleNumber {

    public int singleNumber(int[] nums) {
        // 题设保证了数组不为空
        int sum = 0;
        for (int num : nums) {
            sum = sum ^ num;
        }
        return sum;
    }

    public static void main(String[] args) {
        int a = 2;
        System.out.println(a ^ a);
        System.out.println(a ^ 0);
    }
}
