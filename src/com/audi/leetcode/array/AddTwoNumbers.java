package com.audi.leetcode.array;


import org.omg.Messaging.SyncScopeHelper;

/**
 * https://leetcode.com/problems/add-two-numbers/
 * <p>
 * 借助了虚拟头结点的思想
 * <p>
 * 其实本题也提供了一种思路，假如list内的数字不是反着排的，求两个list的和，其实，就应该像本题一样翻转一次更好计算
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

        ListNode dummyHead = new ListNode(-1);
        ListNode head = dummyHead;

        int carry = 0;
        while (l1 != null && l2 != null) {
            int v1 = l1.val;
            int v2 = l2.val;
            int res = (v1 + v2 + carry) % 10;
            carry = Math.floorDiv((v1 + v2 + carry), 10);
            ListNode node = new ListNode(res);
            head.next = node;
            head = head.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int res = (l1.val + carry) % 10;
            carry = Math.floorDiv(l1.val + carry, 10);
            ListNode node = new ListNode(res);
            head.next = node;
            head = head.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            int res = (l2.val + carry) % 10;
            carry = Math.floorDiv(l2.val + carry, 10);
            ListNode node = new ListNode(res);
            head.next = node;
            head = head.next;
            l2 = l2.next;
        }

        // 处理最后剩余的进位
        if (carry != 0) {
            ListNode node = new ListNode(carry);
            head.next = node;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
//        ListNode listNode = new ListNode(0);
//        ListNode head = listNode;
//        for (int i = 1; i < 3; i++) {
//            ListNode listNode1 = new ListNode(i);
//            listNode.next = listNode1;
//            listNode = listNode.next;
//        }
//
//        ListNode listNode2 = new ListNode(0);
//        ListNode head2 = listNode2;
//        for (int i = 1; i < 4; i++) {
//            ListNode listNode1 = new ListNode(i);
//            listNode2.next = listNode1;
//            listNode2 = listNode2.next;
//        }

        ListNode node1 = new ListNode(9);
        ListNode node2 = new ListNode(8);
//        ListNode node3 = new ListNode(3);
        node1.next = node2;
//        node2.next = node3;

        ListNode node4 = new ListNode(1);
//        ListNode node5 = new ListNode(6);
//        ListNode node6 = new ListNode(4);
//        node4.next = node5;
//        node5.next = node6;


        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode twoNumbers = addTwoNumbers.addTwoNumbers(node1, node4);
        while (twoNumbers != null) {
            System.out.println(twoNumbers.val);
            twoNumbers = twoNumbers.next;
        }
    }

}
