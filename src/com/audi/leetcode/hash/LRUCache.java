package com.audi.leetcode.hash;

import java.util.*;

/**
 * https://leetcode.com/problems/lru-cache/
 * <p>
 * 实现LRU算法
 * <p>
 * 借助LinkedHashMap实现，可以解决问题，但是提交leetcode，貌似会TLE
 * <p>
 * 其实也可以自己数组+Map来实现
 *
 * @author : wangquanzhou
 * @date : 2021/12/6 22:35
 */
public class LRUCache {

    // LRU缓存
    Map<Integer, Integer> map;
    LinkedList<Integer> list;
    int maxSize;


    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        list = new LinkedList<>();
        maxSize = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            int index = list.indexOf(key);
            list.remove(index);
            list.addFirst(key);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            int index = list.indexOf(key);
            list.remove(index);
            list.addFirst(key);
            map.put(key,value);
        } else {
            if (list.size() >= maxSize) {
                Integer tmp = list.get(list.size() - 1);
                list.removeLast();
                map.remove(tmp);
            }
            map.put(key, value);
            list.addFirst(key);
        }
    }


    public static void main(String[] args) {
    }
}
