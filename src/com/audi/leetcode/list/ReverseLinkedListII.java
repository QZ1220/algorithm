package com.audi.leetcode.list;

import com.audi.leetcode.list.bo.ListNode;


/**
 * https://leetcode.com/problems/reverse-linked-list-ii/
 * <p>
 * 反转链表，要求只反转指定区间的链表，其余部分的顺序不变
 * <p>
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * <p>
 * 直接借助ReverseLinkedList来求解
 *
 * @author : wangquanzhou
 * @date : 2021/11/15 22:42
 */
public class ReverseLinkedListII {

    /**
     * 翻转链表的一部分
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode tempHead = head;
        // 取出指定区间的list进行翻转
        ListNode leftNode = null;
        // 记录指定区间后面的部分链表
        ListNode subTair = null;
        for (int i = 1; i <= right; i++) {
            if (i == left) {
                leftNode = head;
            }
            if (i == right) {
                // 保存指定区间后面的部分链表
                subTair = head.next;
                head.next = null;
            }
            head = head.next;
        }
        // 子区间链表翻转
        ListNode reverseHead = reverse(leftNode);

        head = tempHead;
        for (int i = 1; i <= right; i++) {
            if (i == left) {
                head = reverseHead;
            }
            head = head.next;
        }
        head.next = subTair;
        return tempHead;
    }

    /**
     * 借助虚拟头结点翻转链表
     *
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        head = head.next;
        dummyHead.next.next = null;
        while (null != head) {
            ListNode node = head.next;
            head.next = dummyHead.next;
            dummyHead.next = head;
            head = node;
        }
        return dummyHead.next;
    }


    /**
     * 注意这个变量要放在reverseListN递归的外面
     */
    ListNode successor = null;

    /**
     * 反转链表的前N个元素
     *
     * @return
     */
    public ListNode reverseListN(ListNode head, int n) {
        if (n == 1) {
            // 递归的退出条件
            successor = head.next;
            return head;
        }
        ListNode last = reverseListN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    /**
     * 反转链表指定区间的元素
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseListN(head, right);
        }
        // 这里递归的意思就相当于：将原来队首的元素弹出不管，针对剩下的队列元素进行翻转，
        // 因此，left、right下标相当于整体往右移动一个元素，所以都要减一
        head.next = reverseBetween2(head.next, left - 1, right - 1);
        return head;
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode head = new ListNode(1, node2);
        ReverseLinkedListII reverseLinkedListII = new ReverseLinkedListII();
        ListNode newHead = reverseLinkedListII.reverseBetween2(head, 2, 4);
        while (null != newHead) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }


    /**
     * 翻转链表的[left,right]区间的元素
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverse(ListNode head, int m, int n) {
        if (null == head || null == head.next) {
            return head;
        }
        int len = n - m + 1;

        // 找到需要翻转的位置
        ListNode newHead = head;
        ListNode preHead = null;
        while (head != null && (--m > 0)) {
            preHead = head;
            head = head.next;
        }

        ListNode tempHead = null;
        ListNode tempTail = head;
        // 开始饭庄[left,right]区间的元素
        while (head != null && len > 0) {
            ListNode temp = head.next;
            head.next = tempHead;
            tempHead = head;
            head = temp;
            len--;
        }

        tempTail.next = head;

        if (preHead == null) {
            newHead = tempHead;
        } else {
            preHead.next = tempHead;
        }


        return newHead;
    }

}
