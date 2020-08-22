package com.audi.leetcode.search;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/matchsticks-to-square/
 * <p>
 * 使用n个不通长度的火柴棍，能否围成一个正方形
 * <p>
 * 这题其实隐含了一个条件就是：摆出正方形到时候，n个火柴棍都得用上
 * <p>
 * 如果题目变成，n个火柴棍可以只使用部分，那么这个题目会更难
 *
 * @author: WangQuanzhou
 * @date: 2020/8/22 10:51
 */
public class MatchstickstoSquare {
    public boolean makesquare(int[] nums) {
        // nums为空或者火柴棍不足4条
        if (null == nums || nums.length < 4) {
            return Boolean.FALSE;
        }

        // 火柴棍总长度不是4的倍数 也不能组成正方形  这里就体现了n个火柴棍要用完
        int total = Arrays.stream(nums).reduce(0, Integer::sum);
        if (total % 4 != 0) {
            return Boolean.FALSE;
        }

        // 从最大的边开始遍历，可以加快搜索到速度
        Arrays.sort(nums);
        int[] bucket = new int[4];
        return put(nums.length - 1, nums, bucket, total / 4);
    }

    // fixme 深搜  很不幸 TLE了  170 / 174 test cases passed.  因为上面的nums数组深度搜索到时候 没有按照从大到小到顺序进行搜索，修改后即可ac
    // Last executed input:
    //[5,5,5,5,16,4,4,4,4,4,3,3,3,3,4]
    // 依次将nums中到数据取出累加到bucket的各项中，累加和最大不能超过target
    private Boolean put(int pos, int[] nums, int[] bucket, long target) {
        if (pos < 0) {
            // 如果四个边长度都相等  且火柴棍已经全部使用完
            return bucket[0] == target && bucket[1] == target && bucket[2] == target && bucket[3] == target;
        }

        // 在4个桶中分别尝试 放入火柴棍
        // 4叉树回溯深度搜索
        for (int i = 0; i < 4; i++) {
            // 如果这个桶放不下了 继续尝试放置下一个桶
            if (bucket[i] + nums[pos] > target) {
                continue;
            }
            // 如果能放 则将这个元素放入桶中
            bucket[i] += nums[pos];
            // 继续尝试取出下一个元素往放入桶内
            if (put(pos - 1, nums, bucket, target)) {
                return Boolean.TRUE;
            }
            // 回溯 拿出上一次放置错误的元素
            bucket[i] -= nums[pos];
        }
        return Boolean.FALSE;
    }


    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 4, 3, 2, 3};
        MatchstickstoSquare matchstickstoSquare = new MatchstickstoSquare();
        System.out.println(matchstickstoSquare.makesquare(nums));
    }
}
