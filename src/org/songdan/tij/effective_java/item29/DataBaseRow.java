package org.songdan.tij.effective_java.item29;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建类型安全的异构容器
 * Created by PC on 2016/4/27.
 */
public class DataBaseRow {

    private Map<Class<?>, Object> map = new HashMap<>();

    public void set(Column<?> column, Object obj) {
        map.put(column.getType(), obj);
    }

    public <T> T get(Column<T> column) {
        return column.getType().cast(map.get(column.getType()));
    }

    private abstract static class Column<T> {

        private Class<T> type;

        Column() {
            ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
            Type type1 = parameterizedType.getActualTypeArguments()[0];
            type = (Class<T>) type1;
        }

        public Class<T> getType() {
            return type;
        }
    }

    private static class StringColumn extends Column<String> {

    }
    public static void main(String[] args) {
        //        Column<String> c = new IntegerColumn<>();
        Column<String> c = new StringColumn();
        DataBaseRow dataBaseRow = new DataBaseRow();
        dataBaseRow.set(c, "Hello world");
        dataBaseRow.set(c, "明天会更好！");
        System.out.println(dataBaseRow.get(c));
    }

}
