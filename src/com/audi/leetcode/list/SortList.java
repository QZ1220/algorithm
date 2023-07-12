package com.audi.leetcode.list;


/**
 * https://leetcode.com/problems/sort-list/
 * <p>
 * 不同于数组排序，本题是链表，使用归并排序
 * <p>
 * 将原始链表拆分为只有一个元素的多个链表，然后进行merge操作
 *
 * @author: WangQuanzhou
 * @date: 2020/10/18 16:14
 */
public class SortList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode sortList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode slow = dummyNode;
        ListNode fast = dummyNode;
        while (null != fast && null != fast.next) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode head2 = slow.next;
        slow.next = null;

        return merge(sortList(head), sortList(head2));
    }

    /**
     * 合并l1和l2，借助虚拟头节点
     *
     * @param l1
     * @param l2
     * @return
     */
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(4);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(1);
        ListNode l4 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        SortList sortList = new SortList();
        ListNode head = sortList.sortList(l1);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

    }
}
