package com.audi.leetcode.array;

/**
 * https://leetcode.com/problems/find-pivot-index/?envType=study-plan&id=level-1
 *
 * @author : wangquanzhou
 * @date : 2023/3/8 21:27
 */
public class FindPivotIndex {

    /**
     * 一开始想的是使用双指针的方法，但是实际调试时发现不行
     *
     * @param nums
     * @return
     */
    public int pivotIndex2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int leftSum = nums[left];
        int rightSum = nums[right];

        while (left < right) {
            if (leftSum < rightSum) {
                left++;
                leftSum = leftSum + nums[left];
            }
            if (leftSum > rightSum) {
                right--;
                rightSum = rightSum + nums[right];
            }

            if (left < right && left + 2 == right && leftSum == rightSum) {
                return left + 1;
            }
            if (left == 0 && left + 1 == right && rightSum == 0) {
                return 0;
            }
            if (right == nums.length - 1 && left + 1 == right && leftSum == 0) {
                return nums.length - 1;
            }
        }
        return -1;
    }


    /**
     * 经过网上搜索，发现需要使用「前缀和」的方式来解决
     *
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        // 题设限定了nums不可能为空
        if (nums.length == 1) {
            return 0;
        }
        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                if (preSum[nums.length - 1] - nums[0] == 0) {
                    return 0;
                }
            } else if (preSum[i - 1] == (preSum[nums.length - 1] - preSum[i])) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] nums = {1, -1, 2, 1, -1};
        FindPivotIndex pivotIndex = new FindPivotIndex();
        System.out.println(pivotIndex.pivotIndex2(nums));
        System.out.println(pivotIndex.pivotIndex(nums));
    }
}
