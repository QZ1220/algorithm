package com.audi.other;

/**
 * 翻转单链表
 * <p>
 * 1        2       3        4        5
 * head
 *
 * @author : wangquanzhou
 * @date : 2023/7/11 06:57
 */
public class ReverseListII {

    public ListNode reverseBetween(ListNode head, int left, int right) {

        int len = right - left + 1;
        ListNode res = head;
        ListNode preHead = null;
        // 找到要翻转的起始节点
        while (head != null && (--left) > 0) {
            preHead = head;
            head = head.next;
        }

        // 翻转链表的[left,right]区间元素
        ListNode newListTail = head;
        ListNode newHead = null;
        while (head != null && len > 0) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
            len--;
        }

        newListTail.next = head;

        // 如果preHead为空，表示上面的第一个while循环没有执行，也就是说是从链表的开始位置进行翻转
        if (preHead != null) {
            preHead.next = newHead;
        } else {
            res = newHead;
        }
        return res;
    }
}
