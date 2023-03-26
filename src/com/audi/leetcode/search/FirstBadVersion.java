package com.audi.leetcode.search;

/**
 * https://leetcode.com/problems/first-bad-version/description/?envType=study-plan&id=level-1
 * <p>
 * 典型的二分搜索的题目的变形
 *
 * @author : wangquanzhou
 * @date : 2023/3/26 19:31
 */
public class FirstBadVersion {

    public int firstBadVersion(int n) {
        int left = 1, right = n;
        int firstBadPos = 1;
        while (left < right) {
            int middle = (left + right) / 2;
            if (!isBadVersion(middle)) {
                left = middle + 1;
            } else {
                // 如果middle是bad version  还需要确定这是不是first bad version
//                if (!isBadVersion(middle - 1)) {
//                    return middle;
//                }
//                firstBadPos = middle;
                right = middle - 1;
            }
        }
        return left;
    }

    private boolean isBadVersion(int version) {
        return false;
    }

    public static void main(String[] args) {
        System.out.println(7 / 2);
    }
}
