package com.audi.leetcode.stack;

import java.util.Stack;

/**
 * 验证栈的顺序性
 *
 * https://leetcode.com/problems/validate-stack-sequences/description/
 *
 * @author : wangquanzhou
 * @date : 2023/5/9 18:53
 */
public class ValidateStackSequences {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int posIn = 0;
        int posOut = 0;
        Stack<Integer> stack = new Stack<>();
        while (posIn < pushed.length && posOut < popped.length) {
            if (!stack.isEmpty() && stack.peek() == popped[posOut]) {
                stack.pop();
                posOut++;
                continue;
            }
            if (pushed[posIn] == popped[posOut]) {
                posIn++;
                posOut++;
                continue;
            }
            stack.push(pushed[posIn]);
            posIn++;
        }

        while (!stack.isEmpty() && posOut < popped.length) {
            if (stack.pop() != popped[posOut]) {
                return false;
            }
            posOut++;
        }
        return true;

    }
}
