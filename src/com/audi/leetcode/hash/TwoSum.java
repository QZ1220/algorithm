package com.audi.leetcode.hash;


import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 * <p>
 * 题目给出了一个整型数组，求是否存在两个整数，使得相加的和等于target
 * <p>
 * 借助map的思想，将数组的元素放入map中，然后借助map的contains方法来求解
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 *
 * @author: WangQuanzhou
 * @date: 2021-11-02 10:49 AM
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int posList[] = new int[2];
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int tmp = target - nums[i];
            if (map.containsKey(tmp) && map.get(tmp) != i) {
                posList[0] = i;
                posList[1] = map.get(target - nums[i]);
                break;
            }
        }
        return posList;
    }

    public static void main(String[] args) {
    }
}
