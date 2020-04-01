package com.audi.other.p3;

import java.util.ArrayList;


/**
 * FIFO
 *
 * @author: WangQuanzhou
 * @date: 2020/2/21 19:52
 */
public class ArrayQueue<E> implements Queue<E> {

    private ArrayList<E> list;

    public ArrayQueue(int size) {
        this.list = new ArrayList<E>(size);
    }

    public ArrayQueue() {
        this.list = new ArrayList<E>(16);
    }

    @Override
    public void push(E e) {
        if (null == e) {
            return;
        }
        list.add(e);
    }

    @Override
    public E pop() throws Exception {
        if (null == list || list.isEmpty()) {
            throw new Exception("list is empty");
        }
        E temp = list.get(0);
        list.remove(0);
        return temp;
    }

    @Override
    public E peek() throws Exception {
        if (null == list || list.isEmpty()) {
            throw new Exception("list is empty");
        }
        return list.get(0);
    }

    @Override
    public Boolean empty() {
        return list.isEmpty();
    }
}
