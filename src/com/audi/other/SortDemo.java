package com.audi.other;

/**
 * kind of sort algorithm
 *
 * @author : wangquanzhou
 * @date : 2023/7/27 21:43
 */
public class SortDemo {

    public void mergeSort(int[] nums) {
        if (null == nums || nums.length < 2) {
            return;
        }
        mergeSort(nums, 0, nums.length - 1);
    }

    /**
     * mergeSort进行数组的拆分，拆分到只有单个元素为止
     *
     * @param nums
     * @param left
     * @param right
     */
    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    /**
     * 合并子数组
     *
     * @param nums
     * @param left
     * @param mid
     * @param right
     */
    private void merge(int[] nums, int left, int mid, int right) {
        int s1 = left;
        int s2 = mid + 1;

        int[] tmpArr = new int[right - left + 1];
        int pos = 0;
        while (s1 <= mid && s2 <= right) {
            if (nums[s1] <= nums[s2]) {
                tmpArr[pos++] = nums[s1++];
            } else {
                tmpArr[pos++] = nums[s2++];
            }
        }
        while (s1 <= mid) {
            tmpArr[pos++] = nums[s1++];
        }
        while (s2 <= right) {
            tmpArr[pos++] = nums[s2++];
        }

        for (int i = 0; i < tmpArr.length; i++) {
            nums[i + left] = tmpArr[i];
        }
    }


    public void quickSort(int[] nums) {
        if (null == nums || nums.length < 2) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = nums[left];
        int i = left;
        int j = right;
        while (i < j) {
            // 右边如果找到一个大于pivot的元素就停止，否则就继续找
            while (i < j && nums[j] >= pivot) {
                j--;
            }
            // 左边如果找到一个小于pivot的元素就停止，否则就继续找
            while (i < j && nums[i] <= pivot) {
                i++;
            }
            if (i < j) {
                // 交换
                swap(nums, i, j);
            }
        }

        // 交换基准值
        nums[left] = nums[i];
        nums[i] = pivot;

        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public void heapSort(int[] nums) {
        if (null == nums || nums.length < 2) {
            return;
        }

        int n = nums.length;
        // 建堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(nums, n, i);
        }

        // 排序 将最大的元素移动到数组末尾，然后对除开末尾位置的数组元素构建大顶堆
        // 构建完成以后，数组头部又是剩余元素中最大的，重复上述过程，即可完成排序
        for (int i = n - 1; i >= 0; i--) {
            swap(nums, 0, i);
            heapify(nums, i, 0);
        }

    }

    /**
     * 构造大顶堆
     *
     * @param nums
     * @param n
     * @param i
     */
    private void heapify(int[] nums, int n, int i) {
        int parent = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && nums[left] > nums[parent]) {
            parent = left;
        }
        if (right < n && nums[right] > nums[parent]) {
            parent = right;
        }

        if (parent != i) {
            swap(nums, i, parent);
            heapify(nums, n, parent);
        }
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // 合并后的数组长度为偶数，此时返回中间位置两个元素的和，再除以2
        if ((m + n) % 2 == 0) {
            return (findKthSmallest(nums1, nums2, (m + n) / 2) + findKthSmallest(nums1, nums2, (m + n) / 2 + 1)) / 2.0;
        } else {
            // 合并后的数组长度为基数，此时直接返回中间位置的元素
            return findKthSmallest(nums1, nums2, (m + n) / 2 + 1);
        }
    }

    /**
     * 求两个排序数组的第k小的元素
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int findKthSmallest(int[] nums1, int[] nums2, int k) {
        int p1 = 0; // nums1指针
        int p2 = 0; // nums2指针

        while (true) {
            // 边界情况：如果nums1或nums2已经遍历完，则直接返回另一个数组中第k小的数
            if (p1 == nums1.length) {
                return nums2[p2 + k - 1];
            }
            if (p2 == nums2.length) {
                return nums1[p1 + k - 1];
            }

            // 边界情况：如果k=1，则直接返回两个数组当前指针位置较小的数
            if (k == 1) {
                return Math.min(nums1[p1], nums2[p2]);
            }

            // 在nums1中找到以p1为起点，长度为k的一半的区间位置
            // 在nums2中找到以p2为起点，长度为k的一半的区间位置
            int mid1 = Math.min(p1 + k / 2 - 1, nums1.length - 1);
            int mid2 = Math.min(p2 + k / 2 - 1, nums2.length - 1);

            // 如果nums1的中间位置的值小于等于nums2的中间位置的值
            // 则说明答案在nums1的中间位置之后，或者在nums2的中间位置之前
            if (nums1[mid1] <= nums2[mid2]) {
                k -= mid1 - p1 + 1; // 更新k的值，排除掉nums1中mid1之前的部分
                p1 = mid1 + 1; // 更新nums1的指针位置
            }
            // 否则，答案在nums1的中间位置之前，或者在nums2的中间位置之后
            else {
                k -= mid2 - p2 + 1; // 更新k的值，排除掉nums2中mid2之前的部分
                p2 = mid2 + 1; // 更新nums2的指针位置
            }
        }
    }


    public static void main(String[] args) {
        SortDemo sortDemo = new SortDemo();
//        int[] nums = {1, 2, 21, -9, -5, 2, 11, 90, 7, 13, 4, -5};
////        sortDemo.mergeSort(nums);
////        sortDemo.quickSort(nums);
//        sortDemo.heapSort(nums);
//        for (int i = 0; i < nums.length; i++) {
//            System.out.print(nums[i] + "  ");
//        }
//        System.out.println();

        int[] nums1 = {1, 2, 3, 4};
        int[] nums2 = {3, 4, 5, 6, 7};
        System.out.println(sortDemo.findKthSmallest(nums1, nums2, 3));
        System.out.println(sortDemo.findKthSmallest(nums1, nums2, 4));
        System.out.println(sortDemo.findKthSmallest(nums1, nums2, 5));
        System.out.println(sortDemo.findKthSmallest(nums1, nums2, 6));
        System.out.println(sortDemo.findKthSmallest(nums1, nums2, 7));
        System.out.println(2 / 3.0);
    }
}
