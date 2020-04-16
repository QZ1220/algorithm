package com.audi.leetcode.array;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 * <p>
 * 基本思路，就是：某个位置能接多少雨水，首先确定她的左右挡板的最大值，再从两个最大值中选一个较小的，这个较小的值减去当前节点本身的高度，纪委当前节点可接水量
 * <p>
 * Input: [5,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * @author WangQuanzhou
 * @date 2020-04-15
 */
public class TrappingRainWater {

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


    public static void main(String[] args) {
//        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height = {6, 0, 3};
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        System.out.println(trappingRainWater.trap(height));
    }
}
