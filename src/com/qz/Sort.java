package com.qz;

/**
 * 各种排序算法汇总
 *
 * @author : wangquanzhou
 * @date : 2023/8/21 22:50
 */
public class Sort {

    public void mergeSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int s1 = left;
        int s2 = mid + 1;
        int[] tmpArr = new int[right - left + 1];
        int i = 0;
        while (s1 <= mid && s2 <= right) {
            if (nums[s1] <= nums[s2]) {
                tmpArr[i++] = nums[s1++];
            } else {
                tmpArr[i++] = nums[s2++];
            }
        }

        while (s1 <= mid) {
            tmpArr[i++] = nums[s1++];
        }
        while (s2 <= right) {
            tmpArr[i++] = nums[s2++];
        }

        for (int j = 0; j < (right - left + 1); j++) {
            nums[left + j] = tmpArr[j];
        }
    }


    public void heapSort(int[] nums) {
        for (int i = (nums.length / 2) - 1; i >= 0; i--) {
            heapify(nums, nums.length, i);
        }
    }

    /**
     * 维护堆性质 小顶堆   从小到大排序
     *
     * @param nums
     * @param len
     * @param i
     */
    private void heapify(int[] nums, int len, int i) {
        int parent = i;
        int left = 2 * parent + 1;
        int right = 2 * parent + 2;

        if (left < len && nums[left] < nums[parent]) {
            parent = left;
        }
        if (right < len && nums[right] < nums[parent]) {
            parent = right;
        }

        if (parent != i) {
            swap(nums, parent, i);
            heapify(nums, len, parent);
        }

    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int left, int right) {
        if (null == nums || nums.length < 2) {
            return;
        }
        if (left >= right) {
            return;
        }

        int i = left;
        int j = right;
        int base = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= base) {
                right--;
            }

            while (left < right && nums[left] <= base) {
                left++;
            }

            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }

        nums[i] = nums[left];
        nums[left] = base;

        quickSort(nums, i, left - 1);
        // 注意下面不能使用left++
        quickSort(nums, left + 1, j);

    }

    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] nums = {1, -1, 10, 2, 5, 3};
        int[] nums1 = {1, -1, 10, 2, 5, 3};
        int[] nums2 = {1, -1, 10, 2, 5, 3};
        sort.mergeSort(nums);
        for (int num : nums) {
            System.out.print(num + "  ");
        }
        System.out.println();
        sort.heapSort(nums1);
        for (int i : nums1) {
            System.out.print(i + "  ");
        }
        System.out.println();

        sort.quickSort(nums2);
        for (int i : nums2) {
            System.out.print(i + "  ");
        }
        System.out.println();
    }

}