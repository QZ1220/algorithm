package com.audi.leetcode.list;

/**
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 * <p>
 * 这题没有太大的实际意义
 *
 * @author WangQuanzhou
 * @date 2020-08-02
 */
public class IntersectionofTwoLinkedLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (null == headA || null == headB) {
            return null;
        }

        int lenA = size(headA);
        int lenB = size(headB);

        if (lenA > lenB) {
            int distance = lenA - lenB;
            while (distance > 0) {
                distance -= 1;
                headA = headA.next;
            }
        } else if (lenA < lenB) {
            int distance = lenB - lenA;
            while (distance > 0) {
                distance -= 1;
                headB = headB.next;
            }
        }

        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }

            headA = headA.next;
            headB = headB.next;

        }
        return null;
    }

    /**
     * 获取链表大小
     *
     * @param head
     * @return
     */
    private int size(ListNode head) {
        int i = 0;
        while (null != head) {
            i += 1;
            head = head.next;
        }
        return i;
    }


    public static void main(String[] args) {
        ListNode headA = new ListNode(1);
        ListNode headB = new ListNode(2);

        IntersectionofTwoLinkedLists intersectionofTwoLinkedLists = new IntersectionofTwoLinkedLists();
        ListNode node = intersectionofTwoLinkedLists.getIntersectionNode(headA, headB);
        System.out.println();
    }
}
