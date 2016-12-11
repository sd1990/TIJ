package org.songdan.tij.string;

import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
//        System.out.println(Pattern.compile("[^@#%^*]+").matcher("^").matches());
//        System.out.println("%%%".matches("[^@#%^*]+"));
                    /*字段校验：
        1.考试名称、试卷名称等，可输入的字符为：中文、英文、数字、括号、逗号、顿号（支持中英文）
        2.试题部分，试题题目、答案可输入所有的字符*/
        String input="(你好吗)";
//        Pattern reg = Pattern.compile("[\u4e00-\u9fa5a-zA-Z0-9(),/（），、]+");
//        Pattern reg = Pattern.compile("[\u4e00-\u9fa5]");
//        Matcher matcher = reg.matcher(input);
//        System.out.println(matcher.matches());
//        System.out.println("41132719900220115".matches("(\\d{17}([0-9]|X))|(\\d{15})"));
//        System.out.println("1\n2\n3\n\\\\\\\n!@#$%^&amp;*()\nLLLIII\n李占泉\n123".replace("\n", "<br>"));
//        System.out.println("\\\\\\".replace("\\\\", "\\"));
        
        
        /*System.out.println(target.replaceAll("[^a-zA-Z]+", ""));
        
        Pattern reg2 = Pattern.compile("[^\\W]+");
        
        Matcher matcher2 = reg2.matcher(target);
        
        while(matcher2.find()){
            System.out.println(matcher2.group());
        }*/
        
        String target ="Chrome/23.0.1260.0 Mobile Safari/537.9";
        Pattern reg = Pattern.compile("(\\w+)/");
        
        Matcher matcher = reg.matcher(target);
        while(matcher.find()){
            System.out.println(matcher.group(1));
            System.out.println("---------------------");
        }
        
        String[] split = "hello world , songdan".split(" ");
        System.out.println(Arrays.asList(split));
//        Pattern compile = Pattern.compile("abc+");
//        Pattern compile = Pattern.compile("(abc)+");
        Pattern compile = Pattern.compile("abc+?");
        Matcher matcher2 = compile.matcher("abcabcabc");
        while(matcher2.find()){
            System.out.println(matcher2.group());
        }
        testCP("fhskjfdhskfhksd_10.00_8.00_1.50,afdsahfjkshgkj_0.00_5.00_1.00");
        testContinuousZero();
    }
    
    
    
    private static void testCP(String order){
        Pattern reg = Pattern.compile("([A-Za-z0-9]+)_(\\d+[.]\\d+)(_(\\d+[.]\\d+))+");
        Matcher matcher = reg.matcher(order);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
            System.out.println(matcher.group(4));
            System.out.println("------------------------------");
            
        }
    }

    /**
     * 去除连续的0
     */
    public static void testContinuousZero() {
        String num = "10000100";
        String reg = "([0]+)$";
        System.out.println(num.replaceAll(reg,""));
    }

    @Test
    public void testFileSuffiex() {
//        Pattern pattern = Pattern.compile("(.+)(?=\\.[^.]+)");
        Pattern pattern = Pattern.compile("([^（）.]+)（?([^（）]+)）(\\..+)");
//        Pattern pattern = Pattern.compile("([^（）]+)");
        Matcher matcher = pattern.matcher("温柔（五月天）.mp3");
//        Matcher matcher = pattern.matcher("温柔.mp3");
//        System.out.println(matcher.groupCount());
//        System.out.println(matcher.end());
        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
        }
    }

    @Test
    public void testDemo() {
        System.out.println("hell reg");
    }
}
