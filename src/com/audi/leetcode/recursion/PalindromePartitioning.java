package com.audi.leetcode.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-partitioning/
 * <p>
 * 给定一个字符串s，可以将s进行分割成子串，求分割子串是回文串的所有可能的情况
 * <p>
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * <p>
 * Input: s = "a"
 * Output: [["a"]]
 *
 * @author : wangquanzhou
 * @date : 2023/8/14 23:08
 */
public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backTrack(res, new LinkedList<>(), s.toCharArray(), s, 0);
        return res;
    }

    /**
     * 使用回溯的方法求解
     *
     * @param res
     * @param item       保存回文子串
     * @param chars      使用字符数组  加快访问速度
     * @param s
     * @param startIndex
     */
    private void backTrack(List<List<String>> res, List<String> item, char[] chars, String s, int startIndex) {
        if (startIndex >= chars.length) {
            // 注意：item中保存的起始都是回文串，因此这里不需要再做是否是回文串的判断
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = startIndex; i < chars.length; i++) {
            if (isPalindrome(chars, startIndex, i)) {
                // 如果当前子串是回文串，就把当前子串保存到item中
                item.add(s.substring(startIndex, i + 1));
            } else {
                // 注意这里是continue，不是break
                continue;
                // 如果使用break，那么针对测试用例aba，输出[[a, b, a]]，实际上正确的输出应该是[[a, b, a], [aba]]
                // 可以手动画一棵树，推导下回溯的过程
//                break;
            }

            backTrack(res, item, chars, s, i + 1);
            // 这里需要考虑item为空的情况吗？其实不用哈
            // 因为针对一个字符串的子串，至少单个字母是回文串，也就是说item是不会为空的，也就可以执行删除最后元素的操作
            item.remove(item.size() - 1);
        }
    }

    private boolean isPalindrome(char[] chars, int start, int end) {
        while (start <= end) {
            if (chars[start] != chars[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aba";
        PalindromePartitioning partitioning = new PalindromePartitioning();
        System.out.println(partitioning.partition(s));
    }
}
