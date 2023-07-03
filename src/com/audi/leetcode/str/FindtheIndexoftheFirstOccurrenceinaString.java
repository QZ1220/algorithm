package com.audi.leetcode.str;

/**
 * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 给定两个单词，求一个单词在另外一个单词内的首次出现的位置
 * <p>
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6.
 * The first occurrence is at index 0, so we return 0.
 * <p>
 * Input: haystack = "leetcode", needle = "leeto"
 * Output: -1
 * Explanation: "leeto" did not occur in "leetcode", so we return -1.
 *
 * @author : wangquanzhou
 * @date : 2023/7/3 20:33
 */
public class FindtheIndexoftheFirstOccurrenceinaString {

    public int strStr(String haystack, String needle) {
        int p = 0, t = 0;
        int firstPos = -1;
        while (p < haystack.length() && t < needle.length()) {
            if (haystack.charAt(p) != needle.charAt(t)) {
                p++;
                continue;
            }
            firstPos = p;
            if (contains(haystack.substring(p, haystack.length()), needle)) {
                return firstPos;
            } else {
                firstPos = -1;
                p++;
            }
        }
        return firstPos;
    }

    public boolean contains(String haystack, String needle) {
        int i = 0;
        for (; i < needle.length() && i < haystack.length(); i++) {
            if (needle.charAt(i) != haystack.charAt(i)) {
                return false;
            }
        }
        return i == needle.length() ? true : false;
    }

    public static void main(String[] args) {
//        String s1 = "ssadbutsad";
//        String s2 = "sad";
        String s1 = "s";
        String s2 = "se";
        FindtheIndexoftheFirstOccurrenceinaString occurrenceinaString = new FindtheIndexoftheFirstOccurrenceinaString();
        System.out.println(occurrenceinaString.strStr(s1, s2));
    }
}
