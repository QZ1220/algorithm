package com.audi.leetcode.list;

import com.audi.leetcode.list.bo.ListNode;


/**
 * https://leetcode.com/problems/reverse-linked-list-ii/
 * <p>
 * 反转链表，要求只反转指定区间的链表，其余部分的顺序不变
 * <p>
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * <p>
 * 直接借助ReverseLinkedList来求解
 *
 * @author : wangquanzhou
 * @date : 2021/11/15 22:42
 */
public class ReverseLinkedListII {

    /**
     * 翻转链表的一部分
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode tempHead = head;
        // 取出指定区间的list进行翻转
        ListNode leftNode = null;
        // 记录指定区间后面的部分链表
        ListNode subTair = null;
        for (int i = 1; i <= right; i++) {
            if (i == left) {
                leftNode = head;
            }
            if (i == right) {
                // 保存指定区间后面的部分链表
                subTair = head.next;
                head.next=null;
            }
            head=head.next;
        }
        // 子区间链表翻转
        ListNode reverseHead = reverse(leftNode);

        head = tempHead;
        for (int i = 1; i <= right; i++) {
            if (i == left) {
                head = reverseHead;
            }
            head = head.next;
        }
        head.next = subTair;
        return tempHead;
    }

    /**
     * 借助虚拟头结点翻转链表
     *
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head) {
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
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode head = new ListNode(1, node2);
        ReverseLinkedListII reverseLinkedListII = new ReverseLinkedListII();
        ListNode newHead = reverseLinkedListII.reverseBetween(head, 2, 4);
        while (null != newHead) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }
}
