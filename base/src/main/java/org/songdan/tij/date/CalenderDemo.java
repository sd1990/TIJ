package org.songdan.tij.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Songdan
 * @date 2016/9/9 16:25
 */
public class CalenderDemo {
    static Calendar instance = Calendar.getInstance();

    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");

    public static void main(String[] args) throws InterruptedException {
        Date time = instance.getTime();
        System.out.println(df.format(time));
        System.out.println(df2.format(time));
        System.out.println(System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(30);
        Date time1 = instance.getTime();
        System.out.println(df.format(time1));
        System.out.println(System.currentTimeMillis());


    }

}
