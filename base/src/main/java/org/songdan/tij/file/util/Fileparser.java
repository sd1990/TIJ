package org.songdan.tij.file.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import org.songdan.tij.generics.Sets;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 24 十一月 2017
 */
public class Fileparser {

	public static void main(String[] args) throws IOException, URISyntaxException {
		File file = new File("/Users/songdan/Documents/cancel-order-shop.csv");
//		File opcFile = new File("/Users/songdan/Desktop/opc-all-shelf-code.csv");
		BufferedReader reader = null;
		BufferedReader opcReader = null;
		try {
			System.out.println("读一整行:");
			// 读取非汉字可用
			// reader = new BufferedReader(new FileReader(file));
			// 读汉字可用
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//			opcReader = new BufferedReader(new InputStreamReader(new FileInputStream(opcFile)));
			Set<String> set = extractSingleStr(reader);
//			Set<String> opcSet = extractSingleStr(opcReader);
//			Set<String> noSellSet = Sets.difference(opcSet, set);
//			System.out.println(noSellSet.size());
			String join = String.join(",", set);
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
