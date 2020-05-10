package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/unique-paths/
 *
 * @author WangQuanzhou
 * @date 2020-04-29
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        // dp表示从左上角走到右下角可能的路线条数
        int[][] dp = new int[m][n];

        // 如果只有一列，那每个节点只能上一个节点直走，因此都是1
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        // 如果只有一行，那每个节点只能上一个节点直走，因此都是1
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 递推公式：每个节点只能从左上或者前面节点走过来
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * 这个题还有第二种解法，使用排列组合的思想。假如6*6的格子，一共需要走12步，到达目的地。12步里有6步是向下的。
     * <p>
     * 因此从12步里选6步出来向下走，就是C(12,6)
     * <p>
     * https://blog.csdn.net/qq_30076791/article/details/50428285
     * <p>
     * https://blog.csdn.net/i_am_bird/article/details/78769913
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        int total = m + n - 2;
        int down = n - 1;
//        int res = 1;
//        for (int i = 1; i <= down; i++) {
//            total = total * (total - down + i);
//            res = res * (i);
//        }
//        return total / res;

        double res = 1;
        for (int i = 1; i <= down; i++) {
            res = res * (total - down + i) / i;
        }
        return (int) res;

    }


    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths(3, 2));
        System.out.println(uniquePaths.uniquePaths2(3, 2));
        System.out.println(uniquePaths.uniquePaths(7, 3));
        System.out.println(uniquePaths.uniquePaths2(7, 3));
        System.out.println(uniquePaths.uniquePaths(1, 1));
        System.out.println(uniquePaths.uniquePaths2(1, 1));
    }
}
