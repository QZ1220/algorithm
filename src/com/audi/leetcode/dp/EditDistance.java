package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/edit-distance/
 * <p>
 * 给定两个字符串s1、s2，求将s1变成s2最少需要的操作次数
 * <p>
 * 可以使用的操作方式有，增加一个字母、删除一个字母、替换一个字母
 * <p>
 * 使用动态规划求解
 *
 * @author : wangquanzhou
 * @date : 2023/8/13 22:25
 */
public class EditDistance {

    /**
     * 使用动态规划求解
     * <p>
     * dp[i][j]表示：word1以i-1位置结尾的子串，word2以j-1结尾的子串，两个子串变成一样，最少需要的操作次数
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {

        int len1 = word1.length();
        int len2 = word2.length();

        // 定义dp
        int[][] dp = new int[len1 + 1][len2 + 1];
        // 初始化 第一行
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        // 初始化 第一列
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        // 使用字符数组，加快程序速度
        char[] word1CharArr = word1.toCharArray();
        char[] word2CharArr = word2.toCharArray();

        // 进行递推求解
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1CharArr[i - 1] == word2CharArr[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 对于递推公式的推导：参考
                    // https://www.bilibili.com/video/BV1qv4y1q78f/?spm_id_from=333.788&vd_source=d1530fb814268f770330143e24aaf1e6
                    // https://programmercarl.com/0072.%E7%BC%96%E8%BE%91%E8%B7%9D%E7%A6%BB.html#%E7%AE%97%E6%B3%95%E5%85%AC%E5%BC%80%E8%AF%BE
                    // dp[i - 1][j - 1] + 1 表示替换一个元素，需要的最少操作次数
                    // dp[i][j - 1] + 1 表示删除word1的一个元素，或者增加word2的一个元素，使得二者相等，需要的最少操作次数
                    // dp[i - 1][j] + 1 表示删除word2的一个元素，或者增加word1的一个元素，使得二者相等，需要的最少操作次数
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1));
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {

    }
}
