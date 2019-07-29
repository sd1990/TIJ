package org.songdan.tij.check;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.songdan.tij.check.checker.Checker;
import org.songdan.tij.check.config.CheckConfig;
import org.songdan.tij.check.config.CheckConfigItem;
import org.songdan.tij.check.result.CheckResult;
import org.songdan.tij.check.result.CheckResultItem;
import org.songdan.tij.designpattern.composite.JsonUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author: Songdan
 * @create: 2019-07-28 15:41
 **/
@Data
public class MissionConfig {
    private CheckConfig buildCheckConfig;

    public static void main(String[] args) throws IOException, InterruptedException {
        String json = stringFromFile(new File("/Users/songdan/github/TIJ/designpattern/src/main/java/org/songdan/tij/check/checkConfig.json"));
        MissionConfig missionConfig = JsonUtil.of(json, MissionConfig.class);
        Checker checker = missionConfig.getBuildCheckConfig().getChecker();
        for (int i = 0; i < 10; i++) {
            System.out.println("====================");
            CheckResult checkResult = checker.check(new CheckContext());
            Iterator<CheckResultItem> iterator = checkResult.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            System.out.println("====================");
            Thread.sleep(500);
        }
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
