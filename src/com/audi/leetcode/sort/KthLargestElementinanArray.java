package com.audi.leetcode.sort;


import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * <p>
 * 寻找一个数组中第k大的元素
 * <p>
 * 这个题，肯定是可以使用一个大小为k的小顶堆来实现，堆顶的元素就是本题的解，时间复杂度N*LOGN
 * <p>
 * 但是使用堆的话，涉及堆的创建、调整代码量稍微有点大，我们看看是否可以使用子数组的方式来求解，时间复杂度N^2
 *
 * @author: WangQuanzhou
 * @date: 2021/9/2 23:13
 */
public class KthLargestElementinanArray {

    /**
     * 利用数组来实现
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {

        // 新建一个大小为k的数组
        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            int max = nums[0];
            int pos = 0;
            for (int j = 0; j < nums.length; j++) {
                if (max < nums[j]) {
                    max = nums[j];
                    pos = j;
                }
            }
            nums[pos] = Integer.MIN_VALUE;
            list.add(max);
        }
        return list.get(list.size() - 1);
    }

    /**
     * 对内部的循环做一定的优化
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLarges2(int[] nums, int k) {

        // 新建一个大小为k的数组
        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            int max = nums[i];
            int pos = i;
            for (int j = 0; j < nums.length; j++) {
                if (max < nums[j]) {
                    max = nums[j];
                    pos = j;
                }
            }
            // 将当前找到的最大元素写入list，然后将该位置的值替换为nums[i]，原来的nums[i]写为Integer.MIN_VALUE，减少不必要的循环
            nums[pos] = nums[i];
            nums[i] = Integer.MIN_VALUE;
            list.add(max);
        }
        return list.get(list.size() - 1);
    }

    public static void main(String[] args) {
        KthLargestElementinanArray largestElementinanArray = new KthLargestElementinanArray();
        int nums[] = {3,2,1,5,6,4};
//        int nums[] = {-1, -1};
//        int k = 4;
        int k = 2;
//        int kthLargest1 = largestElementinanArray.findKthLargest(nums, k);
        int kthLargest2 = largestElementinanArray.findKthLarges2(nums, k);
//        System.out.println(kthLargest1);
        System.out.println(kthLargest2);
    }
}
