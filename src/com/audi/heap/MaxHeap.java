package com.audi.heap;

import java.util.*;

/**
 * 最大堆
 *
 * @param <E>
 */
public class MaxHeap<E extends Comparable<E>> {

    private ArrayList<E> data;

    public MaxHeap(int capacity) {
        data = new ArrayList<>(capacity);
    }

    public MaxHeap() {
        data = new ArrayList<>();
    }

    // 返回堆中的元素个数
    public int size() {
        return data.size();
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // 向堆中添加元素
    public void add(E e) {
        data.add(e);
        siftUp(data.size() - 1);
    }

    private void siftUp(int k) {

        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            swap(data, k, parent(k));
            k = parent(k);
        }
    }

    /**
     * 交换data数组的i j位置的元素
     *
     * @param data
     * @param i
     * @param j
     */
    private void swap(ArrayList<E> data, int i, int j) {
        if (i > data.size() - 1 || j > data.size() - 1) {
            return;
        }
        E temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    // 看堆中的最大元素
    public E findMax() {
        if (data.size() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    // 取出堆中最大元素
    public E extractMax() {

        E ret = findMax();

        swap(data, 0, data.size() - 1);
        data.remove(data.size() - 1);
        siftDown(0);

        return ret;
    }

    private void siftDown(int k) {

        // 这里之所以要用左孩子，是因为堆都是向左倾斜的，也就是说堆的左孩子在任何情况下都是存在的（除了堆顶节点）
        while (leftChild(k) < data.size()) {
            int j = leftChild(k); // 在此轮循环中,data[k]和data[j]交换位置
            // 选出左右子树种中较大的元素
            if (j + 1 < data.size() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0)
                j++;

            // data[j] 是 leftChild 和 rightChild 中的最大值
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;

            swap(data, k, j);
            k = j;
        }
    }

    // 取出堆中的最大元素，并且替换成元素e
    public E replace(E e) {

        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

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

        MaxHeap<Freq> maxHeap = new MaxHeap<>();
        for (int key : map.keySet()) {
            if (maxHeap.size() < k)
                maxHeap.add(new Freq(key, map.get(key)));
            else if (map.get(key) > maxHeap.findMax().freq) {
                maxHeap.extractMax();
                maxHeap.add(new Freq(key, map.get(key)));
            }
        }

        List<Integer> res = new LinkedList<>();
        while (!maxHeap.isEmpty())
            res.add(maxHeap.extractMax().e);
        return res;
    }
}
