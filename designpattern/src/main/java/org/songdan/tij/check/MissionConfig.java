package org.songdan.tij.check;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.songdan.tij.check.checker.Checker;
import org.songdan.tij.check.config.CheckConfig;
import org.songdan.tij.check.config.CheckConfigItem;
import org.songdan.tij.designpattern.composite.JsonUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author: Songdan
 * @create: 2019-07-28 15:41
 **/
@Data
public class MissionConfig {
    private CheckConfig buildCheckConfig;

    public static void main(String[] args) throws IOException {
        String json = stringFromFile(new File("/Users/songdan/github/TIJ/designpattern/src/main/java/org/songdan/tij/check/checkConfig.json"));
        System.out.println(JsonUtil.of(json,MissionConfig.class));
    }


    /**
     * Reads file contents, returning it as a String, using System default line separator.
     */
    public static String stringFromFile(File file) throws IOException {
        return stringFromFile(file, System.getProperty("line.separator"));
    }

    /**
     * Reads file contents, returning it as a String, joining lines with provided
     * separator.
     */
    public static String stringFromFile(File file, String joinWith) throws IOException {
        StringBuffer buf = new StringBuffer();
        BufferedReader in = new BufferedReader(new FileReader(file));

        try {
            String line = null;
            while ((line = in.readLine()) != null) {
                buf.append(line).append(joinWith);
            }
        } finally {
            in.close();
        }
        return buf.toString();
    }
}
