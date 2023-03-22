package com.audi.leetcode.list;

/**
 * https://leetcode.com/problems/middle-of-the-linked-list/?envType=study-plan&id=level-1
 * <p>
 * 求一个链表的中间元素，如果有两个中间元素，返回后一个
 * <p>
 * 使用快慢指针求解
 *
 * @author : wangquanzhou
 * @date : 2023/3/22 21:53
 */
public class MiddleoftheLinkedList {

    public ListNode middleNode(ListNode head) {
        if (head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            return head.next;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {

    }
}
