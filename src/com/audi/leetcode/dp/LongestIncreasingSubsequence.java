package com.audi.leetcode.dp;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/
 * <p>
 * https://www.cnblogs.com/grandyang/p/4938187.html
 *
 * @author: WangQuanzhou
 * @date: 2020/5/16 22:20
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int length = nums.length;
        if (nums == null || length == 0) {
            return 0;
        }

        // dp数组表示以第i个元素结尾的最长递增子序列的长度
        int[] dp = new int[length];
        // 注意dp数组初始化全都是1
        Arrays.fill(dp, 1);

        for (int i = 0; i < length; i++) {
            // 循环找最长递增子序列
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // 返回足底啊子串长度
        // 这里其实可以在上面的循环出维护max变量  就可以减少一次循环
        int max = dp[0];
        for (int i = 1; i < length; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }


    /**
     * 可以参考文章开头的链接
     * <p>
     * 时间复杂度更优化的解
     * <p>
     * 我们来看一种思路更清晰的二分查找法，跟上面那种方法很类似，思路是先建立一个空的 dp 数组，然后开始遍历原数组，
     * 对于每一个遍历到的数字，用二分查找法在 dp 数组找第一个不小于它的数字，如果这个数字不存在，那么直接在 dp 数组后面加上遍历到的数字，
     * 如果存在，则将这个数字更新为当前遍历到的数字，最后返回 dp 数组的长度即可，注意的是，跟上面的方法一样，
     * 特别注意的是 dp 数组的值可能不是一个真实的 LIS。
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        int length = nums.length;
        if (nums == null || length == 0) {
            return 0;
        }

        ArrayList<Integer> dp = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            int left = 0, right = dp.size();
            while (left < right) {
                int mid = (left + right) / 2;
                if (dp.get(mid) < nums[i]) {
                    left = mid + 1;
                } else {
                    // 注意下面不是mid-1
                    right = mid;
                }
            }
            if (right >= dp.size()) {
                dp.add(nums[i]);
            } else {
                dp.set(right, nums[i]);
            }
        }
        return dp.size();
    }


    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        System.out.println(longestIncreasingSubsequence.lengthOfLIS(nums));
        System.out.println(longestIncreasingSubsequence.lengthOfLIS2(nums));
    }
}
