package com.audi.leetcode.greed;


/**
 * https://leetcode.com/problems/jump-game/
 * <p>
 * 贪心思想，每次跳当前位置可以跳跃的最远位置
 *
 * @author: WangQuanzhou
 * @date: 2020/5/31 18:26
 */
public class JumpGame {

    public boolean canJump(int[] nums) {
        if (null == nums || nums.length == 0) {
            return true;
        }
        int len = nums.length;
        // index[i]表示在第i个位置最远可以跳跃到的位置
        int[] index = new int[len];
        for (int i = 0; i < len; i++) {
            index[i] = i + nums[i];
        }

        // 代表当前所处的位置
        int jump = 0;
        // 表示当前位置可以跳跃的最远位置
        int maxIndex = index[0];

        while (jump < len && jump <= maxIndex) {
            // 如果当前位置已经可以直接跳到末尾，就直接跳出循环，返回true
            if (index[jump] >= len - 1) {
                return true;
            }
            // 维护maxIndex
            if (maxIndex < index[jump]) {
                maxIndex = index[jump];
            }
//            System.out.println("跳到第 " + jump + " 位置");
            jump++;
        }
        // 如果到达了数组的末尾
        if (jump == len) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        JumpGame jumpGame = new JumpGame();
        System.out.println(jumpGame.canJump(nums));
    }
}
