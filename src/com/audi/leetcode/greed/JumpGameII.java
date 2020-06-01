package com.audi.leetcode.greed;


/**
 * https://leetcode.com/problems/jump-game-ii/
 *
 * @author: WangQuanzhou
 * @date: 2020/5/31 19:14
 */
public class JumpGameII {
    public int jump(int[] nums) {
        // 如果数组为空  或者只有一步，那么不需要走（因为起点就在第一步）
        if (null == nums || nums.length == 0 || nums.length == 1) {
            return 0;
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

        int step = 0;

        while (jump < len && jump <= maxIndex) {

            // 维护maxIndex
            if (maxIndex < index[jump]) {
                maxIndex = index[jump];
                step++;
            }

            // 如果当前位置已经可以直接跳到末尾，就直接跳出循环，返回true
            if (index[jump] >= len - 1) {
                // 因为jump从0开始计算，但是实际走的步数需要进行+1操作
                // 下面不能写成jump++
                return ++step;
            }

            jump++;
//            System.out.println("跳到第 " + jump + " 位置");
        }
        return step;
    }

    public static void main(String[] args) {
//        int[] nums = {2, 3, 1, 1, 4};
//        int[] nums = {1, 2};
        int[] nums = {7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3};
//        int[] nums = {1, 2, 1, 1, 1};
//        int[] nums = {0};
        JumpGameII jumpGameII = new JumpGameII();
        System.out.println(jumpGameII.jump(nums));
    }
}
