package com.audi.shame;


/**
 * byteDance
 * 给一个正整数，交换正整数的两个位置的数字，使得得到的正整数最大
 * <p>
 * 42462181
 * 88888881
 * <p>
 * <p>
 * 9899392
 * 9999992
 * 9999382
 * <p>
 * 9783282
 * 9888882
 * 9883272
 * <p>
 * <p>
 * <p>
 * 42482161
 * 88886661
 * <p>
 * 92482161
 * 98886661
 * <p>
 * 82462141
 * <p>
 * 12345
 * 51234
 * <p>
 * 125
 * 512
 * <p>
 * 1800
 * 8100
 * <p>
 * 589
 * 958
 * <p>
 * 5234
 * 5423
 * <p>
 * 908
 * 980
 * <p>
 * <p>
 * 使用一个临时数组保存，输入数字各个位置，从右向左的最大值
 * 比如9783282，那么按照上面的意思，就应该得到9888882
 * 然后，9783282从左向右，找到第一个比当前位置元素大的元素，此处就是第2个位置与倒数第2个位置的元素交换得到9883272
 * 时间复杂度O(N)
 * <p>
 *
 * @author: WangQuanzhou
 * @date: 2021-11-01 8:29 PM
 */
public class Swap {

    public String swapMax(String src) {
        if (null == src || src.length() < 2) {
            return src;
        }
        int length = src.length();
        // 标定最大元素首次出现的位置
        int maxPos = src.length() - 1;
        // 新建一个数组，存储以当前位置为基准，其右边的最大值
        char[] temp = new char[length];
        temp[length - 1] = src.charAt(length - 1);
        // 从输入字符串的右边往左边遍历
        for (int i = length - 2; i >= 0; i--) {
            if (src.charAt(i) > temp[i + 1]) {
                maxPos = i;
                temp[i] = src.charAt(i);
            } else {
                temp[i] = temp[i + 1];
            }
        }

        // 将输入字符串转换为数组，方便后续的元素替换
        char[] srcArray = new char[length];
        for (int i = 0; i < length; i++) {
            srcArray[i] = src.charAt(i);
        }

        for (int i = 0; i < length; i++) {
            if (srcArray[i] == temp[i]) {
                continue;
            }
            // 交换尽量靠左的位置的元素为"最大值"
            char c = src.charAt(i);
            srcArray[i] = src.charAt(maxPos);
            srcArray[maxPos] = c;
            break;
        }
        return new String(srcArray);
    }

    public static void main(String[] args) {

        Swap swap = new Swap();
//        String s = "42462181";
//        String s = "9899392";
//        String s = "12345";
        String s = "54321";
        System.out.println(s);
        System.out.println(swap.swapMax(s));
    }
}
