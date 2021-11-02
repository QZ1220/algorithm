package com.audi.leetcode.list;

import com.audi.leetcode.list.bo.ListNode;

import java.util.HashMap;
import java.util.Map;


/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 * <p>
 * 移除链表中的重复元素，只要重复出现过的元素都需要删除
 * <p>
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 *
 * @author: WangQuanzhou
 * @date: 2021-11-02 1:28 PM
 */
public class RemoveDuplicatesfromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        // sentinel
        ListNode sentinel = new ListNode(0, head);

        // predecessor = the last node
        // before the sublist of duplicates
        ListNode pred = sentinel;

        while (head != null) {
            // if it's a beginning of duplicates sublist
            // skip all duplicates
            if (head.next != null && head.val == head.next.val) {
                // move till the end of duplicates sublist
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // skip all duplicates
                pred.next = head.next;
                // otherwise, move predecessor
            } else {
                pred = pred.next;
            }

            // move forward
            head = head.next;
        }
        return sentinel.next;
    }

    public static void main(String[] args) {
//        ListNode node5 = new ListNode(3);
//        ListNode node4 = new ListNode(2, node5);
//        ListNode node3 = new ListNode(1, node4);
//        ListNode node2 = new ListNode(1, node3);
//        ListNode node1 = new ListNode(1, node2);

        ListNode node3 = new ListNode(2);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        RemoveDuplicatesfromSortedListII sortedListII = new RemoveDuplicatesfromSortedListII();
        ListNode node = sortedListII.deleteDuplicates(node1);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
