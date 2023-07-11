package com.audi.other;

/**
 * list relevant algorithm
 *
 * @author : wangquanzhou
 * @date : 2023/7/11 21:43
 */
public class ListDemo {
    public ListNode partition(ListNode head, int x) {
        if (null==head||null==head.next){
            return head;
        }
        ListNode leftDummyHead = new ListNode(-1);
        ListNode rightDummyHead = new ListNode(-1);
        ListNode leftHead = leftDummyHead;
        ListNode rightHead = rightDummyHead;
        while (null != head) {
            if (head.val < x) {
                leftHead.next = head;
                leftHead = leftHead.next;
            } else {
                rightHead.next = head;
                rightHead = rightHead.next;
            }
            head = head.next;
        }

        rightHead.next=null;

        // merge two list
        leftHead.next=rightDummyHead.next;
        return leftDummyHead.next;
    }



    public static void main(String[] args) {

    }
}
