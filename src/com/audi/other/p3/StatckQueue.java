package com.audi.other.p3;


import java.util.Stack;

/**
 * FILO
 *
 * @author: WangQuanzhou
 * @date: 2020/2/21 19:53
 */
public class StatckQueue<E> implements Queue<E> {
    private Stack<E> stack;

    public StatckQueue() {
        this.stack = new Stack<E>();
    }

    @Override
    public void push(E e) {
        stack.push(e);
    }

    @Override
    public E pop() throws Exception {
        if (stack == null || stack.empty()) {
            throw new Exception("list is empty");
        }
        return stack.pop();
    }

    @Override
    public E peek() throws Exception {
        if (stack == null || stack.empty()) {
            throw new Exception("list is empty");
        }
        return stack.peek();
    }

    @Override
    public Boolean empty() {
        return stack.empty();
    }

}
