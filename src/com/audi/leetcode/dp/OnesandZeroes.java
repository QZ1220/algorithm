package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/ones-and-zeroes/
 * <p>
 * 给定一个字符串数组strs，数组元素是二进制形式的数字字符串，给定数字m、n
 * 要求从strs中选择多个字符串，使得总的0的个数等m，1的个数等于n
 * 求最多有多少个满足条件的字符串
 *
 * @author : wangquanzhou
 * @date : 2023/8/13 16:30
 */
public class OnesandZeroes {

    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        // dp[i][j][k] 表示使用strs的前i个字符串，组成j个0，k个1的字符串的个数
        int[][][] dp = new int[len + 1][m + 1][n + 1];

        for (int i = 1; i <= len; i++) {
            int[] ints = getOneZeroCnt(strs[i - 1]);
            int zeroCnt = ints[0];
            int oneCnt = ints[1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= zeroCnt && k >= oneCnt) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeroCnt][k - oneCnt] + 1);
                    }
                }
            }
        }
        return dp[len][m][n];
    }

    private int[] getOneZeroCnt(String s) {
        int[] arr = new int[2];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                arr[0]++;
            } else {
                arr[1]++;
            }
        }
        return arr;
    }

    public int findMaxForm2(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= len; i++) {
            int[] ints = getOneZeroCnt(strs[i - 1]);
            int zeroCnt = ints[0];
            int oneCnt = ints[1];
            for (int j = m; j >= zeroCnt; j--) {
                for (int k = n; k >= oneCnt; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zeroCnt][k - oneCnt] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {

    }
}
