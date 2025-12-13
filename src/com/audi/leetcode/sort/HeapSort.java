package com.audi.leetcode.sort;

public class HeapSort {

    /**
     * 堆排序主方法（升序）
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;

        // Step 1: 构建最大堆（从最后一个非叶子节点开始）
        // 最后一个非叶子节点索引 = (n / 2) - 1    注意这里数据的起止位置
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Step 2: 逐个将堆顶（最大值）移到末尾
        for (int i = n - 1; i > 0; i--) {
            // 交换堆顶和当前末尾
            swap(arr, 0, i);
            // 对 [0, i-1] 范围重新堆化（堆大小减1）
            heapify(arr, i, 0);
        }
    }

    /**
     * 调整以 index 为根的子树为最大堆（假设子树已经是堆）
     *
     * @param arr      数组
     * @param heapSize 当前堆的大小（有效范围：0 ~ heapSize-1）
     * @param root     根节点索引
     */
    private static void heapify(int[] arr, int heapSize, int root) {
        int largest = root;          // 初始化最大值为根
        int left = 2 * root + 1;     // 左子节点
        int right = 2 * root + 2;    // 右子节点

        // 如果左子节点存在且大于根
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }

        // 如果右子节点存在且大于当前最大值
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        // 如果最大值不是根，则交换并继续调整
        if (largest != root) {
            swap(arr, root, largest);
            // 递归调整受影响的子树
            heapify(arr, heapSize, largest);
        }
    }

    /**
     * 交换数组中两个元素
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ===== 测试主函数 =====
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("原始数组:");
        printArray(arr);

        heapSort(arr);

        System.out.println("堆排序后:");
        printArray(arr);

        // 测试边界情况
        int[] empty = {};
        heapSort(empty);
        System.out.println("空数组: " + java.util.Arrays.toString(empty));

        int[] single = {42};
        heapSort(single);
        System.out.println("单元素: " + java.util.Arrays.toString(single));
    }

    private static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}