package com.audi.leetcode.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/next-greater-element-i/description/
 * <p>
 * 题目给定两个数组，其中nums1是nums2的子序列，现在希望找到nums1中各个元素在nums2中后面的第一个比他大的元素，找不到就是-1
 * <p>
 * 使用单调栈  结合hash表的思路求解  时间复杂度O(N)
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * Output: [-1,3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * Example 2:
 * <p>
 * Input: nums1 = [2,4], nums2 = [1,2,3,4]
 * Output: [3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
 * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
 *
 * @author : wangquanzhou
 * @date : 2025/12/7 12:10
 */
public class NextGreaterElementI {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (null == nums1 || nums1.length < 1) {
            return nums1;
        }
        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);
        // 将nums1的元素放入hashmap方便后续查找，O(1)时间复杂度
        Map<Integer, Integer> map = new HashMap<>(nums1.length);
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
        }
        // 定义单调递增栈
        Stack<Integer> stack = new Stack<>();
        // 遍历nums2数组
        for (int i = 0; i < nums2.length; i++) {
            // 循环判断是否满足条件
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                // 判断当前元素是否在nums1中出现
                if (map.containsKey(stack.peek())) {
                    // 收集结果
                    res[map.get(stack.pop())] = nums2[i];
                } else {
                    // 注意这里需要使用break  否则会陷入while死循环
                    break;
                }
            }
            // 入栈
            stack.push(nums2[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        NextGreaterElementI nextGreaterElementI = new NextGreaterElementI();
        int[] ints = nextGreaterElementI.nextGreaterElement(nums1, nums2);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
        System.out.println();
    }
}
