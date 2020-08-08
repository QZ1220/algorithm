package com.audi.list;

/**
 * https://leetcode.com/problems/linked-list-cycle/
 * <p>
 * 求链表有没有环
 * <p>
 * 典型的可以使用快慢指针来解决这个问题
 *
 * @author WangQuanzhou
 * @date 2020-08-08
 */
public class LinkedListCycle {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 检测链表是否有环
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        // 链表为空或者链表大小为1，则不可能有环
        if (null == head || null == head.next) {
            return Boolean.FALSE;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public static void main(String[] args) {
        LinkedListCycle listCycleII = new LinkedListCycle();
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        boolean hasCycle = listCycleII.hasCycle(node1);
        System.out.println(hasCycle);
    }

}



