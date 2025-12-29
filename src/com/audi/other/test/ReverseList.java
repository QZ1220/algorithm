package com.audi.other.test;

import com.audi.leetcode.list.bo.ListNode;

/**
 * 实现翻转链表，直接原地翻转，不借助额外的空间
 *
 * @author : wangquanzhou
 * @date : 2025/12/29 15:31
 */
public class ReverseList {

    private ListNode reverse(ListNode root) {
        if (null == root || root.next == null) {
            return root;
        }
        ListNode newHead = null;
        while (root != null) {
            ListNode tmp = root.next;
            root.next = newHead;
            newHead = root;
            root = tmp;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        ReverseList reverseList = new ReverseList();
        ListNode newNode = reverseList.reverse(node1);
        while (newNode != null) {
            System.out.println(newNode.val);
            newNode = newNode.next;
        }
    }
}
