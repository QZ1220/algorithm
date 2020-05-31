package com.audi.leetcode.greed;


import java.util.Arrays;

/**
 * https://leetcode.com/problems/assign-cookies/
 * <p>
 * 贪心思想
 * <p>
 * 使用双指针的思想
 *
 * @author: WangQuanzhou
 * @date: 2020/5/31 15:33
 */
public class AssignCookies {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gi = 0;
        int si = 0;
        while (gi < g.length && si < s.length) {
            if (g[gi] <= s[si]) {
                gi++;
            }
            si++;
        }
        return gi;
    }

    public static void main(String[] args) {
        int[] g = {1, 2, 3};
        int[] s = {1, 1};
        AssignCookies assignCookies = new AssignCookies();
        System.out.println(assignCookies.findContentChildren(g, s));
    }
}
