package com.audi.leetcode.array;


import java.util.*;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * <p>
 * 求两个有序数组的合并后的，中位数
 * 若合并后，数组长度为基数，则为中间数字，否则为两个中间数字的平均值
 *
 * @author: WangQuanzhou
 * @date: 2020/11/17 21:50
 */
public class MedianofTwoSortedArrays {

    /**
     * Constraints:
     * <p>
     * nums1.length == m
     * nums2.length == n
     * 0 <= m <= 1000
     * 0 <= n <= 1000
     * 1 <= m + n <= 2000
     * -106 <= nums1[i], nums2[i] <= 106
     * <p>
     * The overall run time complexity should be O(log (m+n))
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m < 1) {
            if (n % 2 != 0) {
                return Double.valueOf(nums2[n / 2]);
            }
            return Double.valueOf((nums2[n / 2] + nums2[(n / 2) - 1] + 0.0) / 2);
        }

        if (n < 1) {
            if (m % 2 != 0) {
                return Double.valueOf(nums2[m / 2]);
            }
            return Double.valueOf((nums2[m / 2] + nums2[(m / 2) - 1] + 0.0) / 2);
        }

        // 使用有序map存储数组元素，key=nums[i],value=某个nums[i]出现的次数
        Map<Integer, Integer> map = new TreeMap();
        for (int i = 0; i < nums1.length; i++) {
            Integer value = map.get(nums1[i]);
            if (value != null) {
                map.put(nums1[i], value + 1);
                continue;
            }
            map.put(nums1[i], 1);
        }

        for (int i = 0; i < nums2.length; i++) {
            Integer value = map.get(nums2[i]);
            if (value != null) {
                map.put(nums2[i], value + 1);
                continue;
            }
            map.put(nums2[i], 1);
        }

        int total = m + n;
        // 中间位置
        int mid = total / 2;
        int pos = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            // nums数组数字个数累加
            pos = pos + entry.getValue();
            // 还没到中间位置
            if (pos < mid) {
                continue;
            }
            // 刚好到中间位置
            if (pos == mid) {
                // 如果新数组长度是偶数
                if (total%2==0){
                    // 当前未知的元素个数为1，需要去前一位置的元素，这就是难点。。。写到这里忽然发现不得了。。
                    if (entry.getValue()<2){
                        //
//                        return Double.valueOf(entry.)
                    }
                }
            }
        }

        return m;
    }




    public static void main(String[] args) {
        System.out.println(1 % 2);
        System.out.println(1 / (2 + 0.0));
    }
}
