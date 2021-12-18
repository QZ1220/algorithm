package com.audi.leetcode.hash;

import java.util.*;

/**
 * https://leetcode.com/problems/majority-element-ii/
 * <p>
 * 题目给了一个整型数组，求出数组所有符合条件的数字
 * 条件：该数字在数组中出现的次数大于3/n，n为数组的长度
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,3]
 * Output: [3]
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: [1]
 * Example 3:
 * <p>
 * Input: nums = [1,2]
 * Output: [1,2]
 * <p>
 * 借助hash表实现
 *
 * @author : wangquanzhou
 * @date : 2021/12/18 21:55
 */
public class MajorityElementII {

    /**
     * 借助hash表实现，虽然可以AC但是性能貌似不是特别好
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> dtsList = new LinkedList<>();
        if (nums.length < 2) {
            // 题设保证了数组不为空，这里就不做空判断了
            dtsList.add(nums[0]);
            return dtsList;
        }

        int length = nums.length;
        int baseCount = length / 3;
        // 使用set存放结果，并且对结果进行去重
        Set<Integer> dtsSet = new HashSet<>(4);

        Map<Integer, Integer> map = new HashMap<>(baseCount);
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
                if (map.get(num) > baseCount) {
                    dtsSet.add(num);
                }
            } else {
                map.put(num, 1);
                if (1 > baseCount) {
                    dtsSet.add(num);
                }
            }
        }
        dtsList.addAll(dtsSet);
        return dtsList;
    }


    /**
     * https://blog.csdn.net/weixin_41504611/article/details/103804027
     * <p>
     * 基于摩尔投票法
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> dtsList = new LinkedList<>();

        return dtsList;
    }


    public static void main(String[] args) {

    }
}
