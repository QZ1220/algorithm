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

    public static void main(String[] args) {
        MajorityElement element = new MajorityElement();
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(element.majorityElement(nums));
    }
}
