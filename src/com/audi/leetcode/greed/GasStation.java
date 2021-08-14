package com.audi.leetcode.greed;


/**
 * https://leetcode.com/problems/gas-station/
 * <p>
 * 思路：
 * 从第0个gas station开始，判断加的gas是否大于等于cost，
 * 如果是，就继续累加下一个gas station的gas，累减下一个cost
 * 加减的过程中不能出现负数，出现了就以当前节点继续上面的过程，直至到达起点，形成环状
 *
 * @author: WangQuanzhou
 * @date: 2021-08-13 8:24 AM
 */
public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        // 题设保证了数组不会为空

        int startIndex = 0;
        int i = 0;
        int len = gas.length;
        int sum = 0;
        // 这里应用一些数学逻辑：
        // 要想题目有解（能环形开），最差的情况就是从gas的最后一个节点开始，
        // 下一次又达到最后一个节点的时候，刚好是2*len
        while (i < 2 * len) {
            sum = sum + gas[i % len] - cost[i % len];
            if (sum < 0) {
                startIndex = i + 1;
                sum = 0;
            }
            i++;
        }
        // 如果startIndex大于了len，就不可能环形开
        if (startIndex < len) {
            return startIndex;
        } else {
            return -1;
        }
    }

    /**
     * 下面这种解法性能会好一些，减少了部分不要的循环
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        // 题设保证了数组不会为空

        int startIndex = 0;
        int i = 0;
        int len = gas.length;
        int sum = 0;
        // 这里应用一些数学逻辑：
        // 要想题目有解（能环形开），最差的情况就是从gas的最后一个节点开始，
        // 下一次又达到最后一个节点的时候，刚好是2*len
        while (i < 2 * len) {
            sum = sum + gas[i % len] - cost[i % len];
            if (sum < 0) {
                startIndex = i + 1;
                sum = 0;
                if (startIndex >= len) {
                    return -1;
                }
            }
            i++;
        }
        return startIndex;
    }

    public static void main(String[] args) {
    }
}
