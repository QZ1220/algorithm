package com.audi.leetcode.list;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * <p>
 * 合并K个有序的链表为一个有序的链表
 *
 * @author WangQuanzhou
 * @date 2020-08-08
 */
public class MergekSortedLists {

    public class ListNode {
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

    /**
     * 合并K个有序的链表
     * <p>
     * 本质上来说可以不断调用合并两个链表的方法来实现
     * <p>
     * 其实这个题  还可以使用分治的思想进行解决
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }
        ListNode firstListHead = lists[0];
        for (int i = 1; i < lists.length; i++) {
            ListNode secondListHead = lists[i];
            firstListHead = mergeTwoLists(firstListHead, secondListHead);
        }
        return firstListHead;

    }

    /**
     * 合并K个有序的链表
     * <p>
     * 其实这个题  还可以使用分治的思想进行解决
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }

        if (lists.length == 1) {
            return lists[0];
        }
        if (lists.length == 2) {
            return mergeTwoLists(lists[0], lists[1]);
        }

        // 进行二分处理
        int mid = lists.length / 2;
        ListNode[] lists1 = new ListNode[mid + 1];
        ListNode[] lists2 = new ListNode[mid + 1];
        for (int i = 0; i < mid; i++) {
            lists1[i] = lists[i];
        }
        for (int i = mid; i < lists.length; i++) {
            lists2[i - mid] = lists[i];
        }

        // 分治合并
        ListNode node1 = mergeKLists(lists1);
        ListNode node2 = mergeKLists(lists2);

        // 最后归总 合并
        return mergeTwoLists(node1, node2);
    }

    public static void main(String[] args) {
        int[] ints = new int[3];
        ints[0] = 123;
        System.out.println(ints.length);
    }

    /**
     * 合并两个有序列表
     * <p>
     * 通过添加虚拟节点作为辅助
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (null == l1) {
            return l2;
        }
        if (null == l2) {
            return l1;
        }

        ListNode dummyNode = new ListNode(0);
        ListNode cur = dummyNode;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                cur.next = l2;
                l2 = l2.next;
            } else {
                cur.next = l1;
                l1 = l1.next;
            }
            cur = cur.next;
        }

        if (l1 == null) {
            cur.next = l2;
        }
        if (l2 == null) {
            cur.next = l1;
        }

        return dummyNode.next;
    }
}
