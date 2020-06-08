package com.audi.list;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * <p>
 * 合并有序列表
 *
 * @author WangQuanzhou
 * @date 2020-02-23
 */
public class MergeList<E extends Comparable<E>> {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
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
