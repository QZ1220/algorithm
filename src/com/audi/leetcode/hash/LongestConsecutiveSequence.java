package com.audi.leetcode.hash;


import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * <p>
 * 题目给出了一个无序的整型数组，求这个整型数组中，最长的连续子串的长度
 * <p>
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * <p>
 * 要求时间复杂度为O(N)
 * <p>
 * 一般这种无序的数组，又要求时间复杂度为O(N)的只能借助hash表来做，
 * 又因为这里我们不需要做其他操作，因此可以进一步简化为使用HashSet来做。
 *
 * @author: WangQuanzhou
 * @date: 2021-10-28 4:33 PM
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        int length = nums.length;
        Set<Integer> set = new HashSet<>(length);
        for (int num : nums) {
            set.add(num);
        }
        int res = 0;
        for (Integer item : set) {
            // 注意这里count从1开始，因为单个元素也是一个连续的子序列
            int count = 1;
            if (!set.contains(item - 1)) {
                while (set.contains(item + 1)) {
                    count++;
                    item++;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }

    public static void main(String[] args) {
    }
}
