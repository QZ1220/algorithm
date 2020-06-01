package com.audi.leetcode.greed;


/**
 * https://leetcode.com/problems/wiggle-subsequence/
 * <p>
 * 使用状态机  更为明了
 *
 * @author: WangQuanzhou
 * @date: 2020/5/31 15:46
 */
public class WiggleSubsequence {

    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        final int BEGIN = 0;
        final int UP = 1;
        final int DOWN = 2;

        int STATE = BEGIN;

        // 题目规定，当只有一个元素时，它也是一个摇摆序列
        int maxLen = 1;

        for (int i = 1; i < nums.length; i++) {
            switch (STATE) {
                case BEGIN:
                    // 循环的过程中，需要注意元素相等的情况
                    if (nums[i] > nums[i - 1]) {
                        STATE = UP;
                        maxLen++;
                    }
                    if (nums[i] < nums[i - 1]) {
                        STATE = DOWN;
                        maxLen++;
                    }
                    break;
                case UP:
                    if (nums[i] < nums[i - 1]) {
                        maxLen++;
                        STATE = DOWN;
                    }
                    break;
                case DOWN:
                    if (nums[i] > nums[i - 1]) {
                        maxLen++;
                        STATE = UP;
                    }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1};
        WiggleSubsequence wiggleSubsequence = new WiggleSubsequence();
        System.out.println(wiggleSubsequence.wiggleMaxLength(nums));
    }
}
