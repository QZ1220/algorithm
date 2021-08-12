package com.audi.leetcode.heap;


import java.util.*;

/**
 * https://leetcode.com/problems/top-k-frequent-words/
 * <p>
 * 题目给出了一个字符串数组，希望找出其中字符串出现频率最高的前k个
 * <p>
 * 使用最小堆来实现
 * <p>
 * 这里也可以使用jdk自带的优先队列来实现，下面的代码使用优先队列来解决这个问题
 *
 * @author: WangQuanzhou
 * @date: 2021-08-11 8:05 AM
 */
public class TopKFrequentWords2 {
    public List<String> topKFrequent(String[] words, int k) {
        // 题干保证了words不会为空

        // 统计每个单词的频率
        Map<String, Integer> map = new HashMap<>(128);
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
                continue;
            }
            map.put(word, 1);
        }

        PriorityQueue<Freq> pq = new PriorityQueue<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (pq.size() < k) {
                pq.add(new Freq(entry.getValue(), entry.getKey()));
            } else {
                Freq head = pq.peek();
                if (entry.getValue() > head.count ||
                        (entry.getValue() == head.count && entry.getKey().compareTo(head.word) < 0)) {
                    pq.remove(head);
                    pq.add(new Freq(entry.getValue(), entry.getKey()));
                }
            }
        }

        List<String> list = new LinkedList<>();
        for (int i = k - 1; i >= 0; i--) {
            // 因为是小顶堆，因此但是题目要求是按照从大到小的顺序，因此这里需要使用头插法插入list中
            ((LinkedList<String>) list).addFirst(pq.poll().word);
        }
        return list;
    }

    public class Freq implements Comparable<Freq> {
        int count;
        String word;

        Freq() {
        }

        Freq(int count, String word) {
            this.count = count;
            this.word = word;
        }

        @Override
        public int compareTo(Freq a) {
            if (this.count > a.count) {
                return 1;
            } else if (this.count < a.count) {
                return -1;
            } else {
                // 字典序
                return a.word.compareTo(this.word);
            }

        }
    }

    public static void main(String[] args) {

        TopKFrequentWords2 topKFrequentWords = new TopKFrequentWords2();

//        String[] words = {"b", "c", "a", "b", "c", "a", "e", "y", "f", "p", "k"};
//        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
//        String[] words = {"the", "the", "day", "day", "is", "hj"};
//        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        String[] words = {"glarko", "zlfiwwb", "nsfspyox", "pwqvwmlgri", "qggx", "qrkgmliewc", "zskaqzwo", "zskaqzwo", "ijy", "htpvnmozay", "jqrlad", "ccjel", "qrkgmliewc", "qkjzgws", "fqizrrnmif", "jqrlad", "nbuorw", "qrkgmliewc", "htpvnmozay", "nftk", "glarko", "hdemkfr", "axyak", "hdemkfr", "nsfspyox", "nsfspyox", "qrkgmliewc", "nftk", "nftk", "ccjel", "qrkgmliewc", "ocgjsu", "ijy", "glarko", "nbuorw", "nsfspyox", "qkjzgws", "qkjzgws", "fqizrrnmif", "pwqvwmlgri", "nftk", "qrkgmliewc", "jqrlad", "nftk", "zskaqzwo", "glarko", "nsfspyox", "zlfiwwb", "hwlvqgkdbo", "htpvnmozay", "nsfspyox", "zskaqzwo", "htpvnmozay", "zskaqzwo", "nbuorw", "qkjzgws", "zlfiwwb", "pwqvwmlgri", "zskaqzwo", "qengse", "glarko", "qkjzgws", "pwqvwmlgri", "fqizrrnmif", "nbuorw", "nftk", "ijy", "hdemkfr", "nftk", "qkjzgws", "jqrlad", "nftk", "ccjel", "qggx", "ijy", "qengse", "nftk", "htpvnmozay", "qengse", "eonrg", "qengse", "fqizrrnmif", "hwlvqgkdbo", "qengse", "qengse", "qggx", "qkjzgws", "qggx", "pwqvwmlgri", "htpvnmozay", "qrkgmliewc", "qengse", "fqizrrnmif", "qkjzgws", "qengse", "nftk", "htpvnmozay", "qggx", "zlfiwwb", "bwp", "ocgjsu", "qrkgmliewc", "ccjel", "hdemkfr", "nsfspyox", "hdemkfr", "qggx", "zlfiwwb", "nsfspyox", "ijy", "qkjzgws", "fqizrrnmif", "qkjzgws", "qrkgmliewc", "glarko", "hdemkfr", "pwqvwmlgri"};
        // ["nftk","qkjzgws","qrkgmliewc","nsfspyox","qengse","htpvnmozay","fqizrrnmif","glarko","hdemkfr","pwqvwmlgri","qggx","zskaqzwo","ijy","zlfiwwb"]
        // [qengse, ccjel, fqizrrnmif, qrkgmliewc, nftk, hdemkfr, qkjzgws, qggx, nbuorw, nsfspyox, pwqvwmlgri, axyak, htpvnmozay, glarko]
        List<String> list = topKFrequentWords.topKFrequent(words, 3);
        // ["nftk","qkjzgws","qrkgmliewc"]
//        [nftk, pwqvwmlgri, qkjzgws]
        System.out.println(list.toString());

    }
}
