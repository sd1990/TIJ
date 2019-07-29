package org.songdan.tij.file.util;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 24 十一月 2017
 */
public class Fileparser {

    public static void main(String[] args) throws IOException, URISyntaxException {
        File file = new File("TIJ/base/target/classes/org/songdan/tij/file/util/123.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            List<String> set = extractSingleStr(reader);
            file = new File("TIJ/base/target/classes/org/songdan/tij/file/util/456.txt");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            List<String> set2 = extractSingleStr(reader);

            System.out.println(set.size());
            System.out.println(set2.size());
            set.removeAll(set2);
            List<List<String>> partition = partition(set, 100);
            System.out.println(partition.size());
            for (List<String> list : partition) {
                String join = String.join(",", list);
                System.out.println("["+join+"]");
            }
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

    public static <T> List<List<T>> partition(List<T> source, int partition) {
        List<List<T>> list = new ArrayList<>(source.size() / partition + 1);
        for (int i = 0; i < source.size(); ) {
            int toIndex = i + partition;
            toIndex = source.size() > toIndex ? toIndex : source.size();
            list.add(source.subList(i, toIndex));
            i = i + partition;
        }
        return list;
    }

    private static List<String> extractSingleStr(BufferedReader reader) throws IOException {
        Set<String> set = new LinkedHashSet<>();
        String str;
        while ((str = reader.readLine()) != null) {
            set.add(str);
        }
        return new ArrayList<>(set);
    }

}
