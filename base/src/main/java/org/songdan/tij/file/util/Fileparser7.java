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
public class Fileparser7 {

	public static void main(String[] args) throws IOException, URISyntaxException {
		File file = new File("/Users/songdan/Desktop/sd-shelf2.csv");
		File file2 = new File("/Users/songdan/Desktop/dzm-shelf2.csv");
//		File opcFile = new File("/Users/songdan/Desktop/opc-all-shelf-code.csv");
		BufferedReader reader = null;
		BufferedReader reader2 = null;
		try {
			System.out.println("读一整行:");
			// 读取非汉字可用
			// reader = new BufferedReader(new FileReader(file));
			// 读汉字可用
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
//			opcReader = new BufferedReader(new InputStreamReader(new FileInputStream(opcFile)));
			Set<String> set1 = extractSingleStr(reader);
			System.out.println(set1.size());
			Set<String> set2 = extractSingleStr(reader2);
			System.out.println(set2.size());
			Set<String> intersection = Sets.intersection(set1, set2);
			String join = String.join(",", intersection);
//			Set<String> opcSet = extractSingleStr(opcReader);
//			Set<String> noSellSet = Sets.difference(opcSet, set);
//			System.out.println(noSellSet.size());
			System.out.println(join);
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

	private static Set<String> extractSingleStr(BufferedReader reader) throws IOException {
		Set<String> set = new LinkedHashSet<>();
		String str;
		while ((str = reader.readLine()) != null) {
			String[] split = str.split(",");
			if (split[0].equals("")) {
				continue;
			}
			set.add("'" + str + "'");
		}
		return set;
	}

}
