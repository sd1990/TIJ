package org.songdan.tij.file.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.*;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 24 十一月 2017
 */
public class Fileparser2 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/songdan/github/TIJ/base/src/main/java/org/songdan/tij/file/util/poi.txt");
        BufferedReader reader = null;
        try {
            System.out.println("读一整行:");
            // 读取非汉字可用
            // reader = new BufferedReader(new FileReader(file));
            // 读汉字可用
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String str;
            List<String> list = new ArrayList<>();
            String formatStr = "update wm_poi_sp_area set valid = 0 , affect = 0 where id = {0} and wm_poi_id = {1} and wm_logistics_code = {2} and affect = {3} and valid = {4};";
            while ((str = reader.readLine()) != null) {
                String[] split = str.split("\\s+");
                String sql = MessageFormat.format(formatStr, split);
                System.out.println(sql);
            }
//            String join = String.join(",", list);
//            String sqlStr = "select *,from_unixtime() from wm_poi_sp_area where wm_poi_id in ("+join+") and wm_logistics_code = 2010 and (valid = 1 or affect = 1);";
//            System.out.println(sqlStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
