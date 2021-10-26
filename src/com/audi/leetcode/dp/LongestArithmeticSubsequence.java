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
 *
 * Input: nums = [9,4,7,2,10]
 * Output: 3
 * Explanation:
 * The longest arithmetic subsequence is [4,7,10].
 *
 * 上面的思路不对，符合条件的元素可以是不连续的，比如上面的示例
 *
 * @author: WangQuanzhou
 * @date: 2021-10-20 8:22 AM
 */
public class LongestArithmeticSubsequence {

    public int longestArithSeqLength(int[] nums) {
        return 1;
    }

    public static void main(String[] args) {
    }
}
