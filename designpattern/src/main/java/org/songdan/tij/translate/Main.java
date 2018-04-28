package org.songdan.tij.translate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import org.songdan.tij.designpattern.composite.JsonUtil;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 13 四月 2018
 */
public class Main {

    public static void main(String[] args) throws IOException {
		String json = stringFromFile(new File("/Users/songdan/IdeaProjects/github/TIJ/designpattern/src/main/java/org/songdan/tij/translate/rule.json"));
//		String json = "{\"rules\":[{\"field\":\"position_code\",\"op\":\"equal\",\"value\":\"北京市\"},{\"rules\":[{\"field\":\"type\",\"op\":\"equal\",\"value\":\"门店\"},{\"field\":\"type\",\"op\":\"equal\",\"value\":\"无人货架\"}],\"op\":\"or\"}],\"op\":\"and\"}";
		AbsRule absRule = JsonUtil.of(json, AbsRule.class);
        System.out.println(
                Optional.ofNullable(absRule).map(AbsRule::toExpression).map(Expression::translate).orElse("i'm wrong"));
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
		}
		finally {
			in.close();
		}
		return buf.toString();
	}

}
