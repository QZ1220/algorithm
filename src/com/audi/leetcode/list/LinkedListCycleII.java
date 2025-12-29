package com.audi.leetcode.list;

/**
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * <p>
 * 求链表有没有环，有的话需要返回环的起始位置
 * <p>
 * 典型的可以使用快慢指针来解决这个问题
 * <p>
 * 或者也可以使用set来解决，但是需要申请额外的内存空间
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

        // 先找到相遇点
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // 相遇的位置不一定就是相遇的起始位置  这一点需要注意
                meet = slow;
                break;
            }
        }


        // 再将慢指针复原到链表的起始位置，然后slow、meet同时走，二者相遇的点就是链表的环的起点
        slow = head;
        // 下面计算相遇的起点位置 借助了一定的数学原理 具体可以参考这张图./list_cycleII.png
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



