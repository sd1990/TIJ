package org.songdan.tij.query.adapter.mysql;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Songdan
 * @create: 2019-04-17 19:45
 **/
public class MysqlToStringBuilder {

    private static ThreadLocal<SimpleDateFormat> formatHolder = new ThreadLocal<SimpleDateFormat>(){

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static String toString(Object obj) {
        if (Boolean.TYPE.isInstance(obj)||Boolean.class.isInstance(obj)) {
            return ((boolean)obj)?"1":"0";
        }
        if (Byte.TYPE.isInstance(obj)||Byte.class.isInstance(obj)) {
            return String.valueOf(((Byte)obj).intValue());
        }
        if (Character.TYPE.isInstance(obj)||Character.class.isInstance(obj)) {
            return "'"+ obj +"'";
        }
        if (Double.TYPE.isInstance(obj)||Double.class.isInstance(obj)) {
            return String.valueOf(BigDecimal.valueOf((double)obj).setScale(2,RoundingMode.HALF_UP).doubleValue());
        }
        if (Float.TYPE.isInstance(obj)||Float.class.isInstance(obj)) {
            return String.valueOf(BigDecimal.valueOf((float)obj).setScale(2,RoundingMode.HALF_UP).floatValue());
        }
        if (Integer.TYPE.isInstance(obj)||Integer.class.isInstance(obj)) {
            return String.valueOf(obj);
        }
        if (Long.TYPE.isInstance(obj)||Long.class.isInstance(obj)) {
            return String.valueOf(obj);
        }
        if (Short.TYPE.isInstance(obj)||Short.class.isInstance(obj)) {
            return String.valueOf(obj);
        }
        if (String.class.isInstance(obj)) {
            return "'"+String.valueOf(obj)+"'";
        }
        if (Date.class.isInstance(obj)) {
            return "'"+formatHolder.get().format((Date)obj)+"'";
        }
        throw new UnsupportedOperationException("not support convert the instance of " + obj.getClass());
    }

}
