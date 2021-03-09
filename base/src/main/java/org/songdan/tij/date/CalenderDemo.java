package org.songdan.tij.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
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
//        TimeUnit.SECONDS.sleep(30);
        Date time1 = instance.getTime();
        System.out.println(df.format(time1));
        System.out.println(System.currentTimeMillis());
        LocalDate now = LocalDate.now();
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId);
        long second = now.atStartOfDay(zoneId).toEpochSecond();
        System.out.println(second);
        LocalDate date =
                Instant.ofEpochMilli(second*1000L).atZone(ZoneId.systemDefault()).toLocalDate();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println(dayOfWeek.getValue());
        LocalDate firstDayOfWeek = date.minusDays(dayOfWeek.getValue() - 1);
        LocalDate lastDayOfWeek = date.plusDays(7-dayOfWeek.getValue());
        System.out.println(firstDayOfWeek);
        System.out.println(lastDayOfWeek);
        ChronoField[] values = ChronoField.values();
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(30).withHour(0).withMinute(0).withSecond(0);
        System.out.println(localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond());
        Set<String> allZones = ZoneId.getAvailableZoneIds();
        for (String id : allZones) {
            System.out.println("zoneId:"+id+",name:"+localDateTime.atZone(ZoneId.of(id)).toString());
        }
//        System.out.println(localDateTime.toEpochSecond(ZoneOffset.from(ZoneId.systemDefault())));
        for (ChronoField chronoField : values) {
            try {
                System.out.println("field:" + chronoField + ",show name:" + chronoField.getDisplayName(Locale.CHINA) + "，value:" + localDateTime.getLong(chronoField));
            } catch (Exception e) {
                System.out.println(e);
            }
        }



    }

}
