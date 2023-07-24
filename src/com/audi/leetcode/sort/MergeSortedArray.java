package com.audi.leetcode.sort;

/**
 * https://leetcode.com/problems/merge-sorted-array/description/
 * <p>
 * 题目给定了两个整型数组nums1、nums2，分别都是非递减排列，nums1的长度为m+n，后n个元素都是0
 * nums2的长度是n
 * 将两个数组合并，合并的后要求也是非递减排列，合并的结果放在nums1
 * <p>
 * 思路：
 * 先将nums2复制到nums1的尾部，然后使用归并排序进行排序
 *
 * @author : wangquanzhou
 * @date : 2023/7/24 21:46
 */
public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n <= 0) {
            return;
        }
        // 将nums数组拷贝到nums1数组
        for (int i = 0; i < n; i++) {
            nums1[i + m] = nums2[i];
        }
        // 开始进行归并排序
        mergeSort(nums1, 0, m + n - 1);


    }

    private void mergeSort(int[] nums, int low, int high) {
        // 此时说明拆分后的子数组只有一个元素
        if (low >= high) {
            return;
        }
        int mid = (low + high) / 2;
        // 注意左边递归的右端点为mid，不是mid-1，这里要区别于二分查找
        mergeSort(nums, low, mid);
        mergeSort(nums, mid + 1, high);

        // 下面进行子数组的合并
        merge(nums, low, mid, high);
    }

    private void merge(int[] nums, int low, int mid, int high) {
        // s1表示第一个子数组的起始位置
        int s1 = low;
        // s2表示第二个子数组的起始位置
        int s2 = mid + 1;
        // 定义临时数组，暂存归并后的子数组
        int[] tmpArr = new int[high - low + 1];
        int i = 0;
        // 进行比较排序
        while (s1 <= mid && s2 <= high) {
            if (nums[s1] <= nums[s2]) {
                tmpArr[i++] = nums[s1++];
            } else {
                tmpArr[i++] = nums[s2++];
            }
        }

        while (s1 <= mid) {
            tmpArr[i++] = nums[s1++];
        }
        while (s2 <= high) {
            tmpArr[i++] = nums[s2++];
        }

        // 将数据从tmpArr拷贝到原数组
        for (int j = 0; j < tmpArr.length; j++) {
            // 注意nums数组元素的下标
            nums[j + low] = tmpArr[j];
        }
    }
}
