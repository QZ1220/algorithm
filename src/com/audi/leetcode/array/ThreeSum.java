package com.audi.leetcode.array;


import java.util.*;

/**
 * https://leetcode.com/problems/3sum/
 * <p>
 * 和求2个数的和的题类似  需要借助hashmap
 * <p>
 * 如何去除重复的元素呢？
 *
 * @author: WangQuanzhou
 * @date: 2020/4/21 22:31
 */
public class ThreeSum {

    public List<List<Integer>> threeSum2(int[] nums) {
        if (null == nums || nums.length < 3) {
            return null;
        }
        List<List<Integer>> list = new ArrayList<>(128);
        HashMap<Integer, Integer> map = new HashMap<>(256);
        // 用下面的方式 key=数组项  value=数组的下标，会将数组中的重复项覆盖
        // 那么 key=数组下标  value=数组项呢？这样是可以，但是时间复杂度翻倍了，因为每次取数据不再是O(1)而是O(n)
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int key : map.keySet()) {
            sum(-key, map, list);
        }
        return list;
    }

    private void sum(int sum, Map<Integer, Integer> map, List<List<Integer>> list) {
        for (int key : map.keySet()) {
            int rest = sum - key;
            if (!map.containsKey(rest) || map.get(-sum) == map.get(key) || map.get(-sum) == map.get(rest) || map.get(key) == map.get(rest)) {
                continue;
            }
            List<Integer> result = new ArrayList<>();
            result.add(-sum);
            result.add(key);
            result.add(rest);
            sort(result);
            if (!exist(list, result)) {
                list.add(result);
            }
        }
    }

    /**
     * 判断list是否已经存在 去重
     *
     * @param list
     * @param result
     * @return
     */
    private Boolean exist(List<List<Integer>> list, List<Integer> result) {
        for (List<Integer> tmpList : list) {
            if (tmpList.get(0) == result.get(0) && tmpList.get(1) == result.get(1) && tmpList.get(2) == result.get(2)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 三个数 排序nums
     * <p>
     * 从小到大
     *
     * @param nums
     */
    private void sort(List<Integer> nums) {
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(j) < nums.get(i)) {
                    Integer tmp = nums.get(i);
                    nums.set(i, nums.get(j));
                    nums.set(j, tmp);
                }
            }
        }
    }

    /**
     * https://www.jianshu.com/p/1241f7f7697b
     * <p>
     * 参考网上，使用双指针的做法
     * <p>
     * 还是要把握好去重
     *
     * 还可以参考这里：https://programmercarl.com/0015.%E4%B8%89%E6%95%B0%E4%B9%8B%E5%92%8C.html#%E6%80%9D%E8%B7%AF
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }

        Arrays.sort(nums);
        int left, right, temp;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {  // 如果大于0则不需要遍历了。后面的都大于0
                break;
            }
            // 跳过i重复的元素，还是利用的序列的有序性质
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                temp = nums[i] + nums[left] + nums[right];
                if (temp == 0) {
                    List<Integer> list = Arrays.asList(nums[i], nums[left], nums[right]);
                    result.add(list);

                    // 循环校验。由于数组已经有序，所以这个操作可以跳过那些重复元素
                    // 从而可以对结果去重
                    while (left < right && nums[left] == list.get(1)) left++;
                    while (left < right && nums[right] == list.get(2)) right--;
                } else if (temp > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-2, 0, 0, 2, 2};
        int[] arr_2 = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(arr));
        System.out.println(threeSum(arr_2));
    }

//    public static void main(String[] args) {
//        int[] nums = {-1, 0, 1, 2, -1, -4};
//        ThreeSum threeSum = new ThreeSum();
//        System.out.println(threeSum.threeSum(nums));
//        List<Integer> linkedList = new LinkedList<>();
//        linkedList.add(12);
//        linkedList.add(1);
//        linkedList.add(18);
//        threeSum.sort(linkedList);
//        System.out.println(linkedList);
//    }
}
