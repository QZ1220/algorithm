package com.audi.leetcode.dp;

/**
 * @author WangQuanzhou
 * @date 2020-04-29
 */
public class Path {

    public int uniquePaths(int m, int n) {
        if (m < 2) {
            return n;
        }
        if (n < 2) {
            return m;
        }
        // dp表示从左上角走到右下角可能的路线条数
        int[][] dp = new int[m][n];

        // 起始节点不需要走，所以有0种可能
        dp[0][0] = 0;

        // 如果只有一行，那每个节点只能上一个节点直走，因此都是1
        for (int i=1;i<m;i++){
            dp[i]
        }
        return 0;
    }

    public static void main(String[] args) {

    }
}
