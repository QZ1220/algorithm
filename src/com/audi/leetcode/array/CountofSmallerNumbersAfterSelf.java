package com.audi.leetcode.array;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 * <p>
 * 输出当前数组位置右侧比它小的元素个数
 * <p>
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * <p>
 * 首先来暴力一点，就是循环计算每个节点右侧比该节点小的元素个数
 *
 * @author: WangQuanzhou
 * @date: 2020/7/18 21:30
 */
public class CountofSmallerNumbersAfterSelf {
    /**
     * 这种时间复杂度O(N^2)的算法果然超时了  倒在了leetcode的最后一个测试用例  15/16  就差一个
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        if (nums.length == 1) {
            res.add(0);
            return res;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            int temp = nums[i];
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (temp > nums[j]) {
                    count++;
                }
            }
            res.add(count);
        }
        res.add(0);
        return res;
    }

    /**
     * 既然算法1这种原始的方法不行，那我们看看能不能有其他优化的算法
     * <p>
     * https://www.cnblogs.com/grandyang/p/5078490.html
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new LinkedList<>();
        }


        Integer[] res = new Integer[nums.length];

        List<Integer> tempList = new LinkedList<>();
        // 注意这里是从nums数组 的后往前便利
        for (int i = nums.length - 1; i >= 0; i--) {
//        for (int i = 0; i < nums.length; i++) {
            int left = 0, right = tempList.size();
            while (left < right) {
                int mid = (left + right) / 2;
                if (tempList.get(mid) >= nums[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            res[i] = right;
            tempList.add(right, nums[i]);
        }

        return Arrays.asList(res);
    }

    public static void main(String[] args) {
        CountofSmallerNumbersAfterSelf countofSmallerNumbersAfterSelf = new CountofSmallerNumbersAfterSelf();
//        int nums[] = {5, 2, 6, 1};
        int nums[] = {6, 6, 6, 1, 1, 1};
        System.out.println(countofSmallerNumbersAfterSelf.countSmaller2(nums));
    }

}
