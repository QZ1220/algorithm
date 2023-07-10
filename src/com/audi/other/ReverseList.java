package com.audi.other;

/**
 * 翻转单链表
 *
 * @author : wangquanzhou
 * @date : 2023/7/11 06:57
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        if (null == head || head.next == null) {
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
}
