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

        // 排序
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


    public static void main(String[] args) {
        SortDemo sortDemo = new SortDemo();
        int[] nums = {1, 2, 21, -9, -5, 2, 11, 90, 7, 13, 4, -5};
//        sortDemo.mergeSort(nums);
//        sortDemo.quickSort(nums);
        sortDemo.heapSort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "  ");
        }
        System.out.println();
    }
}
