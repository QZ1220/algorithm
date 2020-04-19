package com.audi.leetcode.array;


import org.omg.Messaging.SyncScopeHelper;

/**
 * https://leetcode.com/problems/add-two-numbers/
 * <p>
 * todo
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
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        // 设置虚拟头结点
        ListNode dummyHead = new ListNode(-1);
        ListNode tail = head;
        dummyHead.next = head;
        head = head.next;
        while (null != head) {
            // 保存临时头结点
            ListNode tempHead = dummyHead.next;
            ListNode next = head.next;
            dummyHead.next = head;
            // 新元素移动到头部 原头部当做第二节点
            head.next = tempHead;
            // 串联起后续节点
            tail.next = next;
            // 继续翻转后续节点
            head = next;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(0);
        ListNode head = listNode;
        for (int i = 1; i < 10; i++) {
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
        ListNode reverseNode = addTwoNumbers.reverseList(head);
        while (reverseNode.next != null) {
            System.out.println(reverseNode.val);
            reverseNode = reverseNode.next;
        }
    }

}
