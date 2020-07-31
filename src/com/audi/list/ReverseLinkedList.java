package com.audi.list;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 * <p>
 * 借助于虚拟头结点
 *
 * @author WangQuanzhou
 * @date 2020-07-31
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        ListNode dummyNode = new ListNode();
        dummyNode.next = head;

        while (null != head.next) {

        }

    }

    public static void main(String[] args) {

    }


    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
