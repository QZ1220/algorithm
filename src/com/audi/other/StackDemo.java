package com.audi.other;

import java.util.Stack;

/**
 * 栈相关题目
 * <p>
 * pushed = [1,2,3,4,5]
 * popped = [4,3,5,1,2]
 *
 * @author : wangquanzhou
 * @date : 2023/7/12 23:12
 */
public class StackDemo {

    /**
     * https://leetcode.com/problems/validate-stack-sequences/
     * * pushed = [1,2,3,4,5]
     * * popped = [4,3,5,1,2]
     *
     * @param pushed
     * @param popped
     * @return
     */
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

        while (!stack.isEmpty()&&posOut<popped.length){
            if (stack.pop()!=popped[posOut]){
                return false;
            }
            posOut++;
        }

       return true;
    }

    public static void main(String[] args) {

    }
}
