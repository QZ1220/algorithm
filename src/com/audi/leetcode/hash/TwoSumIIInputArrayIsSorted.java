package com.audi.leetcode.hash;


import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 * <p>
 * 题目给了一个递增排列的整型数组，求是否存在两个数使得二者的和等于target
 * <p>
 * 跟twoSum那题的解法一样，借助map来实现
 * <p>
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers is sorted in non-decreasing order.
 * -1000 <= target <= 1000
 * The tests are generated such that there is exactly one solution.
 *
 * @author: WangQuanzhou
 * @date: 2021-11-02 11:07 AM
 */
public class TwoSumIIInputArrayIsSorted {

    /**
     * 基于map的思想
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int len = numbers.length;
        Map<Integer, Integer> map = new HashMap<>(len);
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i + 1);
        }

        int[] res = new int[2];

        for (int i = 0; i < numbers.length; i++) {
            int tmp = target - numbers[i];
            if (map.containsKey(tmp) && map.get(tmp) != (i + 1)) {
                if ((i + 1) > map.get(tmp)) {
                    res[0] = map.get(tmp);
                    res[1] = i + 1;
                } else {
                    res[0] = i + 1;
                    res[1] = map.get(tmp);
                }
                return res;
            }
        }
        return res;
    }

    /**
     * 由于数组的排序了的，因此可以基于双指针的思想来求解
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum2(int[] numbers, int target) {
        int[] res = new int[2];
        int len = numbers.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                res[0] = left + 1;
                res[1] = right + 1;
                return res;
            }
            if ((numbers[right] + numbers[left]) > target) {
                right--;
            }
            if (numbers[left] + numbers[right] < target) {
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2};
        TwoSumIIInputArrayIsSorted sorted = new TwoSumIIInputArrayIsSorted();
        System.out.println(sorted.twoSum2(nums, 0));
    }
}
