package com.audi.leetcode.str;

/**
 * https://leetcode.com/problems/is-subsequence/?envType=study-plan&id=level-1
 * <p>
 * 判断一个字符串是否是另外一个字符串的子串
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 *
 * @author : wangquanzhou
 * @date : 2023/3/18 23:11
 */
public class IsSubsequence {
    /**
     * 遍历s，判断s中字符是否都在t中出现，且相对位置正确
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return Boolean.TRUE;
        }
        int pos = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            pos = findPos(c, t, pos);
            if (pos == -1) {
                return Boolean.FALSE;
            }
            pos++;
        }
        return Boolean.TRUE;
    }

    private int findPos(char c, String t, int startPos) {
        for (int i = startPos; i < t.length(); i++) {
            if (t.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        IsSubsequence subsequence = new IsSubsequence();
        System.out.println(subsequence.isSubsequence("abc","ahbgdc"));
        System.out.println(subsequence.isSubsequence("axc","ahbgdc"));
        System.out.println(subsequence.isSubsequence("aaaaaa","bbaaaa"));
    }

}
