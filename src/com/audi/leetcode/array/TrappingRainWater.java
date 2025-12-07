package com.audi.leetcode.array;

import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 * <p>
 * 基本思路，就是：某个位置能接多少雨水，首先确定她的左右挡板的最大值，再从两个最大值中选一个较小的，这个较小的值减去当前节点本身的高度，即为当前节点可接水量
 * <p>
 * Input: [5,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * @author WangQuanzhou
 * @date 2020-04-15
 */
public class TrappingRainWater {

    // 纵向求解
    public int trap(int[] height) {
        if (null == height || height.length < 3) {
            return 0;
        }

        // height的第一个节点和最后一个节点是肯定接不了水的
        int length = height.length;

        int[] left = new int[length];
        int[] right = new int[length];
        left[0] = height[0];
        right[length - 1] = height[length - 1];

        // 找到每个元素左边的最大值
        for (int i = 1; i < length - 1; i++) {
            left[i] = left[i - 1] - height[i] >= 0 ? left[i - 1] : height[i];
        }

        // 找到每个元素右边的最大值
        for (int i = length - 2; i > 0; i--) {
            right[i] = right[i + 1] - height[i] >= 0 ? right[i + 1] : height[i];
        }

        int sum = 0;
        for (int i = 1; i < length - 1; i++) {
            int leftMax = left[i];
            int rightMax = right[i];
            int min = leftMax - rightMax >= 0 ? rightMax : leftMax;
            if (height[i] < min) {
                sum = sum + min - height[i];
            }
        }
        return sum;
    }


    // 横向求解，使用单调栈求解  参考视频：https://www.bilibili.com/video/BV1uD4y1u75P?spm_id_from=333.788.videopod.sections&vd_source=d1530fb814268f770330143e24aaf1e6
    public int trap2(int[] height) {
        if (null == height || height.length < 3) {
            return 0;
        }
        int sum = 0;

        // 定义单调栈  单调递增栈  横向求和
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // 右边的边界
                int right = height[i];
                // 中间位置
                int middle = height[stack.pop()];
                // 由于上面一行进行了弹出栈的操作，因此这里需要需要再做下栈为空的判断
                if (!stack.isEmpty()) {
                    // 左侧的边界
                    int left = height[stack.peek()];
                    // 计算所能容纳的雨水的面积
                    if (left > middle && right > middle) {
                        int tempH = Math.min(right, left);
                        sum += (tempH - middle) * (i - stack.peek() - 1);
                    }
                }
            }
            stack.push(i);
        }
        return sum;
    }


    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] height = {6, 0, 3};
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        System.out.println(trappingRainWater.trap(height));
        System.out.println(trappingRainWater.trap2(height));
    }
}
