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
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int reachable = 0;
        for(int i = 0; i < nums.length; i ++) {
            if(i > reachable) return false;
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
    }


    public int jumpGameII(int[] nums){
        if (nums.length<2){
            return 0;
        }
        int minJump = 1;
        int currentMaxIndex = nums[0];
        int preMaxIndex = nums[0];
        for (int i=0;i<nums.length;i++){
            if (i>currentMaxIndex){
                minJump++;
                currentMaxIndex=preMaxIndex;
            }
            if (preMaxIndex<(i+nums[i])){
                preMaxIndex=i+nums[i];
            }
        }
        return minJump;
    }




    public static void main(String[] args) {
//        int[] nums = {3,2,1,0,4};
//        int[] nums = {0};
//        int[] nums = {2,3,1,1,4};
        int[] nums = {1,1,1,1};
        GreedDemo greedDemo = new GreedDemo();
        System.out.println(greedDemo.jumpGameII(nums));
    }


}
