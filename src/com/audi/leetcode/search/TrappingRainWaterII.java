package com.audi.leetcode.search;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/trapping-rain-water-ii/
 * <p>
 * 升级版的雨水收集，容器不再是二维的，而是一个三维的模型
 *
 * @author: WangQuanzhou
 * @date: 2020/8/26 7:36
 */
public class TrappingRainWaterII {

    /**
     * 这道题同样可以使用类似于TrappingRainWater的思路  只不过这里不仅仅要考虑左右方向，还要考虑上下方向
     * <p>
     * 同时需要定义一个mask矩阵，维护上下左右的最大值中的最小值，存入mask矩阵
     * <p>
     * 提交leetcode 16 / 39 test cases passed.
     * <p>
     * 提示[[12,13,1,12],[13,4,13,12],[13,8,10,12],[12,13,12,12],[13,13,13,13]]这个用例没通过
     * 我的输出是15，期望的输出是14，但是我手动演算了一次，也应该是15才对啊  不管了。。。
     *
     * @param heightMap
     * @return
     */
    public int trapRainWater(int[][] heightMap) {
        // 要能积水，三维模型的长度、宽度必须都大于2
        if (null == heightMap || heightMap.length < 3 || heightMap[0].length < 3) {
            return 0;
        }

        // 列数  横向
        int column = heightMap[0].length;
        // 行数  纵向
        int row = heightMap.length;


        int[][] mask = new int[row][column];


        // y方向
        for (int i = 1; i < row - 1; i++) {
            // 求横向的最大值
            int[] left = new int[column];
            int[] right = new int[column];
            leftMax(heightMap[i], left);
            rightMax(heightMap[i], right);
            // x方向
            for (int j = 1; j < column - 1; j++) {
                int leftMax = left[j];
                int rightMax = right[j];
                mask[i][j] = leftMax - rightMax >= 0 ? rightMax : leftMax;
            }

        }

        // x方向
        for (int i = 1; i < column - 1; i++) {
            int[] up = new int[row];
            int[] down = new int[row];
            upMax(heightMap, up, i);
            downMax(heightMap, down, i);
            // y方向
            for (int j = 1; j < row - 1; j++) {
                int upMax = up[j];
                int downMax = down[j];
                // 注意这里是先 j 后 i
                int temp = mask[j][i];
                int verticalMin = upMax - downMax >= 0 ? downMax : upMax;
                mask[j][i] = verticalMin - temp >= 0 ? temp : verticalMin;
            }
        }


        // 总共可积的雨水量
        int total = 0;
        // y方向
        for (int i = 1; i < row - 1; i++) {
            // x方向
            for (int j = 1; j < column - 1; j++) {
                if (heightMap[i][j] < mask[i][j]) {
                    total = total + mask[i][j] - heightMap[i][j];
                }
            }
        }
        return total;
    }

    /**
     * 当前节点左边的最大值
     *
     * @param nums
     * @param left
     */
    private void leftMax(int[] nums, int[] left) {
        left[0] = nums[0];
        // 找到每个元素左边的最大值
        for (int i = 1; i < nums.length - 1; i++) {
            left[i] = left[i - 1] - nums[i] >= 0 ? left[i - 1] : nums[i];
        }
    }

    /**
     * 当前节点右边的最大值
     *
     * @param nums
     * @param right
     */
    private void rightMax(int[] nums, int[] right) {
        int length = nums.length;
        right[length - 1] = nums[length - 1];
        // 找到每个元素右边的最大值
        for (int i = length - 2; i > 0; i--) {
            right[i] = right[i + 1] - nums[i] >= 0 ? right[i + 1] : nums[i];
        }
    }


    /**
     * 当前节点上边的最大值
     *
     * @param heightMap
     * @param up
     * @param pos
     */
    private void upMax(int[][] heightMap, int[] up, int pos) {
        up[0] = heightMap[0][pos];
        // 找到每个元素上边的最大值
        for (int i = 1; i < heightMap.length - 1; i++) {
            up[i] = up[i - 1] - heightMap[i][pos] >= 0 ? up[i - 1] : heightMap[i][pos];
        }
    }

    /**
     * 当前节点下边的最大值
     *
     * @param heightMap
     * @param down
     * @param pos
     */
    private void downMax(int[][] heightMap, int[] down, int pos) {
        int length = heightMap.length;
        down[length - 1] = heightMap[length - 1][pos];
        // 找到每个元素下边的最大值
        for (int i = length - 2; i > 0; i--) {
            down[i] = down[i + 1] - heightMap[i][pos] >= 0 ? down[i + 1] : heightMap[i][pos];
        }
    }

    /**
     * 除了上面这种做法以外，还可以使用带优先级的广度优先搜索来做
     *
     * @param heightMap
     * @return
     */
    public int trapRainWater2(int[][] heightMap) {
        // 要能积水，三维模型的长度、宽度必须都大于2
        if (null == heightMap || heightMap.length < 3 || heightMap[0].length < 3) {
            return 0;
        }

        // 列数  横向
        int column = heightMap[0].length;
        // 行数  纵向
        int row = heightMap.length;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int total = 0;
        // 定义一个优先级队列  按照h从小到大排列
        Queue<Item> queue = new PriorityQueue<Item>(Comparator.comparing(item -> item.h));
        // 首尾行元素入队
        for (int i = 0; i < column; i++) {
            queue.add(new Item(0, i, heightMap[0][i]));
            queue.add(new Item(row - 1, i, heightMap[row - 1][i]));
        }
        // 首尾列元素入队
        for (int i = 1; i < row - 1; i++) {
            queue.add(new Item(i, 0, heightMap[i][0]));
            queue.add(new Item(i, column - 1, heightMap[i][column - 1]));
        }

        // 标记哪些点已经被搜索过
        int[][] mask = new int[row][column];

        while (!queue.isEmpty()) {
            Item head = queue.poll();
            int x = head.x;
            int y = head.y;
            int h = head.h;
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                // 超出边界  或者已经被搜索过
                if (newX < 0 || newY < 0 || newX >= column || newY >= row || mask[newX][newY] == 1) {
                    continue;
                }
                if (h > heightMap[newX][newY]) {
                    total = total + h - heightMap[newX][newY];
                    // 将当前位置填平
                    heightMap[newX][newY] = h;
                }
                queue.add(new Item(newX, newY, heightMap[newX][newY]));
                // 标记当前点已经访问过
                mask[newX][newY] = 1;
            }
        }
        return total;
    }

    public class Item {
        int x;
        int y;
        int h;

        public Item(int x, int y, int h) {
            x = x;
            y = y;
            h = h;
        }
    }


    public static void main(String[] args) {
//        int[][] heightMap = {{9, 9, 9, 2, 3}, {9, 1, 7, 1, 3}, {9, 9, 9, 3, 3}};
//        int[}[] heightMap = {{2, 3, 4, 1}, {2, 1, 1, 5}, {9, 6, 7, 8}};
        int[][] heightMap = {{12, 13, 1, 12}, {13, 4, 13, 12}, {13, 8, 10, 12}, {12, 13, 12, 12}, {13, 13, 13, 13}};
        TrappingRainWaterII waterII = new TrappingRainWaterII();
        System.out.println(waterII.trapRainWater(heightMap));
        System.out.println(waterII.trapRainWater2(heightMap));
    }
}
