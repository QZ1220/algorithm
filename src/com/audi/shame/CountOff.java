package com.audi.shame;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 一群人围坐在圆桌上，编号1-n，给一个数字m，从1号开始报数，数到m的人出列，求圆桌人的出列顺序。
 * <p>
 * eg：n=5，m=3
 * 出列顺序：3，1，5，2，4
 * <p>
 * 使用队列来解题
 *
 * @author: WangQuanzhou
 * @date: 2021-09-16 10:21 PM
 */
public class CountOff {

    public List<Integer> countOff(int n, int m) {

        List<Integer> list = new LinkedList<>();
        Queue<Integer> queue = new LinkedBlockingQueue<>();
        for (int i = 0; i < n; i++) {
            queue.add(i + 1);
        }

        int count = 0;
        while (!queue.isEmpty()) {
            while (count < m - 1) {
                count++;
                queue.add(queue.poll());
            }
            list.add(queue.poll());
            count = 0;
        }


        return list;
    }

    public static void main(String[] args) {
        CountOff countOff = new CountOff();
        System.out.println(countOff.countOff(6, 2));
    }
}
