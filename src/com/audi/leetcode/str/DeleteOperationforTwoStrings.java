package com.audi.leetcode.str;


/**
 * https://leetcode.com/problems/delete-operation-for-two-strings/
 * <p>
 * 题目给出两个字符串s1、s2，求经过多少步可以使得s1、s2相等
 * 一步只能删除s1或者s2中的一个字母
 * <p>
 * Input: word1 = "sea", word2 = "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 * <p>
 * Input: word1 = "leetcode", word2 = "etco"
 * Output: 4
 * <p>
 * 但看上面的示例，稍微有点误导性，其实删除的字符也可以是不连续，所以本题其实就是LCS的变种
 *
 * <p>
 * 借助LCS那道题的思想，首先求出两个字符串的最长公共子串，然后计算需要删除的字符个数
 * 其实，需要删除字符的个数等于：word1.len+word1.len-2*最长公共子串长度
 *
 * @author: WangQuanzhou
 * @date: 2021-10-26 8:55 PM
 */
public class DeleteOperationforTwoStrings {

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int dp[][] = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return m + n - 2 * dp[m][n];
    }


    public static void main(String[] args) {
    }
}
