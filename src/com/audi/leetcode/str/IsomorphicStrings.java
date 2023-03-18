package com.audi.leetcode.str;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/isomorphic-strings/?envType=study-plan&id=level-1
 * <p>
 * 求两个字符串是否是同构的
 * <p>
 * Input: s = "egg", t = "add"
 * Output: true
 * <p>
 * Input: s = "foo", t = "bar"
 * Output: false
 *
 * @author : wangquanzhou
 * @date : 2023/3/18 11:57
 */
public class IsomorphicStrings {
    /**
     * 使用map存储s和t的字符，key为s的字符，value为t的字符
     * 但是，这种方法会存在一个问题，如果只是使用一个map单向记录的话
     * 对于"badc", "baba"，这种情况会出现错误，因为出现了一对多的情况
     * 解决办法，也很简单，增加一个map，再以t的字符为key，s的字符为value
     * 两个map进行比对
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic2(String s, String t) {
        // 同构字符串，长度必须是相等的
        if (s.length() != t.length()) {
            return Boolean.FALSE;
        }
        Map<Character, Character> map = new HashMap<>(256);
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (map.get(s.charAt(i)).equals(t.charAt(i))) {
                    continue;
                } else {
                    return Boolean.FALSE;
                }
            } else {
                map.put(s.charAt(i), t.charAt(i));
            }
        }
        return Boolean.TRUE;
    }


    /**
     * https://www.cnblogs.com/grandyang/p/4465779.html
     * <p>
     * 参考这位大佬的做法，使用两个大小为256的数组来解决
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        int[] arrS = new int[256];
        int[] arrT = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (arrS[s.charAt(i)] != arrT[t.charAt(i)]) {
                return Boolean.FALSE;
            } else {
                // 记录元素出现的pos index
                arrS[s.charAt(i)] = i + 1;
                arrT[t.charAt(i)] = i + 1;
            }
        }
        return Boolean.TRUE;
    }

    public static void main(String[] args) {
        IsomorphicStrings isomorphicStrings = new IsomorphicStrings();
//        System.out.println(isomorphicStrings.isIsomorphic("bbbaaaba", "aaabbbba"));
//        System.out.println(isomorphicStrings.isIsomorphic("badc", "baba"));
//        System.out.println(isomorphicStrings.isIsomorphic("paper", "title"));
        System.out.println(isomorphicStrings.isIsomorphic("foo", "bar"));
//        System.out.println(isomorphicStrings.isIsomorphic("egg", "add"));
    }
}
