package com.audi.leetcode.dp;


/**
 * https://leetcode.com/problems/longest-arithmetic-subsequence/
 * <p>
 * 题目给出了一个整型数组，求这个数组中数字能够组成的最长等差子数组，返回这个最长子数组的长度
 * <p>
 * Input: nums = [3,6,9,12]
 * Output: 4
 * Explanation:
 * The whole array is an arithmetic sequence with steps of length = 3.
 * <p>
 * 这个题其实可以先求出相邻元素的差值，组成一个新的数组，然后按照「最长连续公共子序列」的解法求解即可
 * <p>
 * Input: nums = [9,4,7,2,10]
 * Output: 3
 * Explanation:
 * The longest arithmetic subsequence is [4,7,10].
 * <p>
 * 上面的思路不对，符合条件的元素可以是不连续的，比如上面的示例
 *
 * @author: WangQuanzhou
 * @date: 2021-10-20 8:22 AM
 */
public class LongestArithmeticSubsequence {

    /**
     * 参考
     * https://www.cnblogs.com/grandyang/p/14399899.html
     * <p>
     * https://www.bilibili.com/video/BV1dX4y1F7Pb?from=search&seid=12068979259900167544&spm_id_from=333.337.0.0
     * <p>
     * 大概的思路其实就是利用二维数组，按照等差的差值将数组分成了很多类，二维数组的每一列都是一类
     * 然后在每个单独的类上去统计最长的等差长度
     *
     * @param nums
     * @return
     */
    public int longestArithSeqLength(int[] nums) {
        // 题设明确指出nums.len > 2 ，所以这里可以不做空判断
        int res = 0;
        int len = nums.length;
        // 这里dp数组要设置的稍微比1000大一点，直接取1000的话会有数组临界问题
        int[][] dp = new int[1100][len];
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                // 因为0 <= nums[i] <= 500，这里+500的话，就不会出现diff为负数的情况，就可以将其作为数组的下标
                int diff = nums[i] - nums[j] + 500;
                dp[diff][j] = dp[diff][i] + 1;
                res = Math.max(res, dp[diff][j]);
            }
        }
        // 这里加1是因为只有一个元素时，等差序列长度也是1，但是上面的二维数组初始化时全是0，因此这里加1进行补偿
        return res + 1;
    }

    public static void main(String[] args) {
    }
}
