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
        if (null == head || null == head.next) {
            return head;
        }
        ListNode dummyNode = new ListNode(-1);
        ListNode tempHead = dummyNode;
        while (null != head && null != head.next) {
            if (head.val == head.next.val) {
                while (null != head && null != head.next && head.val == head.next.val) {
                    head = head.next;
                }
                tempHead.next = head.next;
            } else {
                tempHead.next = head;
                tempHead = tempHead.next;
            }
            head = head.next;
        }
        return dummyNode.next;
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
