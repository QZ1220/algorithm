package com.audi.leetcode.list;


/**
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 * <p>
 * 删除链表中的一个元素，题设限定了删除的元素不是尾结点
 * <p>
 * 需要删除的元素已经直接给出了，但是链表的头节点没有给出
 *
 * @author: WangQuanzhou
 * @date: 2021-10-19 9:27 AM
 */
public class DeleteNodeinaLinkedList {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
    }
}
