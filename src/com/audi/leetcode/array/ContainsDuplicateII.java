package com.audi.leetcode.array;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/contains-duplicate-ii/
 * <p>
 * 求数组中是否存在重复元素，且二者的下标查在某个范围内，存在返回true，不存在返回false
 * <p>
 * 使用map实现解题
 */
public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (null == nums || nums.length < 2) {
            return false;
        }

        int length = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>(length);

        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (!map.containsKey(num)) {
                List<Integer> list = new LinkedList<>();
                list.add(i);
                map.put(num, list);
                continue;
            }

            List<Integer> list = map.get(num);
            Integer last = list.get(list.size() - 1);
            if (Math.abs(last - i) <= k) {
                return true;
            } else {
                list.add(i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 2, 3};
        int k = 2;
        ContainsDuplicateII duplicateII = new ContainsDuplicateII();
        System.out.println(duplicateII.containsNearbyDuplicate(nums, k));
    }
}
