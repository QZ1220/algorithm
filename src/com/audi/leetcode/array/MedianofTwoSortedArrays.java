package com.audi.leetcode.array;


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

        // fixme 是否需要合并数组
        return m;
    }

    public static void main(String[] args) {
        System.out.println(1 % 2);
        System.out.println(1 / (2 + 0.0));
    }
}
