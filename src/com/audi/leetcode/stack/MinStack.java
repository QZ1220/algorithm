package com.audi.leetcode.stack;

import java.util.ArrayList;
import java.util.List;

public class MinStack {

    private int min;
    private List<Integer> data;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        data = new ArrayList<>();
        min = 0;
    }

    public void push(int val) {
        if (data.isEmpty()) {
            min = val;
        } else {
            if (min > val) {
                min = val;
            }
        }
        data.add(val);
    }

    public void pop() {
        if (data.size() > 0) {
            int top = top();
            data.remove(data.size() - 1);
            if (min == top && data.size() > 0) {
                min = data.get(0);
                for (int i = 1; i < data.size(); i++) {
                    if (min > data.get(i)) {
                        min = data.get(i);
                    }
                }
            }
        }
    }

    public int top() {
        return data.get(data.size() - 1);
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
    }
}
