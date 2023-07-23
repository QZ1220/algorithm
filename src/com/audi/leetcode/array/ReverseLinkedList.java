package com.audi.leetcode.array;


/**
 * https://leetcode.com/problems/reverse-linked-list/
 * <p>
 * 一般这列链表的题 都需要使用虚拟头结点的方式
 *
 * @author: WangQuanzhou
 * @date: 2020/4/19 12:35
 */
public class ReverseLinkedList {
    public ListNode reverseList2(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        // 设置虚拟头结点
        ListNode dummyHead = new ListNode(-1);
        ListNode tail = head;
        dummyHead.next = head;
        head = head.next;
        while (null != head) {
            // 保存临时头结点
            ListNode tempHead = dummyHead.next;
            ListNode next = head.next;
            dummyHead.next = head;
            // 新元素移动到头部 原头部当做第二节点
            head.next = tempHead;
            // 串联起后续节点
            tail.next = next;
            // 继续翻转后续节点
            head = next;
        }
        return dummyHead.next;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode reverseList(ListNode head) {
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

    public static void main(String[] args) {
        ListNode node = new ListNode(-1);
        ListNode head = node;
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        for (int i = 1; i < 20; i++) {
            ListNode listNode = new ListNode(i);
            node.next = listNode;
            node = node.next;
        }

        ListNode reverseHead = reverseLinkedList.reverseList2(head.next);
        while (null != reverseHead) {
            System.out.println(reverseHead.val);
            reverseHead = reverseHead.next;
        }
    }
}
