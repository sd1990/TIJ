package org.songdan.tij.holding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HashMap实现分词器
 * 
 * @author Songdan
 * @date 2017/5/13 14:01
 */
public class TokenizerDemo {

    private static Map<String, String> map = new HashMap<>();

    /** 词典中最长词的长度 */
    private static int maxLength = 3;

    static {
        // 可以从数据库中加载或词表中加载
        map.put("中国", "");
        map.put("国人", "");
        map.put("北京", "");
        map.put("中关村", "");
        map.put("海淀", "");
    }

    public static List<String> analyze(String input) {
        List<String> result = new ArrayList<>();
        int length = input.length();
        for (int i = 0; i < length; i++) {
            int endIndex = i + maxLength;
            if (endIndex > length) {
                endIndex = length;
            }
            for (int j = 0; j < maxLength; j++) {
                String substring = input.substring(i, endIndex);
                String target = map.get(substring);
                if (target != null) {
                    result.add(substring);
                    break;
                }else {
                    //endIndex向前一位，要匹配的词逆序一位
                    endIndex -= 1;
                    if (endIndex == i) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> analyze = analyze("中国人民共和国首都是北京，中关村在海淀区。");
        for (String s : analyze) {
            System.out.println(s);
        }

        //拿到错误信息，分析错误信息，找到关键字，然后就OK

    }

}
