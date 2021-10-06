package com.audi.leetcode.list;


import com.audi.leetcode.list.bo.ListNode;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * <p>
 * 移除链表的从链表尾部开始起的第n个元素
 * <p>
 * 题设已经给出几个限制条件：
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * 因此不会出现越界的情况，入参的链表也不会为空
 *
 * @author: WangQuanzhou
 * @date: 2021-10-06 8:58 AM
 */
public class RemoveNthNodeFromEndofList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = 1;
        ListNode temp = head;
        // 计算list的大小
        while (temp.next != null) {
            size++;
            temp = temp.next;
        }

        if (n == size) {
            return head.next;
        }

        // 找到应该删除的节点的位置
        int pos = size - n;
        temp = head;
        for (int i = 1; i <= size && temp.next != null; i++) {
            if (pos == i) {
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4, listNode5);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);

        RemoveNthNodeFromEndofList endofList = new RemoveNthNodeFromEndofList();
        ListNode head = endofList.removeNthFromEnd(listNode1, 2);

    }
}
