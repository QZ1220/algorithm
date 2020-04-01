package com.audi.other.p3;


/**
 * 3. Problem Three
 * <p>
 * Implement a Queue of your own design with an array or stack respectively, including push, pop, peek, empty methods.
 *
 * @author: WangQuanzhou
 * @date: 2020/2/21 19:38
 */
public interface Queue<E> {

    void push(E e);

    E pop() throws Exception;

    E peek() throws Exception;

    Boolean empty();
}
