package org.songdan.tij.file.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 28 十一月 2017
 */
public class ExcelHandler {

    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy/MM/dd H:mm");
        }
    };

    public static <R> List<R> parseExcel(File file, Function<String, R> parser, Function<String, Result> checker,
            Function<String, Result> headerCheck) {
        try {
            List<R> results = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (i++ == 0) {
                    continue;
                }
                String content = line.substring(0, line.length() - 1);
                if (checker == null) {
                    results.add(parser.apply(content));
                } else {
                    Result result = checker.apply(content);
                    if (result.isOk()) {
                        results.add(parser.apply(content));
                    } else {
                        throw new RuntimeException("第" + (i + 1) + "行:" + result.getCause());
                    }
                }
            }
            return results;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
