package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/is-subsequence/?envType=study-plan&id=level-1
 * <p>
 * 判断一个字符串是否是另外一个字符串的子串
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 * <p>
 * 整体的思路和求解最长公共子序列是一样的，只是最终的判断方式略微有点差异，本题可以判断最长公共字序列的长度，是否与短的那个字符串的长度相等就行
 *
 * @author : wangquanzhou
 * @date : 2023/3/18 23:11
 */
public class IsSubsequence {
    /**
     * 遍历s，判断s中字符是否都在t中出现，且相对位置正确
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0) {
            return Boolean.TRUE;
        }
        // 定义dp数组
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];

        int maxCommon=0;
        // 循环遍历
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                if (dp[i][j]>maxCommon){
                    maxCommon=dp[i][j];
                }
            }
        }
        return maxCommon==m;
    }


    public static void main(String[] args) {
        IsSubsequence subsequence = new IsSubsequence();
        System.out.println(subsequence.isSubsequence("abc", "ahbgdc"));
        System.out.println(subsequence.isSubsequence("axc", "ahbgdc"));
        System.out.println(subsequence.isSubsequence("aaaaaa", "bbaaaa"));
    }

}
