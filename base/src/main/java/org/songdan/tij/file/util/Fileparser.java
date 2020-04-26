package org.songdan.tij.file.util;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 24 十一月 2017
 */
public class Fileparser {

    public static void main(String[] args) throws IOException, URISyntaxException {
        File file = new File("/Users/songdan/github/TIJ/base/src/main/java/org/songdan/tij/file/util/write_all.log");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            List<String> lines = extractSingleStr(reader);
            Set<String> poiIds = new HashSet<>();
            for (int i = 0; i < lines.size(); i++) {
                String str = lines.get(i);
                String[] strs = str.split(",");
                String[] wmPoiIds = strs[0].split(":");
                poiIds.add(wmPoiIds[1]);
            }
            System.out.println(poiIds.size());
            System.out.println(poiIds);
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
