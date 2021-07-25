package com.audi.hash;


import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/replace-words/
 *
 * @author: WangQuanzhou
 * @date: 2021-07-25 12:34 PM
 */
public class ReplaceWords {

    /**
     * 初步思想：
     * 使用hashset存储dictionary的元素
     * 从头便利sentence的每个单词的字符，查看hashset中是否存在，存在就替换
     *
     * @param dictionary
     * @param sentence
     * @return
     */
    public String replaceWords(List<String> dictionary, String sentence) {

        Set<String> dictSet = dictionary.stream().collect(Collectors.toSet());

        List<String> wordList = new LinkedList<>();
        StringBuilder wordBuilder = new StringBuilder().append(sentence.charAt(0));
        for (int i = 1; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            // 如果是空格  将单词添加到wordList
            if (' ' == c) {
                wordList.add(wordBuilder.toString());
                wordBuilder.setLength(0);
                continue;
            }
            // 如果当前字符串在dictionary中存在
            if (dictSet.contains(wordBuilder.toString())) {
                wordList.add(wordBuilder.toString());
                wordBuilder.setLength(0);
                // 继续昂后遍历，知道遇到空格，或者到句子末尾
                while (i < sentence.length()) {
                    if (sentence.charAt(i) == ' ') {
                        break;
                    }
                    i++;
                }
                continue;
            }
            wordBuilder.append(c);
        }

        if (wordBuilder.length() > 0) {
            wordList.add(wordBuilder.toString());
        }

        // 将结果拼接起来
        StringBuilder builder = new StringBuilder();
        for (String s : wordList) {
            builder.append(s).append(' ');
        }

        return builder.toString().trim();
    }

    /**
     * 大神的解法
     * <p>
     * 思路虽然差不多 但是比我的解法要稍微简洁明了一些。
     *
     * @param roots
     * @param sentence
     * @return
     */
    public String replaceWords2(List<String> roots, String sentence) {
        Set<String> rootset = new HashSet();
        for (String root : roots) rootset.add(root);

        StringBuilder ans = new StringBuilder();
        // 首先将字符串拆分成数组，再遍历
        for (String word : sentence.split("\\s+")) {
            String prefix = "";
            for (int i = 1; i <= word.length(); ++i) {
                prefix = word.substring(0, i);
                if (rootset.contains(prefix)) break;
            }
            if (ans.length() > 0) ans.append(" ");
            ans.append(prefix);
        }
        return ans.toString();
    }

    public static void main(String[] args) {

//        String sentence = "aadsfasf absbs bbab cadsfafs";
//        List<String> list = Arrays.asList("a","b","c");


//        String sentence = "the cattle was rattled by the battery";
//        List<String> list = Arrays.asList("cat","bat","rat");

        String sentence = "it is abnormal that this solution is accepted";
        List<String> list = Arrays.asList("ac", "ab");
//
//        String sentence = "ocnn frotscysdyclrc ckcttaceuuxzcghw pxbd oklwhcppuziixpvihihp";
//        List<String> list = Arrays.asList("e", "k", "c", "harqp", "h", "gsafc", "vn", "lqp", "soy", "mr", "x", "iitgm", "sb", "oo", "spj", "gwmly", "iu", "z", "f", "ha", "vds", "v", "vpx", "fir", "t", "xo", "apifm", "tlznm", "kkv", "nxyud", "j", "qp", "omn", "zoxp", "mutu", "i", "nxth", "dwuer", "sadl", "pv", "w", "mding", "mubem", "xsmwc", "vl", "farov", "twfmq", "ljhmr", "q", "bbzs", "kd", "kwc", "a", "buq", "sm", "yi", "nypa", "xwz", "si", "amqx", "iy", "eb", "qvgt", "twy", "rf", "dc", "utt", "mxjfu", "hm", "trz", "lzh", "lref", "qbx", "fmemr", "gil", "go", "qggh", "uud", "trnhf", "gels", "dfdq", "qzkx", "qxw");

        ReplaceWords replaceWords = new ReplaceWords();
        String s = replaceWords.replaceWords(list, sentence);
        System.out.println(s);
    }
}
