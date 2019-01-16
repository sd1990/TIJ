package org.songdan.tij.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author: Songdan
 * @create: 2019-01-02 16:42
 **/
public class Demo {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        System.out.println(sdf.format(new Date()));
    }
}
