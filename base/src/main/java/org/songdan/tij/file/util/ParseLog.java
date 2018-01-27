package org.songdan.tij.file.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.songdan.tij.generics.Sets;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 24 十一月 2017
 */
public class ParseLog {

	public static void main(String[] args) throws IOException, URISyntaxException {
		File file = new File("/Users/songdan/Documents/t1.txt");
//		File opcFile = new File("/Users/songdan/Desktop/opc-all-shelf-code.csv");
		BufferedReader reader = null;
		BufferedReader opcReader = null;
		try {
			System.out.println("读一整行:");
			// 读取非汉字可用
			// reader = new BufferedReader(new FileReader(file));
			// 读汉字可用
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			Set<String> lines = extractSingleStr(reader);
			String tag1 = "shelfAutoInitRequest: ";
			String tag2 = "调整销售点触发幂等控制:";
			LinkedHashSet<String> tagSet1 = new LinkedHashSet<>();
			LinkedHashSet<String> tagSet2 = new LinkedHashSet<>();
			for (String line : lines) {
				if (line.contains(tag1)) {
					String result = getString(tag1, line);

					tagSet1.add(result);
				}
				if (line.contains(tag2)) {
					String result = getString(tag2, line);
					tagSet2.add(result);
				}
			}
			Set<String> difference = Sets.difference(tagSet2, tagSet1);
			System.out.println(difference.size());
			System.out.println(difference);
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

	private static String getString(String tag1, String line) {
		String[] split = line.split(tag1);
		return split[split.length - 1];
	}

	private static Set<String> extractSingleStr(BufferedReader reader) throws IOException {
		Set<String> set = new LinkedHashSet<>();
		String str;
		while ((str = reader.readLine()) != null) {
			String[] split = str.split(",");
			if (split[0].equals("")) {
				continue;
			}
			set.add(str);
		}
		return set;
	}

}
