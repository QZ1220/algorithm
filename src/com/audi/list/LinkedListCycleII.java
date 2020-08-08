package com.audi.list;

/**
 * https://leetcode.com/problems/linked-list-cycle-ii/
 *
 * @author WangQuanzhou
 * @date 2020-08-08
 */
public class LinkedListCycleII {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 检测链表是否有环，有环则返回环的起始节点
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        // 链表为空或者链表大小为1，则不可能有环
        if (null == head || null == head.next) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode meet = null;

        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // 相遇的位置不一定就是相遇的起始位置  这一点需要注意
                meet = slow;
                break;
            }
        }


        slow = head;
        while (slow != null && meet != null) {
            if (slow == meet) {
                return slow;
            }
            slow = slow.next;
            meet = meet.next;
        }
        return null;
    }

    public static void main(String[] args) {
        LinkedListCycleII listCycleII = new LinkedListCycleII();
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        ListNode listNode = listCycleII.detectCycle(node1);
        System.out.println(listNode.val);
    }

}



