package com.audi.leetcode.list;

/**
 * 将链表基数位置的元素全部移动到偶数位置元素的前面
 *
 * @author : wangquanzhou
 * @date : 2023/7/12 07:41
 */
public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        ListNode oddDummyHead = new ListNode(-1);
        ListNode oddHead = oddDummyHead;
        ListNode evenDummyHead = new ListNode(-1);
        ListNode evenHead = evenDummyHead;
        int i = 1;
        while (null != head) {
            if (isOdd(i)) {
                oddHead.next = head;
                oddHead = oddHead.next;
            } else {
                evenHead.next = head;
                evenHead = evenHead.next;
            }
            head = head.next;
            i++;
        }
        oddHead.next = evenDummyHead.next;
        evenHead.next = null;
        return oddDummyHead.next;
    }

    private boolean isOdd(int i) {
        return i > (i / 2) * 2;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        OddEvenLinkedList list = new OddEvenLinkedList();
        ListNode head = list.oddEvenList(node1);
        while (null != head) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
