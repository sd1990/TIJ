package org.songdan.tij.string;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.GsonBuilder;
import org.songdan.tij.generics.Sets;

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
        String template1 = "hello %s! My name is %s";
        System.out.println(String.format(template1,"宋丹","谢静"));
        System.out.println(String.format(template1));
        String template = "hello ,{0}! My name is {1}";
//        System.out.println(MessageFormat.format(template));
//        System.out.println(MessageFormat.format(template));
    }

    public static void main(String[] args) {
//        format();
//        System.out.println("我是一个中国人".split("")[0]);
//        System.out.println(String.format("%040x",new BigInteger(1,"12754".getBytes(Charset.forName("UTF-8")))));
//        System.out.println(String.format("%x",new BigInteger(1,"12754".getBytes(Charset.forName("UTF-8")))));
        String[] str = {"76010002","37010131","76010003","34070162","76010001","76010006","76010007","36020049","36020005","76010004","76010005","34030100","76010008","36020002","76010009","35020114","34050032","35010026","76020001","76020002","76020003","34010058","76020004","35010021","76020005","76020006","76020007","37020015","76020008","76020009","76030002","76010010","36030001","76030001","34070031","34070030","34050009","37010063","37010140","37010102","37010103","36030074","35010018","37010032","34070141","34070020","34070021","37010035","36020021","34070104","34050011","35020059","34070022","36030027","34040008","35020060","36030066","36030064","41050015","34050024","35020101","35020020","76040002","76040001","35020109","34070137","36020013","76040003","34070057","34070178","37010047","34070179","34070011","76050004","37020001","34080014","37020004","36030051","37020003","76050003","76050002","36030053","76050001"};
        Set<String> bachSet = new HashSet<>();
        for (String s : str) {
            bachSet.add(s);
        }
        String skus = "33050005,33060001,34040125,34050009,34070010,34070011,34070030,34070141,34070199,34080074,35020020,35020059,36020003,36020049,36030027,36030053,37010032,37010047,37010063,37010103,37020001,40020010,40020012,66020005,66020017";
        String[] skuArr = skus.split(",");
        Set<String> skuSet = new HashSet<>();
        for (String s : skuArr) {
            skuSet.add(s);
        }

        System.out.println(Sets.intersection(skuSet,bachSet));
        String str1 = "str";
        String str2 = "str";
        System.out.println(str1==str2);

        System.out.println(String.valueOf(1));
        ArrayList<Object> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        System.out.println(list);
        System.out.println(new GsonBuilder().create().toJson(list));
        System.out.println(new MessageFormat("{0,number,#0.00}--{1,number,#0.00}--{2,number,#0.00}--{3,number,#0.00}").format(new Object[]{1,2.1,2.11,0.1}));
    }
}
随便写点什么吧
