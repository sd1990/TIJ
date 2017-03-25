package org.songdan.tij.oom;

/**
 * 测试运行时常量池
 * @author Songdan
 * @date 2017/2/6 16:42
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        //在jdk1.7中String.intern(),如果字符串相等的string对象存在运行时常量池中，返回池中的实例；否则把字符串添加到常量池中并返回该字符串的引用
        String str1 = new StringBuilder("计算机").append("软件").toString();
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str1.intern()==str1);
        System.out.println(str2.intern()==str2);
        System.out.println("=========================");
        String s1 = "hello world";//常量池会记录首次出现的实例引用
        String s2 = "hello world";//获取的是同一个实例引用
        System.out.println(s1 == s2);
        System.out.println(s1 == new String(s1));//new String()会在堆上重新生成一个实例
        System.out.println(new String(s1)==new String(s1));
    }

}
