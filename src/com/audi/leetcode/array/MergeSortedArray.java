package com.audi.leetcode.array;

/**
 * 合并两个递增的数组num1Arr、num2Arr为一个，要求不能新建数组，合并的结果放到num1Arr中
 * <p>
 * https://leetcode.com/problems/merge-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
 *
 * @author : wangquanzhou
 * @date : 2023/6/18 10:23
 */
public class MergeSortedArray {

    /**
     * 0 <= m, n <= 200
     * 1 <= m + n <= 200
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        if (m == 0) {
            copy(nums1, nums2, n);
            return;
        }

        // 合并操作，O(m+n)常量时间复杂度
        // 使用双指针，从两个数组的末尾位置开始向前遍历，比较两个指针位置的数组元素大小，将较大的移动到的数组的尾部
        // 同时需要使用变量记录数组尾部的位置
        int pos1 = m - 1;
        int pos2 = n - 1;
        int lastPos = m + n - 1;
        while (pos1 >= 0 && pos2 >= 0) {

            if (nums1[pos1] > nums2[pos2]) {
                nums1[lastPos] = nums1[pos1];
                pos1--;
            } else {
                nums1[lastPos] = nums2[pos2];
                pos2--;
            }
            lastPos--;
        }
        if (pos2 >= 0) {
            copy(nums1, nums2, pos2 + 1);
        }
    }

    /**
     * 将nums2中的元素复制到nums1中，此操作会覆盖nums1的元素
     *
     * @param nums1
     * @param nums2
     */
    private void copy(int nums1[], int nums2[], int len) {
        for (int i = 0; i < len; i++) {
            nums1[i] = nums2[i];
        }
    }

    public static void main(String[] args) {
//        int[] nums1 = {1, 2, 3, 0, 0, 0};
//        int m = 3;
//        int[] nums2 = {2, 5, 6};
//        int n = 3;

//        int[] nums1 = {3, 5, 5, 0, 0, 0};
//        int m = 3;
//        int[] nums2 = {1, 5, 6};
//        int n = 3;

//        int[] nums1 = {1};
//        int m = 1;
//        int[] nums2 = {};
//        int n = 0;

        int[] nums1 = {0};
        int m = 0;
        int[] nums2 = {1};
        int n = 1;

        MergeSortedArray mergeSortedArray = new MergeSortedArray();
        mergeSortedArray.merge(nums1, m, nums2, n);
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + "  ");
        }
    }
}
