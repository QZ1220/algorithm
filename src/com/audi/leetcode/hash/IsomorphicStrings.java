package com.audi.leetcode.hash;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/isomorphic-strings/
 * <p>
 * 判断输入的两个字符串是否是同构的
 *
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 * <p>
 * Input: s = "paper", t = "title"
 * Output: true
 *
 * @author : wangquanzhou
 * @date : 2021/11/16 22:54
 */
public class IsomorphicStrings {

    /**
     * 下面的算法在
     * "badc"
     * "baba"
     * 的测试用例下回失败
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic2(String s, String t) {
        // 题设s、t长度大于0，且二者长度相等
        int len = s.length();
        if (len == 1) {
            return Boolean.TRUE;
        }
        for (int i = 0; i < len - 1; i++) {
            if ((s.charAt(i) == s.charAt(i + 1) && t.charAt(i) == t.charAt(i + 1))
                    || (s.charAt(i) != s.charAt(i + 1) && t.charAt(i) != t.charAt(i + 1))) {
                continue;
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }


    /**
     * 借助hash表的思想求解
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        // 题设s、t长度大于0，且二者长度相等
        int len = s.length();
        if (len == 1) {
            return Boolean.TRUE;
        }
        Map<Character, List<Integer>> mapS = new HashMap<>(len);
        Map<Character, List<Integer>> mapT = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            if (mapS.containsKey(cs)) {
                List<Integer> listS = mapS.get(cs);
                listS.add(i);
                mapS.put(cs, listS);
            } else {
                List<Integer> listS = new LinkedList<>();
                listS.add(i);
                mapS.put(cs, listS);
            }

            if (mapT.containsKey(ct)) {
                List<Integer> listT = mapT.get(ct);
                listT.add(i);
                mapT.put(ct, listT);
            } else {
                List<Integer> listT = new LinkedList<>();
                listT.add(i);
                mapT.put(ct, listT);
            }
        }

        for (int i = 0; i < len; i++) {
            List<Integer> listS = mapS.get(s.charAt(i));
            List<Integer> listT = mapT.get(t.charAt(i));
            if (listS.size() != listT.size()) {
                return Boolean.FALSE;
            }
            for (int j = 0; j < listS.size(); j++) {
                if (listS.get(j) != listT.get(j)) {
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }


    public static void main(String[] args) {
        String s = "egg";
        String t = "add";
        IsomorphicStrings strings = new IsomorphicStrings();
        System.out.println(strings.isIsomorphic(s, t));
    }
}
