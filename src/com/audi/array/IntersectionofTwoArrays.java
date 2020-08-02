package com.audi.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays/
 * <p>
 * 求两个数组的交集
 * <p>
 * 结果不能有重复元素
 *
 * @author WangQuanzhou
 * @date 2020-08-02
 */
public class IntersectionofTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {

        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }

        for (int i = 0; i < nums2.length; i++) {
            set2.add(nums2[i]);
        }

        if (null != set1 && set1.size() > 0) {
            set1.retainAll(set2);
            if (null != set1 && set1.size() > 0) {
                int[] dst = new int[set1.size()];
                int i = 0;
                for (int num : set1) {
                    dst[i] = num;
                    i++;
                }
                return dst;
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
//        int[] nums1 = {1, 2, 2, 1};
        int[] nums1 = {4, 9, 5};
//        int[] nums2 = {2, 2};
        int[] nums2 = {9, 4, 9, 8, 4};
        IntersectionofTwoArrays arrays = new IntersectionofTwoArrays();
        nums1 = arrays.intersection(nums1, nums2);
        if (null != nums1) {
            for (int i = 0; i < nums1.length; i++) {
                System.out.print(nums1[i] + " ");
            }
        }

    }
}
