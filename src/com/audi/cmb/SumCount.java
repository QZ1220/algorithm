package com.audi.cmb;

import java.util.*;


/**
 * 输入一个数组，求数组的子集中，和为24的所有子集，子集不能重复
 *
 * @author: WangQuanzhou
 * @date: 2021-09-04 3:44 PM
 */
public class SumCount {

    public Integer subsetsCount(int[] nums) {
        // 使用排序  避免重复子集
        Arrays.sort(nums);
        int count = 0;
        Set<List<Integer>> set = new HashSet<>();
        // 总共的子集的个数
        int total = 1 << nums.length;
        for (int i = 0; i < total; i++) {
            // 单个子集
            List<Integer> item = new LinkedList<>();
            for (int j = 0; j < nums.length; j++) {
                // 左移操作
                if ((i & (1 << j)) != 0) {
                    item.add(nums[j]);
                }
            }
            if (!set.contains(item) && sum(item) == 24) {
                set.add(item);
                count++;

            }
        }
        return count;
    }

    private Integer sum(List<Integer> list) {
        int sum = 0;
        if (null == list || list.size() < 1) {
            return sum;
        }
        for (Integer i : list) {
            sum += i;
        }
        return sum;
    }


    public static void main(String[] args) {

        SumCount sumCount = new SumCount();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(sumCount.subsetsCount(nums));
    }
}
