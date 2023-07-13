package com.audi.other;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 贪心相关问题
 *
 * @author : wangquanzhou
 * @date : 2023/7/13 07:42
 */
public class GreedDemo {

    /**
     * 分糖果
     *
     * @param g chind's greed
     * @param s cookies
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gi = 0;
        int si = 0;
        while (gi < g.length && si < s.length) {
            if (s[si] >= g[gi]) {
                si++;
                gi++;
                continue;
            }
            si++;
        }
        return gi;
    }

    /**
     * 一个字符串表示的数字num，删除其中k各数字，使其得到的数字最小
     * <p>
     * 借助栈的思想，但是实际使用需要用到双端队列
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        if (null == num || num == "") {
            return "0";
        }
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < num.length(); i++) {
            while (!deque.isEmpty() && k > 0 && num.charAt(i) < deque.getLast()) {
                deque.removeLast();
                k--;
            }

            if (!deque.isEmpty() || num.charAt(i) != '0') {
                deque.addLast(num.charAt(i));
            }
        }

        while (!deque.isEmpty() && k > 0) {
            deque.removeLast();
            k--;
        }

        if (deque.isEmpty()){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }

    /**
     * jump game
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        // 题设保证了nums不会为空
        int[] jump = new int[nums.length];
        for (int i = 0; i <nums.length ; i++) {
            jump[i] = i+nums[i];
        }

        int runner=0;
        int maxIndex = jump[0];
        while (runner<nums.length&&runner<=maxIndex){
            if (maxIndex<jump[runner]){
                maxIndex=jump[runner];
            }
            runner++;
        }
        if (runner>=nums.length){
            return true;
        }
        return false;
    }


    public boolean canJump2(int[] nums) {
        int maxPos = 0;
        for (int i = 0; i < nums.length; ) {
            maxPos = maxPos(nums, 0, (i + nums[i]) >= nums.length ? (nums.length - 1) : (i + nums[i]));
            if (maxPos <= i) {
                return false;
            }
            i = maxPos;
        }
        return true;
    }

    private int maxPos(int[] nums, int left, int right) {
        int maxPos = 0;
        for (int i = left + 1; i <= right; i++) {
            maxPos = Math.max(maxPos, i + nums[i]);
        }
        return maxPos;
    }

    public boolean canJump3(int[] nums) {
        int reachable = 0;
        for(int i = 0; i < nums.length; i ++) {
            if(i > reachable) return false;
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
    }




    public static void main(String[] args) {
//        int[] nums = {3,2,1,0,4};
        int[] nums = {0};
        GreedDemo greedDemo = new GreedDemo();
        System.out.println(greedDemo.canJump3(nums));
    }


}
