package com.audi.cmb;


import java.util.*;
import java.util.stream.Collectors;

public class StrCount {

    public static String count(String str) {
        if (null == str || str.length() < 1) {
            return str;
        }

        Map<Character, Integer> map = new HashMap<>(str.length());

        // 借助map进行统计
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!map.containsKey(ch)) {
                map.put(ch, 1);
                continue;
            }
            map.put(ch, map.get(ch) + 1);
        }

        List<Character> list = map.keySet().stream().collect(Collectors.toList());
        list.sort(Comparator.naturalOrder());
        StringBuilder sb = new StringBuilder();

        for (Character ch : list) {
            sb.append(ch);
            sb.append(map.get(ch));
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        String s = "wanaxx";
        System.out.println(StrCount.count(s));
    }
}
