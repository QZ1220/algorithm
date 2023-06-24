package com.audi.leetcode.array;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/rotate-array/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 旋转数组，eg：
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * <p>
 * Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 *
 * @author : wangquanzhou
 * @date : 2023/6/24 23:13
 */
public class RotateArray {

    /**
     * 可以借助队列实现，但是要使用额外的空间
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        LinkedList<Integer> queue = new LinkedList<>();
        for (int num : nums) {
            queue.add(num);
        }
        for (int i = 0; i < k; i++) {
            Integer last = queue.pollLast();
            queue.addFirst(last);
        }
        int i=0;
        while (!queue.isEmpty()){
            nums[i]=queue.poll();
            i++;
        }
    }

    public static void main(String[] args) {

    }
}
