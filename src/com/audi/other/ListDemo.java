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
                list.get(i).random = list.get(map.get(ptr.random));
            }
            ptr = ptr.next;
            i++;
        }
        return list.get(0);
    }


    /**
     * merge K sorted list
     * <p>
     * using sort method
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        List<ListNode> list = new ArrayList<>();
        for (ListNode head : lists) {
            while (null != head) {
                list.add(head);
                head = head.next;
            }
        }
        if (list.size() < 1) {
            return null;
        }
        list.sort(Comparator.comparing(v -> v.val));
        for (int i = 1; i < list.size(); i++) {
            list.get(i - 1).next = list.get(i);
        }
        list.get(list.size() - 1).next = null;
        return list.get(0);
    }

    public ListNode mergeTowSortedList(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode newHead = dummyHead;
        while (null != head1 && null != head2) {
            if (head1.val < head2.val) {
                newHead.next = head1;
                head1 = head1.next;
            } else {
                newHead.next = head2;
                head2 = head2.next;
            }
            newHead = newHead.next;
        }
        if (null != head1) {
            newHead.next = head1;
        }
        if (null != head2) {
            newHead.next = head2;
        }
        return dummyHead.next;
    }

    /**
     * using segmentation method
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        if (lists.length == 2) {
            return mergeTowSortedList(lists[0], lists[1]);
        }
        int mid = lists.length / 2;
        ListNode[] leftLists = new ListNode[mid + 1];
        ListNode[] rightLists = new ListNode[mid + 1];

        ListNode leftNode = mergeKLists(leftLists);
        ListNode rightNode = mergeKLists(rightLists);
        return mergeTowSortedList(leftNode, rightNode);
    }

    /**
     * reverse list
     *
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode newHead = null;
        while (null != head) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }

    /**
     * reverse list node which index from m to n
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (null == head || null == head.next) {
            return head;
        }
        int len = n - m + 1;
        ListNode preHead = null;
        ListNode newHead = head;
        while (null != head && (--m) > 0) {
            preHead = head;
            head = head.next;
        }

        ListNode subListTail = head;
        ListNode subListHead = null;
        // reverse sub list
        while (null != head && len > 0) {
            ListNode temp = head.next;
            head.next = subListHead;
            subListHead = head;
            head = temp;
            len--;
        }

        subListTail.next = head;
        if (null == preHead) {
            newHead =subListHead;
        }else {
            preHead.next=subListHead;
        }
        return newHead;
    }


    public static void main(String[] args) {
        Node node0 = new Node(1);
        Node node1 = new Node(2);
        node0.next = node1;
        node0.random = node1;
        node1.random = node1;
        ListDemo demo = new ListDemo();
        Node node = demo.copyRandomList(node0);
        System.out.println(node.val);


    }
}
