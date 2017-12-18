package org.songdan.tij.file.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 24 十一月 2017
 */
public class Fileparser2 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/songdan/Desktop/shelf.txt");
        BufferedReader reader = null;
        try {
            System.out.println("读一整行:");
            // 读取非汉字可用
            // reader = new BufferedReader(new FileReader(file));
            // 读汉字可用
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String str;
            int line = 0;
            StringBuilder builder = new StringBuilder();
            while ((str = reader.readLine()) != null) {
                line++;
                // String s = str.replaceAll("\\s+", "");
                String[] split = str.split("\\s+");
                builder.append(
                        String.format("update shelf_info set bach_entrepot_code = '%s' where bach_shelf_code = '%s';",
                                split[0], split[1]))
                        .append(System.lineSeparator());
            }
            System.out.println(builder.toString());
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
