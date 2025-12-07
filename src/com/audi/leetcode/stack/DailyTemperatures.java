package com.audi.leetcode.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/daily-temperatures/
 * <p>
 * 题目给定一个数组，数组元素表示每日的温度，求当前天后面第几天的温度比当前天更高。返回两个日期之间的距离，示例输入输出如下
 * <p>
 * Example 1:
 * <p>
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * Example 2:
 * <p>
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * Example 3:
 * <p>
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 * <p>
 * 使用单调栈的思想求解
 * <p>
 * 单调栈适合求解，数组中，左边或者右边第一个比当前元素小的位置或者距离，时间复杂度O(n)
 *
 * @author : wangquanzhou
 * @date : 2025/12/7 09:26
 */
public class DailyTemperatures {

    // 使用单调递增的栈的思想求解  时间复杂度O(N)
    public int[] dailyTemperatures(int[] temperatures) {
        if (null == temperatures || temperatures.length == 0) {
            return temperatures;
        }
        int[] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                // 进行出栈  以及  结果记录操作
                Integer head = stack.pop();
                res[head] = i - head;
            }
            // 入栈操作
            stack.push(i);
        }
        return res;
    }


    public static void main(String[] args) {
//        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] temperatures = dailyTemperatures.dailyTemperatures(temps);
        for (int i = 0; i < temperatures.length; i++) {
            System.out.print(temperatures[i] + " ");
        }
        System.out.println();
    }
}
