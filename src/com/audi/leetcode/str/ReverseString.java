package com.audi.leetcode.str;


/**
 * https://leetcode.com/problems/reverse-string/
 * 题目要求对于输入的字符数组进行反序
 * <p>
 * 这个题肯定可以使用栈来做，但是考虑到需要使用额外空间
 * <p>
 * 考虑使用双指针的思想，进行数组的前后交换，知道指针相遇
 *
 * @author: WangQuanzhou
 * @date: 2021/9/30 13:37
 */
public class ReverseString {

    public void reverseString(char[] s) {
        if (null == s || s.length < 2) {
            return;
        }
        int low = 0;
        int high = s.length - 1;
        while (low < high && low < s.length) {
            swap(s, low, high);
            low++;
            high--;
        }
    }

    private void swap(char[] s, int low, int high) {
        char temp = s[low];
        s[low] = s[high];
        s[high] = temp;
    }

    public static void main(String[] args) {

    }
}
