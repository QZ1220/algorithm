package com.audi.leetcode;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/repeated-dna-sequences/
 * <p>
 * 求一个字符串中，出现次数超过2次，且字串长度大于10的字串列表
 * <p>
 * 使用map存储遍历过程中的子串，返回出现次数大于2次的数据
 *
 * @author: WangQuanzhou
 * @date: 2021/11/7 18:36
 */
public class RepeatedDNASequences {

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new LinkedList<>();
        int len = s.length();
        if (len <= 10) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < len - 9; i++) {
            String subStr = s.substring(i, i + 10);
            map.put(subStr, map.getOrDefault(subStr, 0) + 1);
        }
        for (String key : map.keySet()) {
            if (map.get(key) > 1) {
                res.add(key);
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
