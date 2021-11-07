package com.audi.leetcode.array;


import java.util.ArrayList;
import java.util.List;

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
        Node tempHead = head;
        // arrayList存储深拷贝以后的数据
        List<Node> arrayList = new ArrayList<>();
        // srcList存储原始list，主要是LinkedList转ArrayList，方便根据index定位元素
        List<Node> srcList = new ArrayList<>();
        arrayList.add(new Node(head.val));
        srcList.add(head);
        int size = 1;
        while (head.next != null) {
            arrayList.add(new Node(head.next.val));
            srcList.add(head.next);
            head = head.next;
            size++;
        }

        head = tempHead;
        for (int i = 0; i < size - 1; i++) {
            arrayList.get(i).next = arrayList.get(i + 1);
            // 根据原始list的random指针定位元素位置
            arrayList.get(i).random = null == head.random ? null : arrayList.get(srcList.indexOf(head.random));
            head = head.next;
        }
        // 处理最后一个节点的random指针
        arrayList.get(size - 1).random = null == head.random ? null : arrayList.get(srcList.indexOf(head.random));
        return arrayList.get(0);
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
