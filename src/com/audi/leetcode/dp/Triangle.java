package com.audi.leetcode.dp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/triangle/
 * <p>
 * 本题，本质上还是属于一个典型的dp，注意边界条件的判断
 *
 * @author: WangQuanzhou
 * @date: 2020/5/16 21:58
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        for (int i = 1; i < size; i++) {
            List<Integer> subList = triangle.get(i);
            int subSize = subList.size();
            for (int j = 0; j < subSize; j++) {
                // 注意下面的left和right使用的是包装类，不是基本类型
                Integer left = j - 1 < 0 ? null : triangle.get(i - 1).get(j - 1);
                Integer right = j > triangle.get(i - 1).size() - 1 ? null : triangle.get(i - 1).get(j);
                // 不存在左节点
                if (left == null) {
                    subList.set(j, subList.get(j) + right);
                } else if (right == null) {
                    // 不存在右节点
                    subList.set(j, subList.get(j) + left);
                } else {
                    // 从左右节点中选择一个小的，进行累加
                    subList.set(j, subList.get(j) + Math.min(left, right));
                }
            }
        }

        // 求出最后一个list的最小值，即为答案
        List<Integer> lastList = triangle.get(size - 1);
        int min = lastList.get(0);
        for (int i = 1; i < lastList.size(); i++) {
            if (lastList.get(i) < min) {
                min = lastList.get(i);
            }
        }
        return min;
    }

    /**
     * 使用下面的代码  可以避免使用包装类的null判断
     *
     * @param triangle
     * @return
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int size = triangle.size();
        for (int i = 1; i < size; i++) {
            List<Integer> subList = triangle.get(i);
            int subSize = subList.size();
            for (int j = 0; j < subSize; j++) {
                // 不存在左节点
                if (j == 0) {
                    subList.set(j, subList.get(j) + triangle.get(i - 1).get(j));
                } else if (j > triangle.get(i - 1).size() - 1) {
                    // 不存在右节点
                    subList.set(j, subList.get(j) + triangle.get(i - 1).get(j - 1));
                } else {
                    // 从左右节点中选择一个小的，进行累加
                    subList.set(j, subList.get(j) + Math.min(triangle.get(i - 1).get(j - 1), triangle.get(i - 1).get(j)));
                }
            }
        }

        // 求出最后一个list的最小值，即为答案
        List<Integer> lastList = triangle.get(size - 1);
        int min = lastList.get(0);
        for (int i = 1; i < lastList.size(); i++) {
            if (lastList.get(i) < min) {
                min = lastList.get(i);
            }
        }
        return min;
    }

    public static void main(String[] args) {
        List<Integer> firstList = Arrays.asList(7);
        List<Integer> secondList = Arrays.asList(-5, 9);
        List<Integer> thirdList = Arrays.asList(6, 5, 2);
        List<Integer> fourthList = Arrays.asList(-8, -2, -7, 3);
        List<Integer> fifthList = Arrays.asList(-2, 6, -6, -1, 4);
        List<List<Integer>> arrayList = new ArrayList<>();
        arrayList.add(firstList);
        arrayList.add(secondList);
        arrayList.add(thirdList);
        arrayList.add(fourthList);
        arrayList.add(fifthList);
        Triangle triangle = new Triangle();
        System.out.println(triangle.minimumTotal2(arrayList));
    }
}
