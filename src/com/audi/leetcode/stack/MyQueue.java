package com.audi.leetcode.stack;

import java.util.Stack;

public class MyQueue {

    Stack<Integer> stack;
    Stack<Integer> tmpStack;

    public MyQueue() {
        stack = new Stack<>();
        tmpStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
    }

    public int pop() {
        tmpStack.clear();
        while (!stack.isEmpty()){
            tmpStack.add(stack.pop());
        }
        Integer res = tmpStack.pop();
        while (!tmpStack.isEmpty()){
            stack.add(tmpStack.pop());
        }
        return res;
    }

    public int peek() {
        tmpStack.clear();
        while (!stack.isEmpty()){
            tmpStack.add(stack.pop());
        }
        Integer res = tmpStack.peek();
        while (!tmpStack.isEmpty()){
            stack.add(tmpStack.pop());
        }
        return res;
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}
