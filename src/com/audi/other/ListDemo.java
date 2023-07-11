package com.audi.other;

import java.util.*;

/**
 * list relevant algorithm
 *
 * @author : wangquanzhou
 * @date : 2023/7/11 21:43
 */
public class ListDemo {
    public ListNode partition(ListNode head, int x) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode leftDummyHead = new ListNode(-1);
        ListNode rightDummyHead = new ListNode(-1);
        ListNode leftHead = leftDummyHead;
        ListNode rightHead = rightDummyHead;
        while (null != head) {
            if (head.val < x) {
                leftHead.next = head;
                leftHead = leftHead.next;
            } else {
                rightHead.next = head;
                rightHead = rightHead.next;
            }
            head = head.next;
        }

        rightHead.next = null;

        // merge two list
        leftHead.next = rightDummyHead.next;
        return leftDummyHead.next;
    }

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
                list.get(i).random=list.get(map.get(ptr.random));
            }
            ptr=ptr.next;
            i++;
        }
        return list.get(0);
    }

    public static void main(String[] args) {
        Node node0=new Node(1);
        Node node1=new Node(2);
        node0.next=node1;
        node0.random=node1;
        node1.random=node1;
        ListDemo demo = new ListDemo();
        Node node = demo.copyRandomList(node0);
        System.out.println(node.val);


    }
}
