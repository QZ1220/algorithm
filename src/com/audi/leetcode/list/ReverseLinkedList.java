package com.audi.leetcode.list;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 * <p>
 * 借助于虚拟头结点
 *
 * @author WangQuanzhou
 * @date 2020-07-31
 */
public class ReverseLinkedList {

    /**
     * 原地翻转
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        // 注意这里初始为null
        ListNode newHead = null;
        ListNode oldHead = head;
        while (null != oldHead) {
            ListNode next = oldHead.next;
            oldHead.next = newHead;
            newHead = oldHead;
            oldHead = next;
        }
        return newHead;
    }

    /**
     * 借助虚拟头节点进行翻转
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        head = head.next;
        dummyHead.next.next = null;
        while (null != head) {
            ListNode node = head.next;
            head.next = dummyHead.next;
            dummyHead.next = head;
            head = node;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

//        ListNode head = reverseLinkedList.reverseList(node1);
        ListNode head = reverseLinkedList.reverseList2(node1);
        while (null != head) {
            System.out.println(head.val);
            head = head.next;
        }
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
