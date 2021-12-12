package com.audi.leetcode.hash;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
    Map<Integer, Integer> cache;
    int max_size = 0;

    /**
     * 初始化一个指定容量的LRUCache
     *
     * @param capacity cache容量
     */
    public LRUCache(int capacity) {
        cache = new LinkedHashMap<>(capacity, 1.0f, Boolean.TRUE);
        max_size = capacity;
    }

    /**
     * 从cache获取指定key的信息，如果没有则返回-1
     *
     * @param key
     * @return
     */
    public int get(int key) {
        return null == cache.get(key) ? -1 : cache.get(key);
    }

    /**
     * 将键值对存入cache，
     * 如果key存在就覆盖原来的value
     * 如果不存在且cache容量未到capacity时，设置键值对到cache
     * 如果不存在且cache容量已到capacity时，则清理cache中最少使用的键值对，并设置键值对到cache
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (cache.size() >= max_size && !cache.containsKey(key)) {
            Set<Integer> keySet = cache.keySet();
            cache.remove(keySet.toArray()[0]);
        }
        cache.put(key, value);
    }

    public static void main(String[] args) {
    }
}
