package org.songdan.tij.algorithm;

import java.util.Base64;

/**
 * @author Songdan
 * @date 2017/7/19 19:17
 */
public class QRTest {

    public static void main(String[] args) {
        String str = "$015YyX5Lqs56Wo6YCa56eR5oqA5pyJ6ZmQ5YWs5Y+4PC8+OTExMTAxMDhNQTAwNDMzNjVNPC8+PC8+PC8+MjU2Qw==$";
//        String str = "&015YyX5Lqs56Wo6YCa56eR5oqA5pyJ6ZmQ5YWs5Y+4PC8+OTExMTAxMDhNQTAwNDMzNjVNPC8+PC8+PC8+MjU2Qw==&";
//        String str = "&015a6L5Li5PC8+MTIzNDU2Nzg5MDEyMzQ1NjwvPjwvPjwvPkU1MTE=&";
        String substring = str.substring(3, str.length() - 1);
        System.out.println(substring);
        byte[] decode = Base64.getDecoder().decode(substring);
        System.out.println(new String(decode));
    }

    public void test() {
        String str = "$015a6L5Li5PC8+MTIzNDU2Nzg5MDEyMzQ1NjwvPjwvPjwvPkU1MTE=$";
        String substring = str.substring(4, str.length() - 1);
        System.out.println();
    }

}
