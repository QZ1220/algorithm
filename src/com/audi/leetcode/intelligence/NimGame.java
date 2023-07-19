package com.audi.leetcode.intelligence;

/**
 * https://leetcode.com/problems/nim-game/
 * <p>
 * 一道智力题，有一堆石子，一次最少拿一个，最多拿三个，谁最后拿完石子谁获胜
 * <p>
 * 这是一道智力题or动态规划的题
 * <p>
 * 我们解决这种问题的思路一般都是反着思考：
 * <p>
 * 如果我能赢，那么最后轮到我取石子的时候必须要剩下 1~3 颗石子，这样我才能一把拿完。
 * <p>
 * 如何营造这样的一个局面呢？显然，如果对手拿的时候只剩 4 颗石子，那么无论他怎么拿，总会剩下 1~3 颗石子，我就能赢。
 * <p>
 * 如何逼迫对手面对 4 颗石子呢？要想办法，让我选择的时候还有 5~7 颗石子，这样的话我就有把握让对方不得不面对 4 颗石子。
 * <p>
 * 如何营造 5~7 颗石子的局面呢？让对手面对 8 颗石子，无论他怎么拿，都会给我剩下 57 颗，我就能赢。
 * <p>
 * 这样一直循环下去，我们发现只要踩到 4 的倍数，就落入了圈套，永远逃不出 4 的倍数，而且一定会输。
 * <p>
 * 也就是说，谁拿的时候，剩下的石子是4的倍数，那么就会输，反之则会赢
 *
 * @author : wangquanzhou
 * @date : 2023/7/20 07:16
 */
public class NimGame {

    public boolean canWinNim(int n) {
        // 如果上来就踩到 4 的倍数，那就认输吧
        // 否则，可以把对方控制在 4 的倍数，必胜
        return n % 4 != 0;
    }

    public boolean canWinNim3(int n) {
        return win(n);
    }

    /**
     * 在剩下restN颗石子的情况下，nim此时拿会不会赢
     *
     * 使用递归的时候，会超时
     *
     * @param restN
     * @return
     */
    private boolean win(int restN) {
        if (restN <= 0 || restN == 4) {
            return false;
        }
        if (restN < 4) {
            return true;
        }
        for (int i = 1; i < 4; i++) {
            // 此时应该站在对手的角度考虑问题了
            if (!win(restN - i)) {
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {
        NimGame game = new NimGame();
        System.out.println(game.canWinNim(5));
    }
}
