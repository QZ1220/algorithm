package com.audi.leetcode.divide;


import java.util.HashMap;

/**
 * https://leetcode.com/problems/majority-element/
 * <p>
 * 求数组中出现次数超过元素个数一半以上的元素
 *
 * @author: WangQuanzhou
 * @date: 2020/10/28 22:10
 */
public class MajorityElement {
    /**
     * 总感觉这个题目  除了使用Map记录每个元素出现的次数  还有通过数学特征来解题的办法
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
                if (map.get(num) > length / 2) {
                    return num;
                }
            } else {
                map.put(num, 1);
            }
        }
        return 0;
    }


    /**
     * 本题也可以基于摩尔投票法
     *
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        int count = 0; // 计数器初始化为0
        int candidate = nums[0]; // 候选元素初始化为数组第一个元素

        for (int num : nums) {
            if (count == 0) {
                candidate = num; // 当计数器为0，更新候选元素
            }

            if (num == candidate) {
                count++; //num与候选元素相同时，计数器加1
            } else {
                count--; //num与候选元素不同时，计数器减1
            }
        }

        return candidate; //历结束后，候选元素即为出现数超过一半的元素
    }

    public static void main(String[] args) {
        MajorityElement element = new MajorityElement();
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(element.majorityElement(nums));
    }
}
