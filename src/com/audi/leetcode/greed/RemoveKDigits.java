package com.audi.leetcode.greed;


import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/remove-k-digits/
 * <p>
 * 思路：相邻位置的元素进行比较，如果高位的数字大于低位的数字，就需要进行移除。
 * <p>
 * 使用双端队列存储便利过程中的数据，方便后续的便利
 *
 * @author: WangQuanzhou
 * @date: 2020/5/31 16:55
 */
public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        if ("" == num || null == num) {
            return "0";
        }

        // 使用deque双端队列存储数据
        Deque<Character> deque = new LinkedList<>();

        for (int i = 0; i < num.length(); i++) {
            // 相邻位置元素进行比较，删除较大的元素
            while (deque.size() > 0 && deque.getLast() > num.charAt(i) && k > 0) {
                deque.removeLast();
                k--;
            }
            // 如果数字为0，且此时队列为空，那么就忽略这个数字，因为对数字来说，最高位的0没有任何意义
            if (num.charAt(i) != '0' || deque.size() > 0) {
                deque.add(num.charAt(i));
            }
        }

        // 如果上述循环遍历完，k还不为0（比如原num=“12345”），且deque还有数据删除，那么需要继续删除deque
        // 注意：这里删除是从尾部删除，因为上面的while循环保证了越大的数字存储的位置越靠近尾端
        while (deque.size() > 0 && k > 0) {
            deque.removeLast();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pop());
        }
        if (sb.length() == 0) {
            return "0";
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "10";
        RemoveKDigits removeKDigits = new RemoveKDigits();
        System.out.println(removeKDigits.removeKdigits(s, 1));
    }

}
