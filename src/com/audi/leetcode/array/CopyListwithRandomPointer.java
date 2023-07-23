package com.audi.leetcode.array;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 *
 * @author: WangQuanzhou
 * @date: 2021/11/7 17:38
 */
public class CopyListwithRandomPointer {

    public Node copyRandomList(Node head) {
        if (null == head) {
            return head;
        }
        List<Node> list = new ArrayList<>();
        Map<Node, Integer> map = new HashMap<>();

        Node ptr = head;
        int i = 0;
        while (null != ptr) {
            list.add(new Node(ptr.val));
            map.put(ptr, i);
            ptr = ptr.next;
            i++;
        }

        ptr = head;
        // 在list末尾加一个null元素，可以避免处理链表尾部的情况
        list.add(null);
        i = 0;
        while (null != ptr) {
            list.get(i).next = list.get(i + 1);
            if (null != ptr.random) {
                list.get(i).random = list.get(map.get(ptr.random));
            }
            ptr = ptr.next;
            i++;
        }
        return list.get(0);
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {

    }
}
