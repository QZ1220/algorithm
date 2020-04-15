package com.audi.leetcode.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        List<Node> arrayList = new ArrayList<>(length);
        Node firstNode = new Node(height[0], 0);
        arrayList.add(0, firstNode);
        for (int i = 1; i < length-1; i++) {
            arrayList.add(new Node(0, 0));
        }
        Node lastNode = new Node(0, height[length - 1]);
        arrayList.add(length - 1, lastNode);

        for (int i = 1; i < length - 1; i++) {
            int leftMax = arrayList.get(i - 1).leftMax - height[i] >= 0 ? arrayList.get(i - 1).leftMax : height[i];
            arrayList.set(i, new Node(leftMax, 0));
        }

        for (int i = length - 1; i > 0; i--) {
            int rightMax = arrayList.get(i - 1).rightMax - height[i] >= 0 ? arrayList.get(i - 1).rightMax : height[i];
            Node node = arrayList.get(i);
            node.rightMax = rightMax;
            arrayList.set(i, node);
        }

        int sum = 0;
        for (int i = 1; i < length - 1; i++) {
            Node node = arrayList.get(i);
            int min = node.getMin();
            if (height[i] < min) {
                sum = sum + min - height[i];
            }
        }
        return sum;
    }

    class Node {
        public int leftMax = 0;
        public int rightMax = 0;

        public Node(int leftMax, int rightMax) {
            this.leftMax = leftMax;
            this.rightMax = rightMax;
        }

        public int getMin() {
            return leftMax - rightMax >= 0 ? rightMax : leftMax;
        }
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        System.out.println(trappingRainWater.trap(height));
    }
}
