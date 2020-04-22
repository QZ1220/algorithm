package com.audi.leetcode.array;


import java.util.*;

/**
 * https://leetcode.com/problems/3sum/
 * <p>
 * 和求2个数的和的题类似  需要借助hashmap
 * <p>
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
            map.put(nums[i], i);
        }
        for (int key : map.keySet()) {
            sum(-key, map, list);
        }
        return list;
    }

    private void sum(int sum, Map<Integer, Integer> map, List<List<Integer>> list) {
        for (int key : map.keySet()) {
            int rest = sum - key;
            if (!map.containsKey(rest) || map.get(-sum) == map.get(key) || map.get(-sum) == map.get(rest) || map.get(key) == map.get(rest)) {
                continue;
            }
            List<Integer> result = new ArrayList<>();
            result.add(-sum);
            result.add(key);
            result.add(rest);
            sort(result);
            if (!exist(list, result)) {
                list.add(result);
            }
        }
    }

    private Boolean exist(List<List<Integer>> list, List<Integer> result) {
        for (List<Integer> tmpList : list) {
            if (tmpList.get(0) == result.get(0) && tmpList.get(1) == result.get(1) && tmpList.get(2) == result.get(2)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 三个数 排序nums
     * <p>
     * 从小到大
     *
     * @param nums
     */
    private void sort(List<Integer> nums) {
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(j) < nums.get(i)) {
                    Integer tmp = nums.get(i);
                    nums.set(i, nums.get(j));
                    nums.set(j, tmp);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        ThreeSum threeSum = new ThreeSum();
        System.out.println(threeSum.threeSum(nums));
        List<Integer> linkedList = new LinkedList<>();
        linkedList.add(12);
        linkedList.add(1);
        linkedList.add(18);
        threeSum.sort(linkedList);
        System.out.println(linkedList);
    }
}
