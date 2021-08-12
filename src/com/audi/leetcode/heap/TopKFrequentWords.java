package com.audi.leetcode.heap;


import java.util.*;

/**
 * https://leetcode.com/problems/top-k-frequent-words/
 * <p>
 * 题目给出了一个字符串数组，希望找出其中字符串出现频率最高的前k个
 * <p>
 * 使用最小堆来实现
 *
 * @author: WangQuanzhou
 * @date: 2021-08-11 8:05 AM
 */
public class TopKFrequentWords {
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

        MinHeap<Freq> minHeap = new MinHeap<>(k);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.add(new Freq(entry.getValue(), entry.getKey()));
            } else {
                Freq head = minHeap.getHead();
                if (entry.getValue() > head.count ||
                        (entry.getValue() == head.count && entry.getKey().compareTo(head.word) < 0)) {
                    minHeap.replace(new Freq(entry.getValue(), entry.getKey()));
                }
            }
        }

        List<String> list = new LinkedList<>();
        for (int i = k - 1; i >= 0; i--) {
            // 因为是小顶堆，因此但是题目要求是按照从大到小的顺序，因此这里需要使用头插法插入list中
            ((LinkedList<String>) list).addFirst(minHeap.pop().word);
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

    /**
     * 最小堆
     *
     * @param <E>
     */
    public class MinHeap<E extends Comparable<E>> {
        private List<E> list;

        MinHeap() {
            list = new ArrayList<>();
        }

        MinHeap(int initCapacity) {
            list = new ArrayList<>(initCapacity);
        }


        public void add(E e) {
            list.add(e);
            if (list.size() > 1) {
                shiftUp(list.size() - 1);
            }
        }

        public int size() {
            return list.size();
        }

        /**
         * 获取堆顶元素，但是不弹出
         *
         * @return
         */
        public E getHead() {
            if (null == list || list.size() == 0) {
                return null;
            }
            return list.get(0);
        }

        /**
         * 弹出堆顶元素
         *
         * @return
         */
        public E pop() {
            if (null == list || list.size() == 0) {
                throw new IllegalArgumentException();
            }
            E e = list.get(0);
            list.set(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);
            if (list.size() > 1) {
                shiftDown(0);
            }
            return e;
        }

        /**
         * 替换堆顶节点
         *
         * @return
         */
        public E replace(E e) {
            if (null == list || list.size() == 0) {
                throw new IllegalArgumentException();
            }
            E ret = list.get(0);
            list.set(0, e);
            if (list.size() > 1) {
                shiftDown(0);
            }
            return ret;
        }

        /**
         * 进行上浮操作，直至满足最小堆的性质
         */
        public void shiftUp(int i) {
            while (parent(i) >= 0) {
                if (list.get(i).compareTo(list.get(parent(i))) >= 0) {
                    break;
                }
                // 交换父子节点
                swap(i, parent(i));
                i = parent(i);
                if (i == 0) {
                    break;
                }
            }
        }

        public void swap(int i, int j) {
            E e = list.get(i);
            list.set(i, list.get(j));
            list.set(j, e);
        }

        /**
         * 进行下沉操作，直至满足最小堆的性质
         */
        public void shiftDown(int i) {
            if (i >= list.size()) {
                throw new IllegalArgumentException();
            }
            while (leftChild(i) < list.size()) {
                int j = leftChild(i);
                // 注意这些条件判断以及下层的compare方法
                if (rightChild(i) < list.size() && list.get(leftChild(i)).compareTo(list.get(rightChild(i))) > 0) {
                    j++;
                }

                if (list.get(i).compareTo(list.get(j)) > 0) {
                    swap(i, j);
                }
                i = j;
            }
        }

        public int leftChild(int i) {
            return i * 2 + 1;
        }

        public int rightChild(int i) {
            return i * 2 + 2;
        }

        public int parent(int i) {
            if (i < 1) {
                throw new IllegalArgumentException();
            }
            return (i - 1) / 2;
        }
    }

    public static void main(String[] args) {

        TopKFrequentWords topKFrequentWords = new TopKFrequentWords();

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
