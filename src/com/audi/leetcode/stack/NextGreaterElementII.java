package com.audi.leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.com/problems/next-greater-element-ii/
 * <p>
 * 题目给定一个循环数组，即数组元素是首尾相接的，求数组当前位置元素后面第一个比他大的元素。
 * <p>
 * 依然使用单调栈进行求解。
 *
 * @author : wangquanzhou
 * @date : 2025/12/7 14:04
 */
public class NextGreaterElementII {

    // 有两种思路来求解本题，第一种思路就是新开辟一个数组，长度是原数组长度2倍，将原数组复制两份到新的数组，然后就是一道朴素的使用单调栈求解的题
    public int[] nextGreaterElements(int[] nums) {
        if (null == nums || nums.length < 1) {
            return nums;
        }
        int newLen = nums.length * 2;
        int[] newNums = new int[newLen];
        // 复制数组
        for (int i = 0; i < nums.length; i++) {
            newNums[i] = nums[i];
            newNums[i + nums.length] = nums[i];
        }
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        // 定义单调栈  栈中存储的是数组元素的下标位置
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < newLen; i++) {
            while (!stack.isEmpty() && newNums[i] > newNums[stack.peek()]) {
                res[stack.peek() % nums.length] = newNums[i];
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }


    // 另外一种思路是不复制数组，直接使用下标取模的方式进行遍历，代码如下
    public int[] nextGreaterElements2(int[] nums) {
        if (null == nums || nums.length < 1) {
            return nums;
        }
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);

        for (int i = 0; i < nums.length * 2; i++) {
            while (!stack.isEmpty() && nums[i % nums.length] > nums[stack.peek()]) {
                res[stack.pop()] = nums[i % nums.length];
            }
            stack.push(i % nums.length);
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] nums = {1, 2, 1};
        int[] nums = {1, 2, 3, 4, 3};
        NextGreaterElementII nextGreaterElementII = new NextGreaterElementII();
//        int[] elements = nextGreaterElementII.nextGreaterElements(nums);
        int[] elements = nextGreaterElementII.nextGreaterElements2(nums);
        for (int i = 0; i < elements.length; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }
}
