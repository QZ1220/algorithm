package com.audi.leetcode.array;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/3sum/
 * <p>
 * 和求2个数的和的题类似  需要借助hashmap
 *
 * 如何去除重复的元素呢？
 *
 * @author: WangQuanzhou
 * @date: 2020/4/21 22:31
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        if (null == nums || nums.length < 3) {
            return null;
        }
        List<List<Integer>> list = new ArrayList<>(128);
        HashMap<Integer, Integer> map = new HashMap<>(256);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], nums[i]);
        }
        for (int key : map.keySet()) {
            List tmpList = sum(-key, map);
            if (tmpList.size() > 0) {
                list.add(tmpList);
            }
        }
        return list;
    }

    private List<List<Integer>> sum(int sum, Map<Integer, Integer> map) {
        List<List<Integer>> list = new ArrayList<>(32);
        for (int key : map.keySet()) {
            int rest = sum - key;
            if (!map.containsKey(rest)) {
                continue;
            }
            List<Integer> result = new ArrayList<>();
            result.add(-sum);
            result.add(key);
            result.add(rest);
            list.add(result);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        ThreeSum threeSum = new ThreeSum();
        System.out.println(threeSum.threeSum(nums));
    }
}
