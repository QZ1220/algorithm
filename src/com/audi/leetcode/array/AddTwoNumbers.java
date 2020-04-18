package com.audi.leetcode.array;


import org.omg.Messaging.SyncScopeHelper;

/**
 * https://leetcode.com/problems/add-two-numbers/
 *
 * @author: WangQuanzhou
 * @date: 2020/4/18 19:30
 */
public class AddTwoNumbers {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        }
        if (null == l2) {
            return l1;
        }

        ListNode head = new ListNode(-1);

        return l1;
    }

    /**
     * 翻转链表
     *
     * @param node
     * @return
     */
    private ListNode reverse(ListNode node) {
        if (null == node || node.next == null) {
            return node;
        }
        ListNode head = node;
        // 从第二个节点处开始翻转
        node = node.next;
        while (null != node) {
            ListNode node1 = node.next;
            node.next = head;
            node.next.next = node1;
            head = node;
            node = node1;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(0);
        ListNode head = listNode;
        for (int i = 1; i < 3; i++) {
            ListNode listNode1 = new ListNode(i);
            listNode.next = listNode1;
            listNode = listNode.next;
        }

//        while (head != null) {
//            System.out.println(head.val);
//            head = head.next;
//        }

        System.out.println("翻转后：");
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode reverseNode = addTwoNumbers.reverse(head);
        while (reverseNode.next != null) {
            System.out.println(reverseNode.val);
            reverseNode = reverseNode.next;
        }
    }

}
