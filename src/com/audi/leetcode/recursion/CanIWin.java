package com.audi.leetcode.recursion;

import java.util.Map;

/**
 * https://leetcode.com/problems/can-i-win/
 * 题意要求，A、B两个人从（1-maxChoosableInteger）的数字池里拿取数字，每次的数字用完就从池里移除，desiredTotal表示取出数字的和，
 * 如果A取得的数字使得累加和先达到desiredTotal就返回true，否则返回false。
 * <p>
 * 博弈
 * <p>
 * https://www.jianshu.com/p/5c03aa5ded07
 *
 * @author WangQuanzhou
 * @date 2020-04-23
 */
public class CanIWin {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        // desiredTotal在maxChoosableInteger  第一个玩家直接就赢了
        if (desiredTotal <= maxChoosableInteger) {
            return true;
        }

        // 如果maxChoosableInteger的总和都小于desiredTotal，则说明都不能赢（记住数字是不可以重复使用的）
//        int sum = 1;
//        for (int i = 2; i <= maxChoosableInteger; i++) {
//            sum += i;
//        }

        // 也可以使用等差数列求和公式
        if ((maxChoosableInteger * (maxChoosableInteger + 1) / 2) < desiredTotal) {
            return false;
        }

        return dfs(0, 0, maxChoosableInteger, desiredTotal);

//        Map<Integer, Boolean> record = new HashMap<>();
//        return helper(maxChoosableInteger, desiredTotal, 0, record);
    }

    /**
     * 这里的思路还是没想通。。。
     *
     * @param maxChoosableInteger
     * @param desiredTotal
     * @param used
     * @param record
     * @return
     */
    private boolean helper(int maxChoosableInteger, int desiredTotal, int used, Map<Integer, Boolean> record) {
        if (record.containsKey(used)) {
            return record.get(used);
        }
        for (int i = 0; i < maxChoosableInteger; i++) {
            if ((((1 << i) & used) == 0) && (desiredTotal < i + 2 || !helper(maxChoosableInteger, desiredTotal - i - 1, used | (1 << i), record))) {
                record.put(used, true);
                return true;
            }
        }
        record.put(used, false);
        return false;
    }

    // 注意这里是1左移21位
    int[] visitSet = new int[1 << 21];

    /**
     * 参考下面的视频讲解
     * https://www.bilibili.com/video/BV1fv411j7j7/?spm_id_from=333.337.search-card.all.click&vd_source=d1530fb814268f770330143e24aaf1e6
     *
     * @param state               记忆哪些数字已经被使用过了，可以使用set，当然更高效的是类似本题使用bit
     * @param sum
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    private boolean dfs(int state, int sum, int maxChoosableInteger, int desiredTotal) {
        if (visitSet[state] == 2) {
            return true;
        }
        if (visitSet[state] == 1) {
            return false;
        }

        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (((state >> i) & 1) == 1) {
                continue;
            }
            if (sum + i >= desiredTotal) {
                visitSet[state] = 2;
                return true;
            }
            // 站在对手的角度，如果对手输，那么我就赢
            if (!dfs(state + (1 << i), sum + i, maxChoosableInteger, desiredTotal)) {
                visitSet[state] = 2;
                return true;
            }
        }
        visitSet[state] = 1;
        return false;
    }


    public static void main(String[] args) {
        CanIWin canIWin = new CanIWin();
        boolean res = canIWin.canIWin(10, 40);
        System.out.println(res);
    }
}
