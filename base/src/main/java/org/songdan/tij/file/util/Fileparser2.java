package org.songdan.tij.file.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 24 十一月 2017
 */
public class Fileparser2 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/songdan/shelf_code.txt");
        BufferedReader reader = null;
        try {
            System.out.println("读一整行:");
            // 读取非汉字可用
            // reader = new BufferedReader(new FileReader(file));
            // 读汉字可用
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String str;
            Set<String> codeSet = new LinkedHashSet<>();
            while ((str = reader.readLine()) != null) {
                // String s = str.replaceAll("\\s+", "");
                String[] split = str.split("\\s+");
                codeSet.add(split[0]);
            }
            String result = String.join(",", codeSet);
            System.out.println(result);
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
