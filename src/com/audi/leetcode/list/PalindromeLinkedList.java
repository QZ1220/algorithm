package com.audi.leetcode.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-linked-list/
 * <p>
 * 判断一个单链表是否是回文链表
 *
 * @author : wangquanzhou
 * @date : 2023/7/22 10:13
 */
public class PalindromeLinkedList {

    /**
     * 先深复制原链表得到新的链表，再翻转新的链表，比较两个链表是否一致
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode temp1 = head;
        List<ListNode> list = new ArrayList<>();
        while (null != temp1) {
            list.add(new ListNode(temp1.val));
            temp1 = temp1.next;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).next = list.get(i + 1);
        }


        ListNode temp2 = head;
        ListNode newHead = null;
        while (null != temp2) {
            ListNode next = temp2.next;
            temp2.next = newHead;
            newHead = temp2;
            temp2 = next;
        }

        // 比较newHead开头的链表和head开头的链表是否相等
        ListNode node = list.get(0);
        while (node != null && newHead != null) {
            if (node.val != newHead.val) {
                return false;
            }
            node = node.next;
            newHead = newHead.next;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromeLinkedList linkedList = new PalindromeLinkedList();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(linkedList.isPalindrome(node1));
    }
}
