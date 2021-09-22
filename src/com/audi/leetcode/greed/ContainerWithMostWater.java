package com.audi.leetcode.greed;


/**
 * https://leetcode.com/problems/container-with-most-water/
 * <p>
 * 这个题目有点类似于https://leetcode.com/problems/trapping-rain-water/
 * 本题需要求解的是，在数组中那个选择两个边界，使得组成的容器可以容纳最多的水
 * <p>
 * https://blog.csdn.net/qq_34228570/article/details/79534467
 * 参考这种解题思路，使用左右指针来求解，左指针初始在最左侧，右指针初始在最右侧，然后移动左右指针，使得围成的长方形的面积最大
 * <p>
 * 线之间形成的区域总是受限于短线的高度。此外，线越远，所获得的区域就越多。 我们使用两个指针，一个在开头，一个在数组末尾，构成行长度。
 * 此外，我们还保留了一个变量max来存储到目前为止获得的最大面积。在每步中，我们找到它们之间形成的区域，更新max并将指针指向另一端的指针。
 * 因为木桶原理，容积取决于行长度和最短高度的积，所以，两个端点高度较低的需要移动，因为高度较高的移动不可能大于原来的两端点积。
 * 这样，每次都是高度低的移动，直到两指针相邻。
 *
 * @author: WangQuanzhou
 * @date: 2021-09-21 2:34 PM
 */
public class ContainerWithMostWater {


    public int maxArea(int[] height) {
        // 题设说了height数组的长度最小为2
        int length = height.length;
        if (length < 3) {
            return height[1] - height[0] > 0 ? height[0] : height[1];
        }

        int left = 0;
        int right = length - 1;
        int volume = 0;

        // 左指针往右移   右指针往左移  直到二者相邻（不能相遇，相遇围成的面积为0）
        while (left != right) {
            if (height[left] <= height[right]) {
                int temp = height[left] * (right - left);
                volume = temp > volume ? temp : volume;
                left++;
            }
            if (height[left] > height[right]) {
                int temp = height[right] * (right - left);
                volume = temp > volume ? temp : volume;
                right--;
            }
        }
        return volume;
    }

    public static void main(String[] args) {
    }
}
