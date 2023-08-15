package com.audi.leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/restore-ip-addresses/
 * <p>
 * 本题要和https://leetcode.com/problems/palindrome-partitioning/形成对比
 * 重点比较回溯的位置
 * <p>
 * 题目给定一个数字字符串，求字符串可以解析出的合法IP地址的所有可能的结果
 * <p>
 * 使用回溯求解
 *
 * @author : wangquanzhou
 * @date : 2023/8/15 23:13
 */
public class RestoreIPAddresses {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (null == s || s.length() < 4 || s.length() > 12) {
            return res;
        }
        backtrack(s, new ArrayList<>(), res, 0);
        return res;
    }

    private void backtrack(String s, List<String> ipSections, List<String> res, int startIndex) {
        if (startIndex > s.length()) {
            return;
        }
        // 拆分到了合法的IP地址，直接返回
        if (ipSections.size() == 4 && startIndex == s.length()) {
            res.add(String.join(".", ipSections));
            return;
        }
        // (startIndex + i) <= s.length()是为了防止访问超出s
        for (int i = 1; i <= 3 && (startIndex + i) <= s.length(); i++) {
            String section = s.substring(startIndex, startIndex + i);
            if (isValidIpSection(section)) {
                ipSections.add(section);
                backtrack(s, ipSections, res, startIndex + i);
                ipSections.remove(ipSections.size() - 1);
            }
            // 为什么回溯不放在这个位置来做？
            // 因为如果上面的if判断没进，则说明不是一个合法的IP段，那么其后续的IP地址拼接上这个不合法的IP段，也不可能是个合法IP地址
            // 因此回溯只在上面的if判断内部做
        }
    }

    /**
     * 判断某个字符串是否是合法的IP区段
     * <p>
     * 255-true
     * 300-false
     * 011-false
     *
     * @param section
     * @return
     */
    private boolean isValidIpSection(String section) {
        // 合法长度校验
        if (null == section || section.length() < 1 || section.length() > 3) {
            return false;
        }
        // 判断前导0
        if (section.charAt(0) == '0' && section.length() > 1) {
            return false;
        }
        Integer value = Integer.valueOf(section);
        if (value < 0 || value > 255) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "25525511135";
        RestoreIPAddresses addresses = new RestoreIPAddresses();
        System.out.println(addresses.restoreIpAddresses(s));
    }
}
