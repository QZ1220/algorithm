package com.audi.leetcode.array;

/**
 * https://leetcode.com/problems/delete-columns-to-make-sorted/
 * <p>
 * 题目给出了一组长度相等的字符串（仅包含小写字母），需要判断这些字符串组成的矩阵，在列方向不是字典序的
 * 输出有多少个这种字符串
 * <p>
 * Input: strs = ["cba","daf","ghi"]
 * Output: 1
 * Explanation: The grid looks as follows:
 * cba
 * daf
 * ghi
 * Columns 0 and 2 are sorted, but column 1 is not, so you only need to delete 1 column.
 * <p>
 * 思路，其实就是判断每个字符串（列方向）的字母ascii码是否是单调递增的
 *
 * @author : wangquanzhou
 * @date : 2023/1/3 14:29
 */
public class DeleteColumns2MakeSorted {


    public int minDeletionSize(String[] strs) {
        int count = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 0; j < strs.length - 1; j++) {
                if (strs[j].charAt(i) > strs[j + 1].charAt(i)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
//        String strs[] = {"cba", "daf", "ghi"};
        String strs[] = {"zyx", "wvu", "tsr"};
        DeleteColumns2MakeSorted makeSorted = new DeleteColumns2MakeSorted();
        System.out.println(makeSorted.minDeletionSize(strs));
    }
}
