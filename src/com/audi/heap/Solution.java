package com.audi.heap;

import java.util.*;
import java.util.stream.Collectors;

//347. Top K Frequent Elements
public class Solution {

    private class Freq implements Comparable<Freq> {

        public int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            if (this.freq < another.freq)
                return 1;
            else if (this.freq > another.freq)
                return -1;
            else
                return 0;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int num : nums) {
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }

        List<Freq> list = new LinkedList<>();
        for (int key : map.keySet()) {
            if (list.size() < k) {
                list.add(new Freq(key, map.get(key)));
                list = list.stream().sorted(Comparator.comparing(item -> item.freq)).collect(Collectors.toList());
            } else {
                if (map.get(key) <= list.get(0).freq) {
                    continue;
                }
                list.set(0, new Freq(key, map.get(key)));
                list = list.stream().sorted(Comparator.comparing(item -> item.freq)).collect(Collectors.toList());
            }
        }
        return list.stream().map(freq -> freq.e).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {5, 2, 5, 3, 5, 3, 1, 1, 3};
        System.out.println(solution.topKFrequent(nums, 2));
    }
}
