package org.songdan.tij.string;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.text.MessageFormat;

public class StringDemo {

    public static String unescapeBackslash(String oldStr) {
        //上个字符是否是反斜杠
        boolean saw_backslash = false;
        StringBuilder sb = new StringBuilder(oldStr.length());
        //循环遍历
        char[] charArray = oldStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            //
            if (c == '\\') {
                if (!saw_backslash) {
                    saw_backslash = true;
                }
                else {
                    sb.append(c);
                    saw_backslash = false;
                }
            }
            else {
                switch (c) {
                    case 't':
                        sb.append("\\t");
                        break;
                    case 'n':
                        sb.append("\\n");
                        break;

                    default:
                        sb.append(c);
                        break;
                }
                saw_backslash = false;
            }

        }
        return sb.toString();
    }

    public static void format() {
        String template1 = "hello ,! My name is {1}";
        System.out.println(String.format(template1,"宋丹","谢静"));
        String template = "hello ,{0}! My name is {1}";
        System.out.println(MessageFormat.format(template,"宋丹","谢静"));
    }

    public static void main(String[] args) {
//        format();
        System.out.println("我是一个中国人".split("")[0]);
        System.out.println(String.format("%040x",new BigInteger(1,"12754".getBytes(Charset.forName("UTF-8")))));
        System.out.println(String.format("%x",new BigInteger(1,"12754".getBytes(Charset.forName("UTF-8")))));

    }
}
