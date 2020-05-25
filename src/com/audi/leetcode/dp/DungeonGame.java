package com.audi.leetcode.dp;


/**
 * https://leetcode.com/problems/dungeon-game/
 * <p>
 * 这题最好是反过来思考   即从右下角开始推到左上角，求最小值，最小值大于0，则不需要额外补血，否则就是最小值的消相反数
 *
 * @author: WangQuanzhou
 * @date: 2020/5/16 22:29
 */
public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        // 行数
        int height = dungeon.length;
        // 列数
        int width = dungeon[0].length;

        // dp数组表示从右下角开始，剩余的最小血量，值得注意的是，任何一个dp位置的血量都必须大于0
        int dp[][] = new int[height][width];

        // 右下角位置的dp，若dungeon右下角是正数，那么dp=1即可（因为1就可以保证活到最右下角），
        // 若dungeon右下角是负数，那么就要使用1 - dungeon[height - 1][width - 1]
        dp[height - 1][width - 1] = Math.max(1 - dungeon[height - 1][width - 1], 1);


        // 特殊处理最右边那列
        for (int i = height - 2; i >= 0; i--) {
            dp[i][width - 1] = Math.max(-dungeon[i][width - 1] + dp[i + 1][width - 1], 1);
        }

        // 特殊处理最下边那行
        for (int i = width - 2; i >= 0; i--) {
            dp[height - 1][i] = Math.max(-dungeon[height - 1][i] + dp[height - 1][i + 1], 1);
        }

//        递推公式：dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j])
        for (int i = height - 2; i > -1; i--) {
            for (int j = width - 2; j > -1; j--) {
                dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }

        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] grid = {{-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}};
        DungeonGame dungeonGame = new DungeonGame();
        System.out.println(dungeonGame.calculateMinimumHP(grid));
    }

}
