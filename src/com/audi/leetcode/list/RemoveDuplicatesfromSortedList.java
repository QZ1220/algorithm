package com.audi.leetcode.list;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
 * <p>
 * 移除链表的重复元素，多个重复元素只保留一个
 *
 * @author : wangquanzhou
 * @date : 2023/7/23 16:03
 */
public class RemoveDuplicatesfromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head;
        while (head != null && head.next != null) {
            ListNode temp = head;
            if (head.val == head.next.val) {
                while (head != null && head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
            }
            temp.next = head.next;
            head = head.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        node1.next = node2;
        node2.next = node3;
        RemoveDuplicatesfromSortedList duplicatesfromSortedList = new RemoveDuplicatesfromSortedList();
        ListNode head = duplicatesfromSortedList.deleteDuplicates(node1);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
