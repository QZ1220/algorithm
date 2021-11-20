package com.audi.leetcode.two.pointer;


/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * <p>
 * 题目给定一个target，以及一个整型数组，求一个子数组，使得子数组的和等于target
 * 子数组要求尽可能的短
 * 输出子数组的长度
 * <p>
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * <p>
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 * <p>
 * https://www.cnblogs.com/grandyang/p/4501934.html
 * <p>
 * 需要定义两个指针 left 和 right，分别记录子数组的左右的边界位置，然后让 right 向右移，直到子数组和大于等于给定值或者 right 达到数组末尾，
 * 此时更新最短距离，并且将 left 像右移一位，然后再 sum 中减去移去的值，然后重复上面的步骤，直到 right 到达末尾，
 * 且 left 到达临界位置，即要么到达边界，要么再往右移动，和就会小于给定值。
 * 这种思路在
 * 5
 * [2,3,1,2,4,3]
 * 的测试用例下会失败
 * <p>
 *
 * @author: WangQuanzhou
 * @date: 2021/11/10 23:04
 */
public class MinimumSizeSubarraySum {

    /**
     * 采用双指针的思想求解
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int target, int[] nums) {
        if (null == nums || nums.length < 1) {
            return 0;
        }
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if (sum < target) {
            return 0;
        }
        if (sum == target) {
            return len;
        }

        int left = 0, right = len - 1;
        while (left <= right) {
            if (nums[left] <= nums[right]) {
                sum -= nums[left];
                left++;
            } else {
                sum -= nums[right];
                right--;
            }
            len--;
            if (sum == target) {
                return len;
            }
            if (sum < target) {
                return 0;
            }
        }
        return 0;
    }


    /**
     * https://www.cnblogs.com/grandyang/p/4501934.html
     * 参考上面的链接，O(n) 的解法，需要定义两个指针 left 和 right，分别记录子数组的左右的边界位置，然后让 right 向右移，
     * 直到子数组和大于等于给定值或者 right 达到数组末尾，此时更新最短距离，并且将 left 像右移一位，然后再 sum 中减去移去的值，
     * 然后重复上面的步骤，直到 right 到达末尾，且 left 到达临界位置，即要么到达边界，要么再往右移动，和就会小于给定值。
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return nums[0] == target ? 1 : 0;
        }
        int left = 0;
        int right = 0;
        int sum = 0;
        // 记录最小区间长度  初始化为最大数组长度+1，避免最短的子数组就是原数组
        int res = len + 1;
        while (right < len) {
            while (sum < target && right < len) {
                sum += nums[right++];
            }
            while (sum >= target) {
                res = Math.min(res, right - left);
                sum -= nums[left++];
            }
        }
        return res == len + 1 ? 0 : res;
    }


    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 30};
        int t = 30;
        MinimumSizeSubarraySum subarraySum = new MinimumSizeSubarraySum();
        System.out.println(subarraySum.minSubArrayLen(t, nums));
    }
}
