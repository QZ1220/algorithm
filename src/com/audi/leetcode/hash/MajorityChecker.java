package com.audi.leetcode.hash;

/**
 * https://leetcode.com/problems/online-majority-element-in-subarray/
 * <p>
 * 实现一个query方法，实现验证指定数组区间[left...right]元素重复次数超过threshold次的元素
 *
 * @author : wangquanzhou
 * @date : 2021/12/18 22:38
 */
public class MajorityChecker {

    int[] array;

    /**
     * 构造函数
     *
     * @param arr
     */
    public MajorityChecker(int[] arr) {
        this.array = arr;
    }

    /**
     * 统计指定区间出现次数超过threshold次的元素
     *
     * @param left
     * @param right
     * @param threshold
     * @return
     */
    public int query(int left, int right, int threshold) {
        for (int i = 0; i < array.length; i++) {
            if (i < left) {
                continue;
            }
            if (i > right) {
                break;
            }

        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
